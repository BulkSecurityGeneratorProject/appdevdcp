/* tslint:disable no-unused-expression */
import { browser, element, by, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import GrupoItensComponentsPage from './grupo-itens.page-object';
import { GrupoItensDeleteDialog } from './grupo-itens.page-object';
import GrupoItensUpdatePage from './grupo-itens-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('GrupoItens e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let grupoItensUpdatePage: GrupoItensUpdatePage;
  let grupoItensComponentsPage: GrupoItensComponentsPage;
  let grupoItensDeleteDialog: GrupoItensDeleteDialog;

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

  it('should load GrupoItens', async () => {
    await navBarPage.getEntityPage('grupo-itens');
    grupoItensComponentsPage = new GrupoItensComponentsPage();
    expect(await grupoItensComponentsPage.getTitle().getText()).to.match(/Grupo Itens/);
  });

  it('should load create GrupoItens page', async () => {
    await grupoItensComponentsPage.clickOnCreateButton();
    grupoItensUpdatePage = new GrupoItensUpdatePage();
    expect(await grupoItensUpdatePage.getPageTitle().getAttribute('id')).to.match(
      /dcpdesconformidadesApp.grupoItens.home.createOrEditLabel/
    );
  });

  it('should create and save GrupoItens', async () => {
    const nbButtonsBeforeCreate = await grupoItensComponentsPage.countDeleteButtons();

    await grupoItensUpdatePage.setNomeInput('nome');
    expect(await grupoItensUpdatePage.getNomeInput()).to.match(/nome/);
    await grupoItensUpdatePage.setCriadoEmInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await grupoItensUpdatePage.getCriadoEmInput()).to.contain('2001-01-01T02:30');
    // grupoItensUpdatePage.itensSelectLastOption();
    await waitUntilDisplayed(grupoItensUpdatePage.getSaveButton());
    await grupoItensUpdatePage.save();
    await waitUntilHidden(grupoItensUpdatePage.getSaveButton());
    expect(await grupoItensUpdatePage.getSaveButton().isPresent()).to.be.false;

    await grupoItensComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await grupoItensComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last GrupoItens', async () => {
    await grupoItensComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await grupoItensComponentsPage.countDeleteButtons();
    await grupoItensComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    grupoItensDeleteDialog = new GrupoItensDeleteDialog();
    expect(await grupoItensDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/dcpdesconformidadesApp.grupoItens.delete.question/);
    await grupoItensDeleteDialog.clickOnConfirmButton();

    await grupoItensComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await grupoItensComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
