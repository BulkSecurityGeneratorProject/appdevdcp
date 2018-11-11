import { element, by, ElementFinder } from 'protractor';

export default class LojaUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.loja.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  nomeInput: ElementFinder = element(by.css('input#loja-nome'));
  nomeResponsavelInput: ElementFinder = element(by.css('input#loja-nomeResponsavel'));
  prontuarioResponsavelInput: ElementFinder = element(by.css('input#loja-prontuarioResponsavel'));
  latitudeInput: ElementFinder = element(by.css('input#loja-latitude'));
  longitudeInput: ElementFinder = element(by.css('input#loja-longitude'));
  avaliadorSelect: ElementFinder = element(by.css('select#loja-avaliador'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setNomeInput(nome) {
    await this.nomeInput.sendKeys(nome);
  }

  async getNomeInput() {
    return this.nomeInput.getAttribute('value');
  }

  async setNomeResponsavelInput(nomeResponsavel) {
    await this.nomeResponsavelInput.sendKeys(nomeResponsavel);
  }

  async getNomeResponsavelInput() {
    return this.nomeResponsavelInput.getAttribute('value');
  }

  async setProntuarioResponsavelInput(prontuarioResponsavel) {
    await this.prontuarioResponsavelInput.sendKeys(prontuarioResponsavel);
  }

  async getProntuarioResponsavelInput() {
    return this.prontuarioResponsavelInput.getAttribute('value');
  }

  async setLatitudeInput(latitude) {
    await this.latitudeInput.sendKeys(latitude);
  }

  async getLatitudeInput() {
    return this.latitudeInput.getAttribute('value');
  }

  async setLongitudeInput(longitude) {
    await this.longitudeInput.sendKeys(longitude);
  }

  async getLongitudeInput() {
    return this.longitudeInput.getAttribute('value');
  }

  async avaliadorSelectLastOption() {
    await this.avaliadorSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async avaliadorSelectOption(option) {
    await this.avaliadorSelect.sendKeys(option);
  }

  getAvaliadorSelect() {
    return this.avaliadorSelect;
  }

  async getAvaliadorSelectedOption() {
    return this.avaliadorSelect.element(by.css('option:checked')).getText();
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
