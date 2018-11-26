/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import ItemAvaliacaoComponentsPage from './item-avaliacao.page-object';
import { ItemAvaliacaoDeleteDialog } from './item-avaliacao.page-object';
import ItemAvaliacaoUpdatePage from './item-avaliacao-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('ItemAvaliacao e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let itemAvaliacaoUpdatePage: ItemAvaliacaoUpdatePage;
  let itemAvaliacaoComponentsPage: ItemAvaliacaoComponentsPage;
  let itemAvaliacaoDeleteDialog: ItemAvaliacaoDeleteDialog;

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

  it('should load ItemAvaliacaos', async () => {
    await navBarPage.getEntityPage('item-avaliacao');
    itemAvaliacaoComponentsPage = new ItemAvaliacaoComponentsPage();
    expect(await itemAvaliacaoComponentsPage.getTitle().getText()).to.match(/Item Avaliacaos/);
  });

  it('should load create ItemAvaliacao page', async () => {
    await itemAvaliacaoComponentsPage.clickOnCreateButton();
    itemAvaliacaoUpdatePage = new ItemAvaliacaoUpdatePage();
    expect(await itemAvaliacaoUpdatePage.getPageTitle().getAttribute('id')).to.match(
      /dcpdesconformidadesApp.itemAvaliacao.home.createOrEditLabel/
    );
  });

  it('should create and save ItemAvaliacaos', async () => {
    const nbButtonsBeforeCreate = await itemAvaliacaoComponentsPage.countDeleteButtons();

    await itemAvaliacaoUpdatePage.setDescricaoInput('descricao');
    expect(await itemAvaliacaoUpdatePage.getDescricaoInput()).to.match(/descricao/);
    const selectedAnexoObrigatorio = await itemAvaliacaoUpdatePage.getAnexoObrigatorioInput().isSelected();
    if (selectedAnexoObrigatorio) {
      await itemAvaliacaoUpdatePage.getAnexoObrigatorioInput().click();
      expect(await itemAvaliacaoUpdatePage.getAnexoObrigatorioInput().isSelected()).to.be.false;
    } else {
      await itemAvaliacaoUpdatePage.getAnexoObrigatorioInput().click();
      expect(await itemAvaliacaoUpdatePage.getAnexoObrigatorioInput().isSelected()).to.be.true;
    }
    await itemAvaliacaoUpdatePage.setPontosProcedimentoInput('5');
    expect(await itemAvaliacaoUpdatePage.getPontosProcedimentoInput()).to.eq('5');
    await itemAvaliacaoUpdatePage.setPontosPessoaInput('5');
    expect(await itemAvaliacaoUpdatePage.getPontosPessoaInput()).to.eq('5');
    await itemAvaliacaoUpdatePage.setPontosProcessoInput('5');
    expect(await itemAvaliacaoUpdatePage.getPontosProcessoInput()).to.eq('5');
    await itemAvaliacaoUpdatePage.setPontosProdutoInput('5');
    expect(await itemAvaliacaoUpdatePage.getPontosProdutoInput()).to.eq('5');
    await waitUntilDisplayed(itemAvaliacaoUpdatePage.getSaveButton());
    await itemAvaliacaoUpdatePage.save();
    await waitUntilHidden(itemAvaliacaoUpdatePage.getSaveButton());
    expect(await itemAvaliacaoUpdatePage.getSaveButton().isPresent()).to.be.false;

    await itemAvaliacaoComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
    expect(await itemAvaliacaoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
  });

  it('should delete last ItemAvaliacao', async () => {
    await itemAvaliacaoComponentsPage.waitUntilLoaded();
    const nbButtonsBeforeDelete = await itemAvaliacaoComponentsPage.countDeleteButtons();
    await itemAvaliacaoComponentsPage.clickOnLastDeleteButton();

    const deleteModal = element(by.className('modal'));
    await waitUntilDisplayed(deleteModal);

    itemAvaliacaoDeleteDialog = new ItemAvaliacaoDeleteDialog();
    expect(await itemAvaliacaoDeleteDialog.getDialogTitle().getAttribute('id')).to.match(
      /dcpdesconformidadesApp.itemAvaliacao.delete.question/
    );
    await itemAvaliacaoDeleteDialog.clickOnConfirmButton();

    await itemAvaliacaoComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
    expect(await itemAvaliacaoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
