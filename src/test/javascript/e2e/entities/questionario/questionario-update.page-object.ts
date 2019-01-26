import { element, by, ElementFinder } from 'protractor';

export default class QuestionarioUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.questionario.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  nomeInput: ElementFinder = element(by.css('input#questionario-nome'));
  descricaoInput: ElementFinder = element(by.css('input#questionario-descricao'));
  ativoInput: ElementFinder = element(by.css('input#questionario-ativo'));
  versaoInput: ElementFinder = element(by.css('input#questionario-versao'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setNomeInput(nome) {
    await this.nomeInput.sendKeys(nome);
  }

  async getNomeInput() {
    return this.nomeInput.getAttribute('value');
  }

  async setDescricaoInput(descricao) {
    await this.descricaoInput.sendKeys(descricao);
  }

  async getDescricaoInput() {
    return this.descricaoInput.getAttribute('value');
  }

  getAtivoInput() {
    return this.ativoInput;
  }
  async setVersaoInput(versao) {
    await this.versaoInput.sendKeys(versao);
  }

  async getVersaoInput() {
    return this.versaoInput.getAttribute('value');
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
