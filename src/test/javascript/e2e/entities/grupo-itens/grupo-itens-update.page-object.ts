import { element, by, ElementFinder } from 'protractor';

export default class GrupoItensUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.grupoItens.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  nomeInput: ElementFinder = element(by.css('input#grupo-itens-nome'));
  ordemExibicaoInput: ElementFinder = element(by.css('input#grupo-itens-ordemExibicao'));
  questionarioSelect: ElementFinder = element(by.css('select#grupo-itens-questionario'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setNomeInput(nome) {
    await this.nomeInput.sendKeys(nome);
  }

  async getNomeInput() {
    return this.nomeInput.getAttribute('value');
  }

  async setOrdemExibicaoInput(ordemExibicao) {
    await this.ordemExibicaoInput.sendKeys(ordemExibicao);
  }

  async getOrdemExibicaoInput() {
    return this.ordemExibicaoInput.getAttribute('value');
  }

  async questionarioSelectLastOption() {
    await this.questionarioSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async questionarioSelectOption(option) {
    await this.questionarioSelect.sendKeys(option);
  }

  getQuestionarioSelect() {
    return this.questionarioSelect;
  }

  async getQuestionarioSelectedOption() {
    return this.questionarioSelect.element(by.css('option:checked')).getText();
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
