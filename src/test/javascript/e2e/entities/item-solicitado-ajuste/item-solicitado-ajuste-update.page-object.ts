import { element, by, ElementFinder } from 'protractor';

export default class ItemSolicitadoAjusteUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.itemSolicitadoAjuste.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  respondidoEmInput: ElementFinder = element(by.css('input#item-solicitado-ajuste-respondidoEm'));
  ultimaAtualizacaoEmInput: ElementFinder = element(by.css('input#item-solicitado-ajuste-ultimaAtualizacaoEm'));
  departamentoInput: ElementFinder = element(by.css('input#item-solicitado-ajuste-departamento'));
  codigoSapInput: ElementFinder = element(by.css('input#item-solicitado-ajuste-codigoSap'));
  descricaoItemInput: ElementFinder = element(by.css('input#item-solicitado-ajuste-descricaoItem'));
  saldoSapInput: ElementFinder = element(by.css('input#item-solicitado-ajuste-saldoSap'));
  saldoFisicoInput: ElementFinder = element(by.css('input#item-solicitado-ajuste-saldoFisico'));
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

  async setDepartamentoInput(departamento) {
    await this.departamentoInput.sendKeys(departamento);
  }

  async getDepartamentoInput() {
    return this.departamentoInput.getAttribute('value');
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

  async setSaldoSapInput(saldoSap) {
    await this.saldoSapInput.sendKeys(saldoSap);
  }

  async getSaldoSapInput() {
    return this.saldoSapInput.getAttribute('value');
  }

  async setSaldoFisicoInput(saldoFisico) {
    await this.saldoFisicoInput.sendKeys(saldoFisico);
  }

  async getSaldoFisicoInput() {
    return this.saldoFisicoInput.getAttribute('value');
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
