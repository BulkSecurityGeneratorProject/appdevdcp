import { element, by, ElementFinder } from 'protractor';

export default class ItemSolicitadoAjusteUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.itemSolicitadoAjuste.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  respondidoEmInput: ElementFinder = element(by.css('input#item-solicitado-ajuste-respondidoEm'));
  ultimaAtualizacaoEmInput: ElementFinder = element(by.css('input#item-solicitado-ajuste-ultimaAtualizacaoEm'));
  codigoDepartamentoInput: ElementFinder = element(by.css('input#item-solicitado-ajuste-codigoDepartamento'));
  codigoSapInput: ElementFinder = element(by.css('input#item-solicitado-ajuste-codigoSap'));
  descricaoItemInput: ElementFinder = element(by.css('input#item-solicitado-ajuste-descricaoItem'));
  saldoSap0001Input: ElementFinder = element(by.css('input#item-solicitado-ajuste-saldoSap0001'));
  saldoFisico0001Input: ElementFinder = element(by.css('input#item-solicitado-ajuste-saldoFisico0001'));
  saldoSap9000Input: ElementFinder = element(by.css('input#item-solicitado-ajuste-saldoSap9000'));
  saldoFisico9000Input: ElementFinder = element(by.css('input#item-solicitado-ajuste-saldoFisico9000'));
  motivoDivergenciaInput: ElementFinder = element(by.css('input#item-solicitado-ajuste-motivoDivergencia'));
  acaoCorretivaOuPreventivaInput: ElementFinder = element(by.css('input#item-solicitado-ajuste-acaoCorretivaOuPreventiva'));
  responsavelInput: ElementFinder = element(by.css('input#item-solicitado-ajuste-responsavel'));
  avaliacaoSelect: ElementFinder = element(by.css('select#item-solicitado-ajuste-avaliacao'));

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

  async setCodigoDepartamentoInput(codigoDepartamento) {
    await this.codigoDepartamentoInput.sendKeys(codigoDepartamento);
  }

  async getCodigoDepartamentoInput() {
    return this.codigoDepartamentoInput.getAttribute('value');
  }

  async setCodigoSapInput(codigoSap) {
    await this.codigoSapInput.sendKeys(codigoSap);
  }

  async getCodigoSapInput() {
    return this.codigoSapInput.getAttribute('value');
  }

  async setDescricaoItemInput(descricaoItem) {
    await this.descricaoItemInput.sendKeys(descricaoItem);
  }

  async getDescricaoItemInput() {
    return this.descricaoItemInput.getAttribute('value');
  }

  async setSaldoSap0001Input(saldoSap0001) {
    await this.saldoSap0001Input.sendKeys(saldoSap0001);
  }

  async getSaldoSap0001Input() {
    return this.saldoSap0001Input.getAttribute('value');
  }

  async setSaldoFisico0001Input(saldoFisico0001) {
    await this.saldoFisico0001Input.sendKeys(saldoFisico0001);
  }

  async getSaldoFisico0001Input() {
    return this.saldoFisico0001Input.getAttribute('value');
  }

  async setSaldoSap9000Input(saldoSap9000) {
    await this.saldoSap9000Input.sendKeys(saldoSap9000);
  }

  async getSaldoSap9000Input() {
    return this.saldoSap9000Input.getAttribute('value');
  }

  async setSaldoFisico9000Input(saldoFisico9000) {
    await this.saldoFisico9000Input.sendKeys(saldoFisico9000);
  }

  async getSaldoFisico9000Input() {
    return this.saldoFisico9000Input.getAttribute('value');
  }

  async setMotivoDivergenciaInput(motivoDivergencia) {
    await this.motivoDivergenciaInput.sendKeys(motivoDivergencia);
  }

  async getMotivoDivergenciaInput() {
    return this.motivoDivergenciaInput.getAttribute('value');
  }

  async setAcaoCorretivaOuPreventivaInput(acaoCorretivaOuPreventiva) {
    await this.acaoCorretivaOuPreventivaInput.sendKeys(acaoCorretivaOuPreventiva);
  }

  async getAcaoCorretivaOuPreventivaInput() {
    return this.acaoCorretivaOuPreventivaInput.getAttribute('value');
  }

  async setResponsavelInput(responsavel) {
    await this.responsavelInput.sendKeys(responsavel);
  }

  async getResponsavelInput() {
    return this.responsavelInput.getAttribute('value');
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
