/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import PerdaQuebraAcumuladosAnoLojaComponentsPage from './perda-quebra-acumulados-ano-loja.page-object';
import { PerdaQuebraAcumuladosAnoLojaDeleteDialog } from './perda-quebra-acumulados-ano-loja.page-object';
import PerdaQuebraAcumuladosAnoLojaUpdatePage from './perda-quebra-acumulados-ano-loja-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('PerdaQuebraAcumuladosAnoLoja e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let perdaQuebraAcumuladosAnoLojaUpdatePage: PerdaQuebraAcumuladosAnoLojaUpdatePage;
  let perdaQuebraAcumuladosAnoLojaComponentsPage: PerdaQuebraAcumuladosAnoLojaComponentsPage;
  /*let perdaQuebraAcumuladosAnoLojaDeleteDialog: PerdaQuebraAcumuladosAnoLojaDeleteDialog;*/

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

  it('should load PerdaQuebraAcumuladosAnoLojas', async () => {
    await navBarPage.getEntityPage('perda-quebra-acumulados-ano-loja');
    perdaQuebraAcumuladosAnoLojaComponentsPage = new PerdaQuebraAcumuladosAnoLojaComponentsPage();
    expect(await perdaQuebraAcumuladosAnoLojaComponentsPage.getTitle().getText()).to.match(/Perda Quebra Acumulados Ano Lojas/);
  });

  it('should load create PerdaQuebraAcumuladosAnoLoja page', async () => {
    await perdaQuebraAcumuladosAnoLojaComponentsPage.clickOnCreateButton();
    perdaQuebraAcumuladosAnoLojaUpdatePage = new PerdaQuebraAcumuladosAnoLojaUpdatePage();
    expect(await perdaQuebraAcumuladosAnoLojaUpdatePage.getPageTitle().getAttribute('id')).to.match(
      /dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.home.createOrEditLabel/
    );
  });

  /* it('should create and save PerdaQuebraAcumuladosAnoLojas', async () => {
        const nbButtonsBeforeCreate = await perdaQuebraAcumuladosAnoLojaComponentsPage.countDeleteButtons();

        await perdaQuebraAcumuladosAnoLojaUpdatePage.setAnoInput('5');
        expect(await perdaQuebraAcumuladosAnoLojaUpdatePage.getAnoInput()).to.eq('5');
        await perdaQuebraAcumuladosAnoLojaUpdatePage.setPercentualPerdaInput('5');
        expect(await perdaQuebraAcumuladosAnoLojaUpdatePage.getPercentualPerdaInput()).to.eq('5');
        await perdaQuebraAcumuladosAnoLojaUpdatePage.setFinanceiroPerdaInput('5');
        expect(await perdaQuebraAcumuladosAnoLojaUpdatePage.getFinanceiroPerdaInput()).to.eq('5');
        await perdaQuebraAcumuladosAnoLojaUpdatePage.setPontuacaoPerdaInput('5');
        expect(await perdaQuebraAcumuladosAnoLojaUpdatePage.getPontuacaoPerdaInput()).to.eq('5');
        await perdaQuebraAcumuladosAnoLojaUpdatePage.statusPerdaSelectLastOption();
        await perdaQuebraAcumuladosAnoLojaUpdatePage.categorizacaoPerdaSelectLastOption();
        await perdaQuebraAcumuladosAnoLojaUpdatePage.setPercentualQuebraInput('5');
        expect(await perdaQuebraAcumuladosAnoLojaUpdatePage.getPercentualQuebraInput()).to.eq('5');
        await perdaQuebraAcumuladosAnoLojaUpdatePage.setFinanceiroQuebraInput('5');
        expect(await perdaQuebraAcumuladosAnoLojaUpdatePage.getFinanceiroQuebraInput()).to.eq('5');
        await perdaQuebraAcumuladosAnoLojaUpdatePage.setPontuacaoQuebraInput('5');
        expect(await perdaQuebraAcumuladosAnoLojaUpdatePage.getPontuacaoQuebraInput()).to.eq('5');
        await perdaQuebraAcumuladosAnoLojaUpdatePage.statusQuebraSelectLastOption();
        await perdaQuebraAcumuladosAnoLojaUpdatePage.categorizacaoQuebraSelectLastOption();
        await perdaQuebraAcumuladosAnoLojaUpdatePage.lojaSelectLastOption();
        await waitUntilDisplayed(perdaQuebraAcumuladosAnoLojaUpdatePage.getSaveButton());
        await perdaQuebraAcumuladosAnoLojaUpdatePage.save();
        await waitUntilHidden(perdaQuebraAcumuladosAnoLojaUpdatePage.getSaveButton());
        expect(await perdaQuebraAcumuladosAnoLojaUpdatePage.getSaveButton().isPresent()).to.be.false;

        await perdaQuebraAcumuladosAnoLojaComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
        expect(await perdaQuebraAcumuladosAnoLojaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

  /* it('should delete last PerdaQuebraAcumuladosAnoLoja', async () => {
        await perdaQuebraAcumuladosAnoLojaComponentsPage.waitUntilLoaded();
        const nbButtonsBeforeDelete = await perdaQuebraAcumuladosAnoLojaComponentsPage.countDeleteButtons();
        await perdaQuebraAcumuladosAnoLojaComponentsPage.clickOnLastDeleteButton();

        const deleteModal = element(by.className('modal'));
        await waitUntilDisplayed(deleteModal);

        perdaQuebraAcumuladosAnoLojaDeleteDialog = new PerdaQuebraAcumuladosAnoLojaDeleteDialog();
        expect(await perdaQuebraAcumuladosAnoLojaDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.delete.question/);
        await perdaQuebraAcumuladosAnoLojaDeleteDialog.clickOnConfirmButton();

        await perdaQuebraAcumuladosAnoLojaComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
        expect(await perdaQuebraAcumuladosAnoLojaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
