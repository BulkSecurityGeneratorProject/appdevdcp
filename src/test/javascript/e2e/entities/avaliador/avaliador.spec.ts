/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import AvaliadorComponentsPage from './avaliador.page-object';
import { AvaliadorDeleteDialog } from './avaliador.page-object';
import AvaliadorUpdatePage from './avaliador-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Avaliador e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let avaliadorUpdatePage: AvaliadorUpdatePage;
  let avaliadorComponentsPage: AvaliadorComponentsPage;
  let avaliadorDeleteDialog: AvaliadorDeleteDialog;

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

  it('should load Avaliadors', async () => {
    await navBarPage.getEntityPage('avaliador');
    avaliadorComponentsPage = new AvaliadorComponentsPage();
    expect(await avaliadorComponentsPage.getTitle().getText()).to.match(/Avaliadors/);
  });

  it('should load create Avaliador page', async () => {
    await avaliadorComponentsPage.clickOnCreateButton();
    avaliadorUpdatePage = new AvaliadorUpdatePage();
    expect(await avaliadorUpdatePage.getPageTitle().getAttribute('id')).to.match(/dcpdesconformidadesApp.avaliador.home.createOrEditLabel/);
  });

  it('should create and save Avaliadors', async () => {
    const nbButtonsBeforeCreate = await avaliadorComponentsPage.countDeleteButtons();

    await avaliadorUpdatePage.setNomeInput('nome');
    expect(await avaliadorUpdatePage.getNomeInput()).to.match(/nome/);
    await avaliadorUpdatePage.setLoginInput('login');
    expect(await avaliadorUpdatePage.getLoginInput()).to.match(/login/);
    await avaliadorUpdatePage.setProntuarioInput('5');
    expect(await avaliadorUpdatePage.getProntuarioInput()).to.eq('5');
    await waitUntilDisplayed(avaliadorUpdatePage.getSaveButton());
    await avaliadorUpdatePage.save();
    await waitUntilHidden(avaliadorUpdatePage.getSaveButton());
    expect(await avaliadorUpdatePage.getSaveButton().isPresent()).to.be.false;

    await avaliadorComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await avaliadorComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Avaliador', async () => {
    await avaliadorComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await avaliadorComponentsPage.countDeleteButtons();
    await avaliadorComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    avaliadorDeleteDialog = new AvaliadorDeleteDialog();
    expect(await avaliadorDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/dcpdesconformidadesApp.avaliador.delete.question/);
    await avaliadorDeleteDialog.clickOnConfirmButton();

    await avaliadorComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await avaliadorComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
