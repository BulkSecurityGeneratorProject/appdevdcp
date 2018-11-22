/* tslint:disable no-unused-expression */
import { browser, element, by, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import AvaliacaoComponentsPage from './avaliacao.page-object';
import { AvaliacaoDeleteDialog } from './avaliacao.page-object';
import AvaliacaoUpdatePage from './avaliacao-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Avaliacao e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let avaliacaoUpdatePage: AvaliacaoUpdatePage;
  let avaliacaoComponentsPage: AvaliacaoComponentsPage;
  /*let avaliacaoDeleteDialog: AvaliacaoDeleteDialog;*/

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

  it('should load Avaliacaos', async () => {
    await navBarPage.getEntityPage('avaliacao');
    avaliacaoComponentsPage = new AvaliacaoComponentsPage();
    expect(await avaliacaoComponentsPage.getTitle().getText()).to.match(/Avaliacaos/);
  });

  it('should load create Avaliacao page', async () => {
    await avaliacaoComponentsPage.clickOnCreateButton();
    avaliacaoUpdatePage = new AvaliacaoUpdatePage();
    expect(await avaliacaoUpdatePage.getPageTitle().getAttribute('id')).to.match(/dcpdesconformidadesApp.avaliacao.home.createOrEditLabel/);
  });

  /* it('should create and save Avaliacaos', async () => {
        const nbButtonsBeforeCreate = await avaliacaoComponentsPage.countDeleteButtons();

        await avaliacaoUpdatePage.setIniciadaEmInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(await avaliacaoUpdatePage.getIniciadaEmInput()).to.contain('2001-01-01T02:30');
        await avaliacaoUpdatePage.setLatitudeInicioAvaliacaoInput('5');
        expect(await avaliacaoUpdatePage.getLatitudeInicioAvaliacaoInput()).to.eq('5');
        await avaliacaoUpdatePage.setLongitudeInicioAvaliacaoInput('5');
        expect(await avaliacaoUpdatePage.getLongitudeInicioAvaliacaoInput()).to.eq('5');
        await avaliacaoUpdatePage.setNomeResponsavelLojaInput('nomeResponsavelLoja');
        expect(await avaliacaoUpdatePage.getNomeResponsavelLojaInput()).to.match(/nomeResponsavelLoja/);
        await avaliacaoUpdatePage.setProntuarioResponsavelLojaInput('5');
        expect(await avaliacaoUpdatePage.getProntuarioResponsavelLojaInput()).to.eq('5');
        await avaliacaoUpdatePage.setSubmetidoEmInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(await avaliacaoUpdatePage.getSubmetidoEmInput()).to.contain('2001-01-01T02:30');
        await avaliacaoUpdatePage.setLatitudeSubmissaoAvaliacaoInput('5');
        expect(await avaliacaoUpdatePage.getLatitudeSubmissaoAvaliacaoInput()).to.eq('5');
        await avaliacaoUpdatePage.setLongitudeSubmissaoAvaliacaoInput('5');
        expect(await avaliacaoUpdatePage.getLongitudeSubmissaoAvaliacaoInput()).to.eq('5');
        await avaliacaoUpdatePage.setObservacaoSubmissaoEnviadaForaDaLojaInput('observacaoSubmissaoEnviadaForaDaLoja');
        expect(await avaliacaoUpdatePage.getObservacaoSubmissaoEnviadaForaDaLojaInput()).to.match(/observacaoSubmissaoEnviadaForaDaLoja/);
        await avaliacaoUpdatePage.statusSelectLastOption();
        await avaliacaoUpdatePage.criticidadePainelSelectLastOption();
        await avaliacaoUpdatePage.nivelEficienciaGeralSelectLastOption();
        await avaliacaoUpdatePage.nivelEficienciaProcedimentoSelectLastOption();
        await avaliacaoUpdatePage.nivelEficienciaPessoaSelectLastOption();
        await avaliacaoUpdatePage.nivelEficienciaProcessoSelectLastOption();
        await avaliacaoUpdatePage.nivelEficienciaProdutoSelectLastOption();
        await avaliacaoUpdatePage.setCanceladoEmInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(await avaliacaoUpdatePage.getCanceladoEmInput()).to.contain('2001-01-01T02:30');
        await avaliacaoUpdatePage.setMotivoCancelamentoInput('motivoCancelamento');
        expect(await avaliacaoUpdatePage.getMotivoCancelamentoInput()).to.match(/motivoCancelamento/);
        await avaliacaoUpdatePage.userSelectLastOption();
        await avaliacaoUpdatePage.questionarioSelectLastOption();
        await waitUntilDisplayed(avaliacaoUpdatePage.getSaveButton());
        await avaliacaoUpdatePage.save();
        await waitUntilHidden(avaliacaoUpdatePage.getSaveButton());
        expect(await avaliacaoUpdatePage.getSaveButton().isPresent()).to.be.false;

        await avaliacaoComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeCreate + 1);
        expect(await avaliacaoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

  /* it('should delete last Avaliacao', async () => {
        await avaliacaoComponentsPage.waitUntilLoaded();
        const nbButtonsBeforeDelete = await avaliacaoComponentsPage.countDeleteButtons();
        await avaliacaoComponentsPage.clickOnLastDeleteButton();

        const deleteModal = element(by.className('modal'));
        await waitUntilDisplayed(deleteModal);

        avaliacaoDeleteDialog = new AvaliacaoDeleteDialog();
        expect(await avaliacaoDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/dcpdesconformidadesApp.avaliacao.delete.question/);
        await avaliacaoDeleteDialog.clickOnConfirmButton();

        await avaliacaoComponentsPage.waitUntilDeleteButtonsLength(nbButtonsBeforeDelete - 1);
        expect(await avaliacaoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
