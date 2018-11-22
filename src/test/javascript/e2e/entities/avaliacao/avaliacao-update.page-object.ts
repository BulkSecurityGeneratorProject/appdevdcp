import { element, by, ElementFinder } from 'protractor';

export default class AvaliacaoUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.avaliacao.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  iniciadaEmInput: ElementFinder = element(by.css('input#avaliacao-iniciadaEm'));
  latitudeInicioAvaliacaoInput: ElementFinder = element(by.css('input#avaliacao-latitudeInicioAvaliacao'));
  longitudeInicioAvaliacaoInput: ElementFinder = element(by.css('input#avaliacao-longitudeInicioAvaliacao'));
  nomeResponsavelLojaInput: ElementFinder = element(by.css('input#avaliacao-nomeResponsavelLoja'));
  prontuarioResponsavelLojaInput: ElementFinder = element(by.css('input#avaliacao-prontuarioResponsavelLoja'));
  submetidoEmInput: ElementFinder = element(by.css('input#avaliacao-submetidoEm'));
  latitudeSubmissaoAvaliacaoInput: ElementFinder = element(by.css('input#avaliacao-latitudeSubmissaoAvaliacao'));
  longitudeSubmissaoAvaliacaoInput: ElementFinder = element(by.css('input#avaliacao-longitudeSubmissaoAvaliacao'));
  observacaoSubmissaoEnviadaForaDaLojaInput: ElementFinder = element(by.css('input#avaliacao-observacaoSubmissaoEnviadaForaDaLoja'));
  statusSelect: ElementFinder = element(by.css('select#avaliacao-status'));
  criticidadePainelSelect: ElementFinder = element(by.css('select#avaliacao-criticidadePainel'));
  nivelEficienciaGeralSelect: ElementFinder = element(by.css('select#avaliacao-nivelEficienciaGeral'));
  nivelEficienciaProcedimentoSelect: ElementFinder = element(by.css('select#avaliacao-nivelEficienciaProcedimento'));
  nivelEficienciaPessoaSelect: ElementFinder = element(by.css('select#avaliacao-nivelEficienciaPessoa'));
  nivelEficienciaProcessoSelect: ElementFinder = element(by.css('select#avaliacao-nivelEficienciaProcesso'));
  nivelEficienciaProdutoSelect: ElementFinder = element(by.css('select#avaliacao-nivelEficienciaProduto'));
  canceladoEmInput: ElementFinder = element(by.css('input#avaliacao-canceladoEm'));
  motivoCancelamentoInput: ElementFinder = element(by.css('input#avaliacao-motivoCancelamento'));
  userSelect: ElementFinder = element(by.css('select#avaliacao-user'));
  questionarioSelect: ElementFinder = element(by.css('select#avaliacao-questionario'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setIniciadaEmInput(iniciadaEm) {
    await this.iniciadaEmInput.sendKeys(iniciadaEm);
  }

  async getIniciadaEmInput() {
    return this.iniciadaEmInput.getAttribute('value');
  }

  async setLatitudeInicioAvaliacaoInput(latitudeInicioAvaliacao) {
    await this.latitudeInicioAvaliacaoInput.sendKeys(latitudeInicioAvaliacao);
  }

  async getLatitudeInicioAvaliacaoInput() {
    return this.latitudeInicioAvaliacaoInput.getAttribute('value');
  }

  async setLongitudeInicioAvaliacaoInput(longitudeInicioAvaliacao) {
    await this.longitudeInicioAvaliacaoInput.sendKeys(longitudeInicioAvaliacao);
  }

  async getLongitudeInicioAvaliacaoInput() {
    return this.longitudeInicioAvaliacaoInput.getAttribute('value');
  }

  async setNomeResponsavelLojaInput(nomeResponsavelLoja) {
    await this.nomeResponsavelLojaInput.sendKeys(nomeResponsavelLoja);
  }

  async getNomeResponsavelLojaInput() {
    return this.nomeResponsavelLojaInput.getAttribute('value');
  }

  async setProntuarioResponsavelLojaInput(prontuarioResponsavelLoja) {
    await this.prontuarioResponsavelLojaInput.sendKeys(prontuarioResponsavelLoja);
  }

  async getProntuarioResponsavelLojaInput() {
    return this.prontuarioResponsavelLojaInput.getAttribute('value');
  }

  async setSubmetidoEmInput(submetidoEm) {
    await this.submetidoEmInput.sendKeys(submetidoEm);
  }

  async getSubmetidoEmInput() {
    return this.submetidoEmInput.getAttribute('value');
  }

  async setLatitudeSubmissaoAvaliacaoInput(latitudeSubmissaoAvaliacao) {
    await this.latitudeSubmissaoAvaliacaoInput.sendKeys(latitudeSubmissaoAvaliacao);
  }

  async getLatitudeSubmissaoAvaliacaoInput() {
    return this.latitudeSubmissaoAvaliacaoInput.getAttribute('value');
  }

  async setLongitudeSubmissaoAvaliacaoInput(longitudeSubmissaoAvaliacao) {
    await this.longitudeSubmissaoAvaliacaoInput.sendKeys(longitudeSubmissaoAvaliacao);
  }

  async getLongitudeSubmissaoAvaliacaoInput() {
    return this.longitudeSubmissaoAvaliacaoInput.getAttribute('value');
  }

  async setObservacaoSubmissaoEnviadaForaDaLojaInput(observacaoSubmissaoEnviadaForaDaLoja) {
    await this.observacaoSubmissaoEnviadaForaDaLojaInput.sendKeys(observacaoSubmissaoEnviadaForaDaLoja);
  }

  async getObservacaoSubmissaoEnviadaForaDaLojaInput() {
    return this.observacaoSubmissaoEnviadaForaDaLojaInput.getAttribute('value');
  }

  async setStatusSelect(status) {
    await this.statusSelect.sendKeys(status);
  }

  async getStatusSelect() {
    return this.statusSelect.element(by.css('option:checked')).getText();
  }

  async statusSelectLastOption() {
    await this.statusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setCriticidadePainelSelect(criticidadePainel) {
    await this.criticidadePainelSelect.sendKeys(criticidadePainel);
  }

  async getCriticidadePainelSelect() {
    return this.criticidadePainelSelect.element(by.css('option:checked')).getText();
  }

  async criticidadePainelSelectLastOption() {
    await this.criticidadePainelSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setNivelEficienciaGeralSelect(nivelEficienciaGeral) {
    await this.nivelEficienciaGeralSelect.sendKeys(nivelEficienciaGeral);
  }

  async getNivelEficienciaGeralSelect() {
    return this.nivelEficienciaGeralSelect.element(by.css('option:checked')).getText();
  }

  async nivelEficienciaGeralSelectLastOption() {
    await this.nivelEficienciaGeralSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setNivelEficienciaProcedimentoSelect(nivelEficienciaProcedimento) {
    await this.nivelEficienciaProcedimentoSelect.sendKeys(nivelEficienciaProcedimento);
  }

  async getNivelEficienciaProcedimentoSelect() {
    return this.nivelEficienciaProcedimentoSelect.element(by.css('option:checked')).getText();
  }

  async nivelEficienciaProcedimentoSelectLastOption() {
    await this.nivelEficienciaProcedimentoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setNivelEficienciaPessoaSelect(nivelEficienciaPessoa) {
    await this.nivelEficienciaPessoaSelect.sendKeys(nivelEficienciaPessoa);
  }

  async getNivelEficienciaPessoaSelect() {
    return this.nivelEficienciaPessoaSelect.element(by.css('option:checked')).getText();
  }

  async nivelEficienciaPessoaSelectLastOption() {
    await this.nivelEficienciaPessoaSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setNivelEficienciaProcessoSelect(nivelEficienciaProcesso) {
    await this.nivelEficienciaProcessoSelect.sendKeys(nivelEficienciaProcesso);
  }

  async getNivelEficienciaProcessoSelect() {
    return this.nivelEficienciaProcessoSelect.element(by.css('option:checked')).getText();
  }

  async nivelEficienciaProcessoSelectLastOption() {
    await this.nivelEficienciaProcessoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setNivelEficienciaProdutoSelect(nivelEficienciaProduto) {
    await this.nivelEficienciaProdutoSelect.sendKeys(nivelEficienciaProduto);
  }

  async getNivelEficienciaProdutoSelect() {
    return this.nivelEficienciaProdutoSelect.element(by.css('option:checked')).getText();
  }

  async nivelEficienciaProdutoSelectLastOption() {
    await this.nivelEficienciaProdutoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setCanceladoEmInput(canceladoEm) {
    await this.canceladoEmInput.sendKeys(canceladoEm);
  }

  async getCanceladoEmInput() {
    return this.canceladoEmInput.getAttribute('value');
  }

  async setMotivoCancelamentoInput(motivoCancelamento) {
    await this.motivoCancelamentoInput.sendKeys(motivoCancelamento);
  }

  async getMotivoCancelamentoInput() {
    return this.motivoCancelamentoInput.getAttribute('value');
  }

  async userSelectLastOption() {
    await this.userSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async userSelectOption(option) {
    await this.userSelect.sendKeys(option);
  }

  getUserSelect() {
    return this.userSelect;
  }

  async getUserSelectedOption() {
    return this.userSelect.element(by.css('option:checked')).getText();
  }

  async questionarioSelectLastOption() {
    await this.questionarioSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async questionarioSelectOption(option) {
    await this.questionarioSelect.sendKeys(option);
  }

  getQuestionarioSelect() {
    return this.questionarioSelect;
  }

  async getQuestionarioSelectedOption() {
    return this.questionarioSelect.element(by.css('option:checked')).getText();
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }
}
