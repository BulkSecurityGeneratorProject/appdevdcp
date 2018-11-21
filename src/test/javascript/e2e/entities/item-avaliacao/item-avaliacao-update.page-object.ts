import { element, by, ElementFinder } from 'protractor';

export default class ItemAvaliacaoUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.itemAvaliacao.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  descricaoInput: ElementFinder = element(by.css('input#item-avaliacao-descricao'));
  anexoObrigatorioInput: ElementFinder = element(by.css('input#item-avaliacao-anexoObrigatorio'));
  criadoEmInput: ElementFinder = element(by.css('input#item-avaliacao-criadoEm'));
  pontosProcedimentoInput: ElementFinder = element(by.css('input#item-avaliacao-pontosProcedimento'));
  pontosPessoaInput: ElementFinder = element(by.css('input#item-avaliacao-pontosPessoa'));
  pontosProcessoInput: ElementFinder = element(by.css('input#item-avaliacao-pontosProcesso'));
  pontosProdutoInput: ElementFinder = element(by.css('input#item-avaliacao-pontosProduto'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setDescricaoInput(descricao) {
    await this.descricaoInput.sendKeys(descricao);
  }

  async getDescricaoInput() {
    return this.descricaoInput.getAttribute('value');
  }

  getAnexoObrigatorioInput() {
    return this.anexoObrigatorioInput;
  }
  async setCriadoEmInput(criadoEm) {
    await this.criadoEmInput.sendKeys(criadoEm);
  }

  async getCriadoEmInput() {
    return this.criadoEmInput.getAttribute('value');
  }

  async setPontosProcedimentoInput(pontosProcedimento) {
    await this.pontosProcedimentoInput.sendKeys(pontosProcedimento);
  }

  async getPontosProcedimentoInput() {
    return this.pontosProcedimentoInput.getAttribute('value');
  }

  async setPontosPessoaInput(pontosPessoa) {
    await this.pontosPessoaInput.sendKeys(pontosPessoa);
  }

  async getPontosPessoaInput() {
    return this.pontosPessoaInput.getAttribute('value');
  }

  async setPontosProcessoInput(pontosProcesso) {
    await this.pontosProcessoInput.sendKeys(pontosProcesso);
  }

  async getPontosProcessoInput() {
    return this.pontosProcessoInput.getAttribute('value');
  }

  async setPontosProdutoInput(pontosProduto) {
    await this.pontosProdutoInput.sendKeys(pontosProduto);
  }

  async getPontosProdutoInput() {
    return this.pontosProdutoInput.getAttribute('value');
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
