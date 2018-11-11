import { element, by, ElementFinder } from 'protractor';

export default class QuestionarioUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.questionario.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  nomeInput: ElementFinder = element(by.css('input#questionario-nome'));
  descricaoInput: ElementFinder = element(by.css('input#questionario-descricao'));
  ativoInput: ElementFinder = element(by.css('input#questionario-ativo'));
  criadoEmInput: ElementFinder = element(by.css('input#questionario-criadoEm'));
  grupoSelect: ElementFinder = element(by.css('select#questionario-grupo'));

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
  async setCriadoEmInput(criadoEm) {
    await this.criadoEmInput.sendKeys(criadoEm);
  }

  async getCriadoEmInput() {
    return this.criadoEmInput.getAttribute('value');
  }

  async grupoSelectLastOption() {
    await this.grupoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async grupoSelectOption(option) {
    await this.grupoSelect.sendKeys(option);
  }

  getGrupoSelect() {
    return this.grupoSelect;
  }

  async getGrupoSelectedOption() {
    return this.grupoSelect.element(by.css('option:checked')).getText();
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
