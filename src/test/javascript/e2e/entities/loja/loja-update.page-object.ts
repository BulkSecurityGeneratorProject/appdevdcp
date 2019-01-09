import { element, by, ElementFinder } from 'protractor';

export default class LojaUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.loja.home.editLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  nomeInput: ElementFinder = element(by.css('input#loja-nome'));
  enderecoInput: ElementFinder = element(by.css('input#loja-endereco'));
  cidadeInput: ElementFinder = element(by.css('input#loja-cidade'));
  cepInput: ElementFinder = element(by.css('input#loja-cep'));
  latitudeInput: ElementFinder = element(by.css('input#loja-latitude'));
  longitudeInput: ElementFinder = element(by.css('input#loja-longitude'));
  avaliadoresSelect: ElementFinder = element(by.css('select#loja-avaliadores'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setNomeInput(nome) {
    await this.nomeInput.sendKeys(nome);
  }

  async getNomeInput() {
    return this.nomeInput.getAttribute('value');
  }

  async setEnderecoInput(endereco) {
    await this.enderecoInput.sendKeys(endereco);
  }

  async getEnderecoInput() {
    return this.enderecoInput.getAttribute('value');
  }

  async setCidadeInput(cidade) {
    await this.cidadeInput.sendKeys(cidade);
  }

  async getCidadeInput() {
    return this.cidadeInput.getAttribute('value');
  }

  async setCepInput(cep) {
    await this.cepInput.sendKeys(cep);
  }

  async getCepInput() {
    return this.cepInput.getAttribute('value');
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

  async avaliadoresSelectLastOption() {
    await this.avaliadoresSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async avaliadoresSelectOption(option) {
    await this.avaliadoresSelect.sendKeys(option);
  }

  getAvaliadoresSelect() {
    return this.avaliadoresSelect;
  }

  async getAvaliadoresSelectedOption() {
    return this.avaliadoresSelect.element(by.css('option:checked')).getText();
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
