/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import QuestionarioComponentsPage from './questionario.page-object';
import { QuestionarioDeleteDialog } from './questionario.page-object';
import QuestionarioUpdatePage from './questionario-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Questionario e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let questionarioUpdatePage: QuestionarioUpdatePage;
  let questionarioComponentsPage: QuestionarioComponentsPage;
  let questionarioDeleteDialog: QuestionarioDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.waitUntilDisplayed();

    await signInPage.username.sendKeys('admin');
    await signInPage.password.sendKeys('admin');
    await signInPage.loginButton.click();
    await signInPage.waitUntilHidden();

    await waitUntilDisplayed(navBarPage.entityMenu);
  });

  it('should load Questionarios', async () => {
    await navBarPage.getEntityPage('questionario');
    questionarioComponentsPage = new QuestionarioComponentsPage();
    expect(await questionarioComponentsPage.getTitle().getText()).to.match(/Questionarios/);
  });

  it('should load create Questionario page', async () => {
    await questionarioComponentsPage.clickOnCreateButton();
    questionarioUpdatePage = new QuestionarioUpdatePage();
    expect(await questionarioUpdatePage.getPageTitle().getAttribute('id')).to.match(
      /dcpdesconformidadesApp.questionario.home.createOrEditLabel/
    );
  });

  it('should create and save Questionarios', async () => {
    const nbButtonsBeforeCreate = await questionarioComponentsPage.countDeleteButtons();

    await questionarioUpdatePage.setNomeInput('nome');
    expect(await questionarioUpdatePage.getNomeInput()).to.match(/nome/);
    await questionarioUpdatePage.setDescricaoInput('descricao');
    expect(await questionarioUpdatePage.getDescricaoInput()).to.match(/descricao/);
    const selectedAtivo = await questionarioUpdatePage.getAtivoInput().isSelected();
    if (selectedAtivo) {
      await questionarioUpdatePage.getAtivoInput().click();
      expect(await questionarioUpdatePage.getAtivoInput().isSelected()).to.be.false;
    } else {
      await questionarioUpdatePage.getAtivoInput().click();
      expect(await questionarioUpdatePage.getAtivoInput().isSelected()).to.be.true;
    }
    await questionarioUpdatePage.setVersaoInput('5');
    expect(await questionarioUpdatePage.getVersaoInput()).to.eq('5');
    await waitUntilDisplayed(questionarioUpdatePage.getSaveButton());
    await questionarioUpdatePage.save();
    await waitUntilHidden(questionarioUpdatePage.getSaveButton());
    expect(await questionarioUpdatePage.getSaveButton().isPresent()).to.be.false;

    await questionarioComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await questionarioComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Questionario', async () => {
    await questionarioComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await questionarioComponentsPage.countDeleteButtons();
    await questionarioComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    questionarioDeleteDialog = new QuestionarioDeleteDialog();
    expect(await questionarioDeleteDialog.getDialogTitle().getAttribute('id')).to.match(
      /dcpdesconformidadesApp.questionario.delete.question/
    );
    await questionarioDeleteDialog.clickOnConfirmButton();

    await questionarioComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await questionarioComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
