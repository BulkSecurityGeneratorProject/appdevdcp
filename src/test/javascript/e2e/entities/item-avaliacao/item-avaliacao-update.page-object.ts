import { element, by, ElementFinder } from 'protractor';

export default class ItemAvaliacaoUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.itemAvaliacao.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  descricaoInput: ElementFinder = element(by.css('input#item-avaliacao-descricao'));
  anexoObrigatorioInput: ElementFinder = element(by.css('input#item-avaliacao-anexoObrigatorio'));
  pontosProcedimentoInput: ElementFinder = element(by.css('input#item-avaliacao-pontosProcedimento'));
  pontosPessoaInput: ElementFinder = element(by.css('input#item-avaliacao-pontosPessoa'));
  pontosProcessoInput: ElementFinder = element(by.css('input#item-avaliacao-pontosProcesso'));
  pontosProdutoInput: ElementFinder = element(by.css('input#item-avaliacao-pontosProduto'));
  ordemExibicaoInput: ElementFinder = element(by.css('input#item-avaliacao-ordemExibicao'));
  grupoSelect: ElementFinder = element(by.css('select#item-avaliacao-grupo'));

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

  async setOrdemExibicaoInput(ordemExibicao) {
    await this.ordemExibicaoInput.sendKeys(ordemExibicao);
  }

  async getOrdemExibicaoInput() {
    return this.ordemExibicaoInput.getAttribute('value');
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
