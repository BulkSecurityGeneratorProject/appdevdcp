/* tslint:disable no-unused-expression */
import { browser, element, by, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import ItemAvaliadoPerdaQuebraAcumuladosComponentsPage from './item-avaliado-perda-quebra-acumulados.page-object';
import { ItemAvaliadoPerdaQuebraAcumuladosDeleteDialog } from './item-avaliado-perda-quebra-acumulados.page-object';
import ItemAvaliadoPerdaQuebraAcumuladosUpdatePage from './item-avaliado-perda-quebra-acumulados-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('ItemAvaliadoPerdaQuebraAcumulados e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let itemAvaliadoPerdaQuebraAcumuladosUpdatePage: ItemAvaliadoPerdaQuebraAcumuladosUpdatePage;
  let itemAvaliadoPerdaQuebraAcumuladosComponentsPage: ItemAvaliadoPerdaQuebraAcumuladosComponentsPage;
  let itemAvaliadoPerdaQuebraAcumuladosDeleteDialog: ItemAvaliadoPerdaQuebraAcumuladosDeleteDialog;

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

  it('should load ItemAvaliadoPerdaQuebraAcumulados', async () => {
    await navBarPage.getEntityPage('item-avaliado-perda-quebra-acumulados');
    itemAvaliadoPerdaQuebraAcumuladosComponentsPage = new ItemAvaliadoPerdaQuebraAcumuladosComponentsPage();
    expect(await itemAvaliadoPerdaQuebraAcumuladosComponentsPage.getTitle().getText()).to.match(/Item Avaliado Perda Quebra Acumulados/);
  });

  it('should load create ItemAvaliadoPerdaQuebraAcumulados page', async () => {
    await itemAvaliadoPerdaQuebraAcumuladosComponentsPage.clickOnCreateButton();
    itemAvaliadoPerdaQuebraAcumuladosUpdatePage = new ItemAvaliadoPerdaQuebraAcumuladosUpdatePage();
    expect(await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.getPageTitle().getAttribute('id')).to.match(
      /dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.home.createOrEditLabel/
    );
  });

  it('should create and save ItemAvaliadoPerdaQuebraAcumulados', async () => {
    const nbButtonsBeforeCreate = await itemAvaliadoPerdaQuebraAcumuladosComponentsPage.countDeleteButtons();

    await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.tipoSelectLastOption();
    await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.setRespondidoEmInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.getRespondidoEmInput()).to.contain('2001-01-01T02:30');
    await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.setUltimaAtualizacaoEmInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.getUltimaAtualizacaoEmInput()).to.contain('2001-01-01T02:30');
    await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.setPercentualInput('5');
    expect(await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.getPercentualInput()).to.eq('5');
    await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.setFinanceiroInput('5');
    expect(await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.getFinanceiroInput()).to.eq('5');
    await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.setPontuacaoInput('5');
    expect(await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.getPontuacaoInput()).to.eq('5');
    await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.setLatitudeLocalRespostaInput('5');
    expect(await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.getLatitudeLocalRespostaInput()).to.eq('5');
    await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.setLongitudeLocalRespostaInput('5');
    expect(await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.getLongitudeLocalRespostaInput()).to.eq('5');
    await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.avaliacaoSelectLastOption();
    await waitUntilDisplayed(itemAvaliadoPerdaQuebraAcumuladosUpdatePage.getSaveButton());
    await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.save();
    await waitUntilHidden(itemAvaliadoPerdaQuebraAcumuladosUpdatePage.getSaveButton());
    expect(await itemAvaliadoPerdaQuebraAcumuladosUpdatePage.getSaveButton().isPresent()).to.be.false;

    await itemAvaliadoPerdaQuebraAcumuladosComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await itemAvaliadoPerdaQuebraAcumuladosComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last ItemAvaliadoPerdaQuebraAcumulados', async () => {
    await itemAvaliadoPerdaQuebraAcumuladosComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await itemAvaliadoPerdaQuebraAcumuladosComponentsPage.countDeleteButtons();
    await itemAvaliadoPerdaQuebraAcumuladosComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    itemAvaliadoPerdaQuebraAcumuladosDeleteDialog = new ItemAvaliadoPerdaQuebraAcumuladosDeleteDialog();
    expect(await itemAvaliadoPerdaQuebraAcumuladosDeleteDialog.getDialogTitle().getAttribute('id')).to.match(
      /dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.delete.question/
    );
    await itemAvaliadoPerdaQuebraAcumuladosDeleteDialog.clickOnConfirmButton();

    await itemAvaliadoPerdaQuebraAcumuladosComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await itemAvaliadoPerdaQuebraAcumuladosComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
