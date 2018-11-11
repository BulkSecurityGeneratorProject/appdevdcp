/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import AnexoItemComponentsPage from './anexo-item.page-object';
import { AnexoItemDeleteDialog } from './anexo-item.page-object';
import AnexoItemUpdatePage from './anexo-item-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('AnexoItem e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let anexoItemUpdatePage: AnexoItemUpdatePage;
  let anexoItemComponentsPage: AnexoItemComponentsPage;
  /*let anexoItemDeleteDialog: AnexoItemDeleteDialog;*/

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

  it('should load AnexoItems', async () => {
    await navBarPage.getEntityPage('anexo-item');
    anexoItemComponentsPage = new AnexoItemComponentsPage();
    expect(await anexoItemComponentsPage.getTitle().getText()).to.match(/Anexo Items/);
  });

  it('should load create AnexoItem page', async () => {
    await anexoItemComponentsPage.clickOnCreateButton();
    anexoItemUpdatePage = new AnexoItemUpdatePage();
    expect(await anexoItemUpdatePage.getPageTitle().getAttribute('id')).to.match(/dcpdesconformidadesApp.anexoItem.home.createOrEditLabel/);
  });

  /* it('should create and save AnexoItems', async () => {
        const nbButtonsBeforeCreate = await anexoItemComponentsPage.countDeleteButtons();

        await anexoItemUpdatePage.tipoSelectLastOption();
        await anexoItemUpdatePage.setCaminhoArquivoInput('caminhoArquivo');
        expect(await anexoItemUpdatePage.getCaminhoArquivoInput()).to.match(/caminhoArquivo/);
        await anexoItemUpdatePage.itemAvaliadoSelectLastOption();
        await waitUntilDisplayed(anexoItemUpdatePage.getSaveButton());
        await anexoItemUpdatePage.save();
        await waitUntilHidden(anexoItemUpdatePage.getSaveButton());
        expect(await anexoItemUpdatePage.getSaveButton().isPresent()).to.be.false;

        await anexoItemComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
        expect(await anexoItemComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

  /* it('should delete last AnexoItem', async () => {
        await anexoItemComponentsPage.waitUntilLoaded();
        const nbButtonsBeforeDelete = await anexoItemComponentsPage.countDeleteButtons();
        await anexoItemComponentsPage.clickOnLastDeleteButton();

        const deleteModal = element(by.className('modal'));
        await waitUntilDisplayed(deleteModal);

        anexoItemDeleteDialog = new AnexoItemDeleteDialog();
        expect(await anexoItemDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/dcpdesconformidadesApp.anexoItem.delete.question/);
        await anexoItemDeleteDialog.clickOnConfirmButton();

        await anexoItemComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
        expect(await anexoItemComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
