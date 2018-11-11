/* tslint:disable no-unused-expression */
import { browser, element, by, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import ItemAvaliadoComponentsPage from './item-avaliado.page-object';
import { ItemAvaliadoDeleteDialog } from './item-avaliado.page-object';
import ItemAvaliadoUpdatePage from './item-avaliado-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('ItemAvaliado e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let itemAvaliadoUpdatePage: ItemAvaliadoUpdatePage;
  let itemAvaliadoComponentsPage: ItemAvaliadoComponentsPage;
  let itemAvaliadoDeleteDialog: ItemAvaliadoDeleteDialog;

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

  it('should load ItemAvaliados', async () => {
    await navBarPage.getEntityPage('item-avaliado');
    itemAvaliadoComponentsPage = new ItemAvaliadoComponentsPage();
    expect(await itemAvaliadoComponentsPage.getTitle().getText()).to.match(/Item Avaliados/);
  });

  it('should load create ItemAvaliado page', async () => {
    await itemAvaliadoComponentsPage.clickOnCreateButton();
    itemAvaliadoUpdatePage = new ItemAvaliadoUpdatePage();
    expect(await itemAvaliadoUpdatePage.getPageTitle().getAttribute('id')).to.match(
      /dcpdesconformidadesApp.itemAvaliado.home.createOrEditLabel/
    );
  });

  it('should create and save ItemAvaliados', async () => {
    const nbButtonsBeforeCreate = await itemAvaliadoComponentsPage.countDeleteButtons();

    await itemAvaliadoUpdatePage.setRespondidoEmInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await itemAvaliadoUpdatePage.getRespondidoEmInput()).to.contain('2001-01-01T02:30');
    await itemAvaliadoUpdatePage.setUltimaAtualizacaoEmInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await itemAvaliadoUpdatePage.getUltimaAtualizacaoEmInput()).to.contain('2001-01-01T02:30');
    await itemAvaliadoUpdatePage.statusSelectLastOption();
    await itemAvaliadoUpdatePage.setObservacoesInput('observacoes');
    expect(await itemAvaliadoUpdatePage.getObservacoesInput()).to.match(/observacoes/);
    await itemAvaliadoUpdatePage.setLatitudeLocalRespostaInput('5');
    expect(await itemAvaliadoUpdatePage.getLatitudeLocalRespostaInput()).to.eq('5');
    await itemAvaliadoUpdatePage.setLongitudeLocalRespostaInput('5');
    expect(await itemAvaliadoUpdatePage.getLongitudeLocalRespostaInput()).to.eq('5');
    await itemAvaliadoUpdatePage.avaliacaoSelectLastOption();
    await itemAvaliadoUpdatePage.itemAvaliacaoSelectLastOption();
    await waitUntilDisplayed(itemAvaliadoUpdatePage.getSaveButton());
    await itemAvaliadoUpdatePage.save();
    await waitUntilHidden(itemAvaliadoUpdatePage.getSaveButton());
    expect(await itemAvaliadoUpdatePage.getSaveButton().isPresent()).to.be.false;

    await itemAvaliadoComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await itemAvaliadoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last ItemAvaliado', async () => {
    await itemAvaliadoComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await itemAvaliadoComponentsPage.countDeleteButtons();
    await itemAvaliadoComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    itemAvaliadoDeleteDialog = new ItemAvaliadoDeleteDialog();
    expect(await itemAvaliadoDeleteDialog.getDialogTitle().getAttribute('id')).to.match(
      /dcpdesconformidadesApp.itemAvaliado.delete.question/
    );
    await itemAvaliadoDeleteDialog.clickOnConfirmButton();

    await itemAvaliadoComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await itemAvaliadoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
