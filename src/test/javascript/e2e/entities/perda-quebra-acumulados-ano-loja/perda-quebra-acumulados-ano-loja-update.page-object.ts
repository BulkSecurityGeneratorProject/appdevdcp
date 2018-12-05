import { element, by, ElementFinder } from 'protractor';

export default class PerdaQuebraAcumuladosAnoLojaUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  anoInput: ElementFinder = element(by.css('input#perda-quebra-acumulados-ano-loja-ano'));
  percentualPerdaInput: ElementFinder = element(by.css('input#perda-quebra-acumulados-ano-loja-percentualPerda'));
  financeiroPerdaInput: ElementFinder = element(by.css('input#perda-quebra-acumulados-ano-loja-financeiroPerda'));
  pontuacaoPerdaInput: ElementFinder = element(by.css('input#perda-quebra-acumulados-ano-loja-pontuacaoPerda'));
  statusPerdaSelect: ElementFinder = element(by.css('select#perda-quebra-acumulados-ano-loja-statusPerda'));
  categorizacaoPerdaSelect: ElementFinder = element(by.css('select#perda-quebra-acumulados-ano-loja-categorizacaoPerda'));
  percentualQuebraInput: ElementFinder = element(by.css('input#perda-quebra-acumulados-ano-loja-percentualQuebra'));
  financeiroQuebraInput: ElementFinder = element(by.css('input#perda-quebra-acumulados-ano-loja-financeiroQuebra'));
  pontuacaoQuebraInput: ElementFinder = element(by.css('input#perda-quebra-acumulados-ano-loja-pontuacaoQuebra'));
  statusQuebraSelect: ElementFinder = element(by.css('select#perda-quebra-acumulados-ano-loja-statusQuebra'));
  categorizacaoQuebraSelect: ElementFinder = element(by.css('select#perda-quebra-acumulados-ano-loja-categorizacaoQuebra'));
  lojaSelect: ElementFinder = element(by.css('select#perda-quebra-acumulados-ano-loja-loja'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setAnoInput(ano) {
    await this.anoInput.sendKeys(ano);
  }

  async getAnoInput() {
    return this.anoInput.getAttribute('value');
  }

  async setPercentualPerdaInput(percentualPerda) {
    await this.percentualPerdaInput.sendKeys(percentualPerda);
  }

  async getPercentualPerdaInput() {
    return this.percentualPerdaInput.getAttribute('value');
  }

  async setFinanceiroPerdaInput(financeiroPerda) {
    await this.financeiroPerdaInput.sendKeys(financeiroPerda);
  }

  async getFinanceiroPerdaInput() {
    return this.financeiroPerdaInput.getAttribute('value');
  }

  async setPontuacaoPerdaInput(pontuacaoPerda) {
    await this.pontuacaoPerdaInput.sendKeys(pontuacaoPerda);
  }

  async getPontuacaoPerdaInput() {
    return this.pontuacaoPerdaInput.getAttribute('value');
  }

  async setStatusPerdaSelect(statusPerda) {
    await this.statusPerdaSelect.sendKeys(statusPerda);
  }

  async getStatusPerdaSelect() {
    return this.statusPerdaSelect.element(by.css('option:checked')).getText();
  }

  async statusPerdaSelectLastOption() {
    await this.statusPerdaSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setCategorizacaoPerdaSelect(categorizacaoPerda) {
    await this.categorizacaoPerdaSelect.sendKeys(categorizacaoPerda);
  }

  async getCategorizacaoPerdaSelect() {
    return this.categorizacaoPerdaSelect.element(by.css('option:checked')).getText();
  }

  async categorizacaoPerdaSelectLastOption() {
    await this.categorizacaoPerdaSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setPercentualQuebraInput(percentualQuebra) {
    await this.percentualQuebraInput.sendKeys(percentualQuebra);
  }

  async getPercentualQuebraInput() {
    return this.percentualQuebraInput.getAttribute('value');
  }

  async setFinanceiroQuebraInput(financeiroQuebra) {
    await this.financeiroQuebraInput.sendKeys(financeiroQuebra);
  }

  async getFinanceiroQuebraInput() {
    return this.financeiroQuebraInput.getAttribute('value');
  }

  async setPontuacaoQuebraInput(pontuacaoQuebra) {
    await this.pontuacaoQuebraInput.sendKeys(pontuacaoQuebra);
  }

  async getPontuacaoQuebraInput() {
    return this.pontuacaoQuebraInput.getAttribute('value');
  }

  async setStatusQuebraSelect(statusQuebra) {
    await this.statusQuebraSelect.sendKeys(statusQuebra);
  }

  async getStatusQuebraSelect() {
    return this.statusQuebraSelect.element(by.css('option:checked')).getText();
  }

  async statusQuebraSelectLastOption() {
    await this.statusQuebraSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setCategorizacaoQuebraSelect(categorizacaoQuebra) {
    await this.categorizacaoQuebraSelect.sendKeys(categorizacaoQuebra);
  }

  async getCategorizacaoQuebraSelect() {
    return this.categorizacaoQuebraSelect.element(by.css('option:checked')).getText();
  }

  async categorizacaoQuebraSelectLastOption() {
    await this.categorizacaoQuebraSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async lojaSelectLastOption() {
    await this.lojaSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async lojaSelectOption(option) {
    await this.lojaSelect.sendKeys(option);
  }

  getLojaSelect() {
    return this.lojaSelect;
  }

  async getLojaSelectedOption() {
    return this.lojaSelect.element(by.css('option:checked')).getText();
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
