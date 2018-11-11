import { element, by, ElementFinder } from 'protractor';

export default class ItemAvaliadoUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.itemAvaliado.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  respondidoEmInput: ElementFinder = element(by.css('input#item-avaliado-respondidoEm'));
  ultimaAtualizacaoEmInput: ElementFinder = element(by.css('input#item-avaliado-ultimaAtualizacaoEm'));
  statusSelect: ElementFinder = element(by.css('select#item-avaliado-status'));
  observacoesInput: ElementFinder = element(by.css('input#item-avaliado-observacoes'));
  latitudeLocalRespostaInput: ElementFinder = element(by.css('input#item-avaliado-latitudeLocalResposta'));
  longitudeLocalRespostaInput: ElementFinder = element(by.css('input#item-avaliado-longitudeLocalResposta'));
  avaliacaoSelect: ElementFinder = element(by.css('select#item-avaliado-avaliacao'));
  itemAvaliacaoSelect: ElementFinder = element(by.css('select#item-avaliado-itemAvaliacao'));

  getPageTitle() {
    return this.pageTitle;
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
  async setObservacoesInput(observacoes) {
    await this.observacoesInput.sendKeys(observacoes);
  }

  async getObservacoesInput() {
    return this.observacoesInput.getAttribute('value');
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

  async itemAvaliacaoSelectLastOption() {
    await this.itemAvaliacaoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async itemAvaliacaoSelectOption(option) {
    await this.itemAvaliacaoSelect.sendKeys(option);
  }

  getItemAvaliacaoSelect() {
    return this.itemAvaliacaoSelect;
  }

  async getItemAvaliacaoSelectedOption() {
    return this.itemAvaliacaoSelect.element(by.css('option:checked')).getText();
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
