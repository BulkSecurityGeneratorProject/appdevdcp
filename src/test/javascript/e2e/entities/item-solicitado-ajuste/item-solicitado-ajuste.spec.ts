/* tslint:disable no-unused-expression */
import { browser, element, by, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import ItemSolicitadoAjusteComponentsPage from './item-solicitado-ajuste.page-object';
import { ItemSolicitadoAjusteDeleteDialog } from './item-solicitado-ajuste.page-object';
import ItemSolicitadoAjusteUpdatePage from './item-solicitado-ajuste-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('ItemSolicitadoAjuste e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let itemSolicitadoAjusteUpdatePage: ItemSolicitadoAjusteUpdatePage;
  let itemSolicitadoAjusteComponentsPage: ItemSolicitadoAjusteComponentsPage;
  /*let itemSolicitadoAjusteDeleteDialog: ItemSolicitadoAjusteDeleteDialog;*/

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

  it('should load ItemSolicitadoAjustes', async () => {
    await navBarPage.getEntityPage('item-solicitado-ajuste');
    itemSolicitadoAjusteComponentsPage = new ItemSolicitadoAjusteComponentsPage();
    expect(await itemSolicitadoAjusteComponentsPage.getTitle().getText()).to.match(/Item Solicitado Ajustes/);
  });

  it('should load create ItemSolicitadoAjuste page', async () => {
    await itemSolicitadoAjusteComponentsPage.clickOnCreateButton();
    itemSolicitadoAjusteUpdatePage = new ItemSolicitadoAjusteUpdatePage();
    expect(await itemSolicitadoAjusteUpdatePage.getPageTitle().getAttribute('id')).to.match(
      /dcpdesconformidadesApp.itemSolicitadoAjuste.home.createOrEditLabel/
    );
  });

  /* it('should create and save ItemSolicitadoAjustes', async () => {
        const nbButtonsBeforeCreate = await itemSolicitadoAjusteComponentsPage.countDeleteButtons();

        await itemSolicitadoAjusteUpdatePage.setRespondidoEmInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(await itemSolicitadoAjusteUpdatePage.getRespondidoEmInput()).to.contain('2001-01-01T02:30');
        await itemSolicitadoAjusteUpdatePage.setUltimaAtualizacaoEmInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(await itemSolicitadoAjusteUpdatePage.getUltimaAtualizacaoEmInput()).to.contain('2001-01-01T02:30');
        await itemSolicitadoAjusteUpdatePage.setDepartamentoInput('5');
        expect(await itemSolicitadoAjusteUpdatePage.getDepartamentoInput()).to.eq('5');
        await itemSolicitadoAjusteUpdatePage.setCodigoSapInput('5');
        expect(await itemSolicitadoAjusteUpdatePage.getCodigoSapInput()).to.eq('5');
        await itemSolicitadoAjusteUpdatePage.setDescricaoItemInput('descricaoItem');
        expect(await itemSolicitadoAjusteUpdatePage.getDescricaoItemInput()).to.match(/descricaoItem/);
        await itemSolicitadoAjusteUpdatePage.setSaldoSapInput('5');
        expect(await itemSolicitadoAjusteUpdatePage.getSaldoSapInput()).to.eq('5');
        await itemSolicitadoAjusteUpdatePage.setSaldoFisicoInput('5');
        expect(await itemSolicitadoAjusteUpdatePage.getSaldoFisicoInput()).to.eq('5');
        await itemSolicitadoAjusteUpdatePage.setMotivoDivergenciaInput('motivoDivergencia');
        expect(await itemSolicitadoAjusteUpdatePage.getMotivoDivergenciaInput()).to.match(/motivoDivergencia/);
        await itemSolicitadoAjusteUpdatePage.setAcaoCorretivaOuPreventivaInput('acaoCorretivaOuPreventiva');
        expect(await itemSolicitadoAjusteUpdatePage.getAcaoCorretivaOuPreventivaInput()).to.match(/acaoCorretivaOuPreventiva/);
        await itemSolicitadoAjusteUpdatePage.setResponsavelInput('responsavel');
        expect(await itemSolicitadoAjusteUpdatePage.getResponsavelInput()).to.match(/responsavel/);
        await itemSolicitadoAjusteUpdatePage.avaliacaoSelectLastOption();
        await waitUntilDisplayed(itemSolicitadoAjusteUpdatePage.getSaveButton());
        await itemSolicitadoAjusteUpdatePage.save();
        await waitUntilHidden(itemSolicitadoAjusteUpdatePage.getSaveButton());
        expect(await itemSolicitadoAjusteUpdatePage.getSaveButton().isPresent()).to.be.false;

        await itemSolicitadoAjusteComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
        expect(await itemSolicitadoAjusteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

  /* it('should delete last ItemSolicitadoAjuste', async () => {
        await itemSolicitadoAjusteComponentsPage.waitUntilLoaded();
        const nbButtonsBeforeDelete = await itemSolicitadoAjusteComponentsPage.countDeleteButtons();
        await itemSolicitadoAjusteComponentsPage.clickOnLastDeleteButton();

        const deleteModal = element(by.className('modal'));
        await waitUntilDisplayed(deleteModal);

        itemSolicitadoAjusteDeleteDialog = new ItemSolicitadoAjusteDeleteDialog();
        expect(await itemSolicitadoAjusteDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/dcpdesconformidadesApp.itemSolicitadoAjuste.delete.question/);
        await itemSolicitadoAjusteDeleteDialog.clickOnConfirmButton();

        await itemSolicitadoAjusteComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
        expect(await itemSolicitadoAjusteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
