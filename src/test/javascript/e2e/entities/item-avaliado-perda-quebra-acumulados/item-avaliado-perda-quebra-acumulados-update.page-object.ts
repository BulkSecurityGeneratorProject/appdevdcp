import { element, by, ElementFinder } from 'protractor';

export default class ItemAvaliadoPerdaQuebraAcumuladosUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  tipoSelect: ElementFinder = element(by.css('select#item-avaliado-perda-quebra-acumulados-tipo'));
  respondidoEmInput: ElementFinder = element(by.css('input#item-avaliado-perda-quebra-acumulados-respondidoEm'));
  ultimaAtualizacaoEmInput: ElementFinder = element(by.css('input#item-avaliado-perda-quebra-acumulados-ultimaAtualizacaoEm'));
  percentualInput: ElementFinder = element(by.css('input#item-avaliado-perda-quebra-acumulados-percentual'));
  financeiroInput: ElementFinder = element(by.css('input#item-avaliado-perda-quebra-acumulados-financeiro'));
  pontuacaoInput: ElementFinder = element(by.css('input#item-avaliado-perda-quebra-acumulados-pontuacao'));
  latitudeLocalRespostaInput: ElementFinder = element(by.css('input#item-avaliado-perda-quebra-acumulados-latitudeLocalResposta'));
  longitudeLocalRespostaInput: ElementFinder = element(by.css('input#item-avaliado-perda-quebra-acumulados-longitudeLocalResposta'));
  classificacaoSelect: ElementFinder = element(by.css('select#item-avaliado-perda-quebra-acumulados-classificacao'));
  categorizacaoSelect: ElementFinder = element(by.css('select#item-avaliado-perda-quebra-acumulados-categorizacao'));
  avaliacaoSelect: ElementFinder = element(by.css('select#item-avaliado-perda-quebra-acumulados-avaliacao'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setTipoSelect(tipo) {
    await this.tipoSelect.sendKeys(tipo);
  }

  async getTipoSelect() {
    return this.tipoSelect.element(by.css('option:checked')).getText();
  }

  async tipoSelectLastOption() {
    await this.tipoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setRespondidoEmInput(respondidoEm) {
    await this.respondidoEmInput.sendKeys(respondidoEm);
  }

  async getRespondidoEmInput() {
    return this.respondidoEmInput.getAttribute('value');
  }

  async setUltimaAtualizacaoEmInput(ultimaAtualizacaoEm) {
    await this.ultimaAtualizacaoEmInput.sendKeys(ultimaAtualizacaoEm);
  }

  async getUltimaAtualizacaoEmInput() {
    return this.ultimaAtualizacaoEmInput.getAttribute('value');
  }

  async setPercentualInput(percentual) {
    await this.percentualInput.sendKeys(percentual);
  }

  async getPercentualInput() {
    return this.percentualInput.getAttribute('value');
  }

  async setFinanceiroInput(financeiro) {
    await this.financeiroInput.sendKeys(financeiro);
  }

  async getFinanceiroInput() {
    return this.financeiroInput.getAttribute('value');
  }

  async setPontuacaoInput(pontuacao) {
    await this.pontuacaoInput.sendKeys(pontuacao);
  }

  async getPontuacaoInput() {
    return this.pontuacaoInput.getAttribute('value');
  }

  async setLatitudeLocalRespostaInput(latitudeLocalResposta) {
    await this.latitudeLocalRespostaInput.sendKeys(latitudeLocalResposta);
  }

  async getLatitudeLocalRespostaInput() {
    return this.latitudeLocalRespostaInput.getAttribute('value');
  }

  async setLongitudeLocalRespostaInput(longitudeLocalResposta) {
    await this.longitudeLocalRespostaInput.sendKeys(longitudeLocalResposta);
  }

  async getLongitudeLocalRespostaInput() {
    return this.longitudeLocalRespostaInput.getAttribute('value');
  }

  async setClassificacaoSelect(classificacao) {
    await this.classificacaoSelect.sendKeys(classificacao);
  }

  async getClassificacaoSelect() {
    return this.classificacaoSelect.element(by.css('option:checked')).getText();
  }

  async classificacaoSelectLastOption() {
    await this.classificacaoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setCategorizacaoSelect(categorizacao) {
    await this.categorizacaoSelect.sendKeys(categorizacao);
  }

  async getCategorizacaoSelect() {
    return this.categorizacaoSelect.element(by.css('option:checked')).getText();
  }

  async categorizacaoSelectLastOption() {
    await this.categorizacaoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async avaliacaoSelectLastOption() {
    await this.avaliacaoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async avaliacaoSelectOption(option) {
    await this.avaliacaoSelect.sendKeys(option);
  }

  getAvaliacaoSelect() {
    return this.avaliacaoSelect;
  }

  async getAvaliacaoSelectedOption() {
    return this.avaliacaoSelect.element(by.css('option:checked')).getText();
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
