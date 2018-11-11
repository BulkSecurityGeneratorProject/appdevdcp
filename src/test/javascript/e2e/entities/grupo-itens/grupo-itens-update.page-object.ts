import { element, by, ElementFinder } from 'protractor';

export default class GrupoItensUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.grupoItens.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  nomeInput: ElementFinder = element(by.css('input#grupo-itens-nome'));
  criadoEmInput: ElementFinder = element(by.css('input#grupo-itens-criadoEm'));
  itensSelect: ElementFinder = element(by.css('select#grupo-itens-itens'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setNomeInput(nome) {
    await this.nomeInput.sendKeys(nome);
  }

  async getNomeInput() {
    return this.nomeInput.getAttribute('value');
  }

  async setCriadoEmInput(criadoEm) {
    await this.criadoEmInput.sendKeys(criadoEm);
  }

  async getCriadoEmInput() {
    return this.criadoEmInput.getAttribute('value');
  }

  async itensSelectLastOption() {
    await this.itensSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async itensSelectOption(option) {
    await this.itensSelect.sendKeys(option);
  }

  getItensSelect() {
    return this.itensSelect;
  }

  async getItensSelectedOption() {
    return this.itensSelect.element(by.css('option:checked')).getText();
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
