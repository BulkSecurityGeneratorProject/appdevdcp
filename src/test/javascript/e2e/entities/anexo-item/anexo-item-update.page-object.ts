import { element, by, ElementFinder } from 'protractor';

export default class AnexoItemUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.anexoItem.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  tipoSelect: ElementFinder = element(by.css('select#anexo-item-tipo'));
  caminhoArquivoInput: ElementFinder = element(by.css('input#anexo-item-caminhoArquivo'));
  itemAvaliadoSelect: ElementFinder = element(by.css('select#anexo-item-itemAvaliado'));

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
  async setCaminhoArquivoInput(caminhoArquivo) {
    await this.caminhoArquivoInput.sendKeys(caminhoArquivo);
  }

  async getCaminhoArquivoInput() {
    return this.caminhoArquivoInput.getAttribute('value');
  }

  async itemAvaliadoSelectLastOption() {
    await this.itemAvaliadoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async itemAvaliadoSelectOption(option) {
    await this.itemAvaliadoSelect.sendKeys(option);
  }

  getItemAvaliadoSelect() {
    return this.itemAvaliadoSelect;
  }

  async getItemAvaliadoSelectedOption() {
    return this.itemAvaliadoSelect.element(by.css('option:checked')).getText();
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
