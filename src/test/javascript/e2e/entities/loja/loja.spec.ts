/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import LojaComponentsPage from './loja.page-object';
import { LojaDeleteDialog } from './loja.page-object';
import LojaUpdatePage from './loja-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Loja e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let lojaUpdatePage: LojaUpdatePage;
  let lojaComponentsPage: LojaComponentsPage;
  let lojaDeleteDialog: LojaDeleteDialog;

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

  it('should load Lojas', async () => {
    await navBarPage.getEntityPage('loja');
    lojaComponentsPage = new LojaComponentsPage();
    expect(await lojaComponentsPage.getTitle().getText()).to.match(/Lojas/);
  });

  it('should load create Loja page', async () => {
    await lojaComponentsPage.clickOnCreateButton();
    lojaUpdatePage = new LojaUpdatePage();
    expect(await lojaUpdatePage.getPageTitle().getAttribute('id')).to.match(/dcpdesconformidadesApp.loja.home.createOrEditLabel/);
  });

  it('should create and save Lojas', async () => {
    const nbButtonsBeforeCreate = await lojaComponentsPage.countDeleteButtons();

    await lojaUpdatePage.setNomeInput('nome');
    expect(await lojaUpdatePage.getNomeInput()).to.match(/nome/);
    await lojaUpdatePage.setEnderecoInput('endereco');
    expect(await lojaUpdatePage.getEnderecoInput()).to.match(/endereco/);
    await lojaUpdatePage.setCidadeInput('cidade');
    expect(await lojaUpdatePage.getCidadeInput()).to.match(/cidade/);
    await lojaUpdatePage.setCepInput('cep');
    expect(await lojaUpdatePage.getCepInput()).to.match(/cep/);
    await lojaUpdatePage.setLatitudeInput('5');
    expect(await lojaUpdatePage.getLatitudeInput()).to.eq('5');
    await lojaUpdatePage.setLongitudeInput('5');
    expect(await lojaUpdatePage.getLongitudeInput()).to.eq('5');
    // lojaUpdatePage.userSelectLastOption();
    await waitUntilDisplayed(lojaUpdatePage.getSaveButton());
    await lojaUpdatePage.save();
    await waitUntilHidden(lojaUpdatePage.getSaveButton());
    expect(await lojaUpdatePage.getSaveButton().isPresent()).to.be.false;

    await lojaComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await lojaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last Loja', async () => {
    await lojaComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await lojaComponentsPage.countDeleteButtons();
    await lojaComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    lojaDeleteDialog = new LojaDeleteDialog();
    expect(await lojaDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/dcpdesconformidadesApp.loja.delete.question/);
    await lojaDeleteDialog.clickOnConfirmButton();

    await lojaComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await lojaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
