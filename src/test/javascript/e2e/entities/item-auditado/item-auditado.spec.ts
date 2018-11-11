/* tslint:disable no-unused-expression */
import { browser, element, by, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import ItemAuditadoComponentsPage from './item-auditado.page-object';
import { ItemAuditadoDeleteDialog } from './item-auditado.page-object';
import ItemAuditadoUpdatePage from './item-auditado-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('ItemAuditado e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let itemAuditadoUpdatePage: ItemAuditadoUpdatePage;
  let itemAuditadoComponentsPage: ItemAuditadoComponentsPage;
  let itemAuditadoDeleteDialog: ItemAuditadoDeleteDialog;

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

  it('should load ItemAuditados', async () => {
    await navBarPage.getEntityPage('item-auditado');
    itemAuditadoComponentsPage = new ItemAuditadoComponentsPage();
    expect(await itemAuditadoComponentsPage.getTitle().getText()).to.match(/Item Auditados/);
  });

  it('should load create ItemAuditado page', async () => {
    await itemAuditadoComponentsPage.clickOnCreateButton();
    itemAuditadoUpdatePage = new ItemAuditadoUpdatePage();
    expect(await itemAuditadoUpdatePage.getPageTitle().getAttribute('id')).to.match(
      /dcpdesconformidadesApp.itemAuditado.home.createOrEditLabel/
    );
  });

  it('should create and save ItemAuditados', async () => {
    const nbButtonsBeforeCreate = await itemAuditadoComponentsPage.countDeleteButtons();

    await itemAuditadoUpdatePage.setRespondidoEmInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await itemAuditadoUpdatePage.getRespondidoEmInput()).to.contain('2001-01-01T02:30');
    await itemAuditadoUpdatePage.setUltimaAtualizacaoEmInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await itemAuditadoUpdatePage.getUltimaAtualizacaoEmInput()).to.contain('2001-01-01T02:30');
    await itemAuditadoUpdatePage.tipoSelectLastOption();
    await itemAuditadoUpdatePage.setDepartamentoInput('5');
    expect(await itemAuditadoUpdatePage.getDepartamentoInput()).to.eq('5');
    await itemAuditadoUpdatePage.setCodigoSapInput('5');
    expect(await itemAuditadoUpdatePage.getCodigoSapInput()).to.eq('5');
    await itemAuditadoUpdatePage.setDescricaoItemInput('descricaoItem');
    expect(await itemAuditadoUpdatePage.getDescricaoItemInput()).to.match(/descricaoItem/);
    await itemAuditadoUpdatePage.setSaldoSapInput('5');
    expect(await itemAuditadoUpdatePage.getSaldoSapInput()).to.eq('5');
    await itemAuditadoUpdatePage.setSaldoFisicoInput('5');
    expect(await itemAuditadoUpdatePage.getSaldoFisicoInput()).to.eq('5');
    await itemAuditadoUpdatePage.setMotivoDivergenciaInput('motivoDivergencia');
    expect(await itemAuditadoUpdatePage.getMotivoDivergenciaInput()).to.match(/motivoDivergencia/);
    await itemAuditadoUpdatePage.avaliacaoSelectLastOption();
    await waitUntilDisplayed(itemAuditadoUpdatePage.getSaveButton());
    await itemAuditadoUpdatePage.save();
    await waitUntilHidden(itemAuditadoUpdatePage.getSaveButton());
    expect(await itemAuditadoUpdatePage.getSaveButton().isPresent()).to.be.false;

    await itemAuditadoComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await itemAuditadoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last ItemAuditado', async () => {
    await itemAuditadoComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await itemAuditadoComponentsPage.countDeleteButtons();
    await itemAuditadoComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    itemAuditadoDeleteDialog = new ItemAuditadoDeleteDialog();
    expect(await itemAuditadoDeleteDialog.getDialogTitle().getAttribute('id')).to.match(
      /dcpdesconformidadesApp.itemAuditado.delete.question/
    );
    await itemAuditadoDeleteDialog.clickOnConfirmButton();

    await itemAuditadoComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await itemAuditadoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
