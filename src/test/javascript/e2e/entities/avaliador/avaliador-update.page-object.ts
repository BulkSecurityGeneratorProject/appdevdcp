import { element, by, ElementFinder } from 'protractor';

export default class AvaliadorUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.avaliador.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  nomeInput: ElementFinder = element(by.css('input#avaliador-nome'));
  loginInput: ElementFinder = element(by.css('input#avaliador-login'));
  prontuarioInput: ElementFinder = element(by.css('input#avaliador-prontuario'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setNomeInput(nome) {
    await this.nomeInput.sendKeys(nome);
  }

  async getNomeInput() {
    return this.nomeInput.getAttribute('value');
  }

  async setLoginInput(login) {
    await this.loginInput.sendKeys(login);
  }

  async getLoginInput() {
    return this.loginInput.getAttribute('value');
  }

  async setProntuarioInput(prontuario) {
    await this.prontuarioInput.sendKeys(prontuario);
  }

  async getProntuarioInput() {
    return this.prontuarioInput.getAttribute('value');
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
