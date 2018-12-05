import { element, by, ElementFinder } from 'protractor';

export default class AvaliacaoUpdatePage {
  pageTitle: ElementFinder = element(by.id('dcpdesconformidadesApp.avaliacao.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  iniciadaEmInput: ElementFinder = element(by.css('input#avaliacao-iniciadaEm'));
  latitudeInicioAvaliacaoInput: ElementFinder = element(by.css('input#avaliacao-latitudeInicioAvaliacao'));
  longitudeInicioAvaliacaoInput: ElementFinder = element(by.css('input#avaliacao-longitudeInicioAvaliacao'));
  nomeResponsavelLojaInput: ElementFinder = element(by.css('input#avaliacao-nomeResponsavelLoja'));
  prontuarioResponsavelLojaInput: ElementFinder = element(by.css('input#avaliacao-prontuarioResponsavelLoja'));
  submetidoEmInput: ElementFinder = element(by.css('input#avaliacao-submetidoEm'));
  latitudeSubmissaoAvaliacaoInput: ElementFinder = element(by.css('input#avaliacao-latitudeSubmissaoAvaliacao'));
  longitudeSubmissaoAvaliacaoInput: ElementFinder = element(by.css('input#avaliacao-longitudeSubmissaoAvaliacao'));
  observacaoSubmissaoEnviadaForaDaLojaInput: ElementFinder = element(by.css('input#avaliacao-observacaoSubmissaoEnviadaForaDaLoja'));
  statusSelect: ElementFinder = element(by.css('select#avaliacao-status'));
  criticidadePainelSelect: ElementFinder = element(by.css('select#avaliacao-criticidadePainel'));
  nivelEficienciaGeralSelect: ElementFinder = element(by.css('select#avaliacao-nivelEficienciaGeral'));
  nivelEficienciaProcedimentoSelect: ElementFinder = element(by.css('select#avaliacao-nivelEficienciaProcedimento'));
  nivelEficienciaPessoaSelect: ElementFinder = element(by.css('select#avaliacao-nivelEficienciaPessoa'));
  nivelEficienciaProcessoSelect: ElementFinder = element(by.css('select#avaliacao-nivelEficienciaProcesso'));
  nivelEficienciaProdutoSelect: ElementFinder = element(by.css('select#avaliacao-nivelEficienciaProduto'));
  canceladoEmInput: ElementFinder = element(by.css('input#avaliacao-canceladoEm'));
  motivoCancelamentoInput: ElementFinder = element(by.css('input#avaliacao-motivoCancelamento'));
  percentualPerdaInput: ElementFinder = element(by.css('input#avaliacao-percentualPerda'));
  financeiroPerdaInput: ElementFinder = element(by.css('input#avaliacao-financeiroPerda'));
  pontuacaoPerdaInput: ElementFinder = element(by.css('input#avaliacao-pontuacaoPerda'));
  statusPerdaSelect: ElementFinder = element(by.css('select#avaliacao-statusPerda'));
  categorizacaoPerdaSelect: ElementFinder = element(by.css('select#avaliacao-categorizacaoPerda'));
  percentualQuebraInput: ElementFinder = element(by.css('input#avaliacao-percentualQuebra'));
  financeiroQuebraInput: ElementFinder = element(by.css('input#avaliacao-financeiroQuebra'));
  pontuacaoQuebraInput: ElementFinder = element(by.css('input#avaliacao-pontuacaoQuebra'));
  statusQuebraSelect: ElementFinder = element(by.css('select#avaliacao-statusQuebra'));
  categorizacaoQuebraSelect: ElementFinder = element(by.css('select#avaliacao-categorizacaoQuebra'));
  importadoViaPlanilhaInput: ElementFinder = element(by.css('input#avaliacao-importadoViaPlanilha'));
  caminhoArquivoPlanilhaInput: ElementFinder = element(by.css('input#avaliacao-caminhoArquivoPlanilha'));
  avaliadorSelect: ElementFinder = element(by.css('select#avaliacao-avaliador'));
  questionarioSelect: ElementFinder = element(by.css('select#avaliacao-questionario'));
  lojaSelect: ElementFinder = element(by.css('select#avaliacao-loja'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setIniciadaEmInput(iniciadaEm) {
    await this.iniciadaEmInput.sendKeys(iniciadaEm);
  }

  async getIniciadaEmInput() {
    return this.iniciadaEmInput.getAttribute('value');
  }

  async setLatitudeInicioAvaliacaoInput(latitudeInicioAvaliacao) {
    await this.latitudeInicioAvaliacaoInput.sendKeys(latitudeInicioAvaliacao);
  }

  async getLatitudeInicioAvaliacaoInput() {
    return this.latitudeInicioAvaliacaoInput.getAttribute('value');
  }

  async setLongitudeInicioAvaliacaoInput(longitudeInicioAvaliacao) {
    await this.longitudeInicioAvaliacaoInput.sendKeys(longitudeInicioAvaliacao);
  }

  async getLongitudeInicioAvaliacaoInput() {
    return this.longitudeInicioAvaliacaoInput.getAttribute('value');
  }

  async setNomeResponsavelLojaInput(nomeResponsavelLoja) {
    await this.nomeResponsavelLojaInput.sendKeys(nomeResponsavelLoja);
  }

  async getNomeResponsavelLojaInput() {
    return this.nomeResponsavelLojaInput.getAttribute('value');
  }

  async setProntuarioResponsavelLojaInput(prontuarioResponsavelLoja) {
    await this.prontuarioResponsavelLojaInput.sendKeys(prontuarioResponsavelLoja);
  }

  async getProntuarioResponsavelLojaInput() {
    return this.prontuarioResponsavelLojaInput.getAttribute('value');
  }

  async setSubmetidoEmInput(submetidoEm) {
    await this.submetidoEmInput.sendKeys(submetidoEm);
  }

  async getSubmetidoEmInput() {
    return this.submetidoEmInput.getAttribute('value');
  }

  async setLatitudeSubmissaoAvaliacaoInput(latitudeSubmissaoAvaliacao) {
    await this.latitudeSubmissaoAvaliacaoInput.sendKeys(latitudeSubmissaoAvaliacao);
  }

  async getLatitudeSubmissaoAvaliacaoInput() {
    return this.latitudeSubmissaoAvaliacaoInput.getAttribute('value');
  }

  async setLongitudeSubmissaoAvaliacaoInput(longitudeSubmissaoAvaliacao) {
    await this.longitudeSubmissaoAvaliacaoInput.sendKeys(longitudeSubmissaoAvaliacao);
  }

  async getLongitudeSubmissaoAvaliacaoInput() {
    return this.longitudeSubmissaoAvaliacaoInput.getAttribute('value');
  }

  async setObservacaoSubmissaoEnviadaForaDaLojaInput(observacaoSubmissaoEnviadaForaDaLoja) {
    await this.observacaoSubmissaoEnviadaForaDaLojaInput.sendKeys(observacaoSubmissaoEnviadaForaDaLoja);
  }

  async getObservacaoSubmissaoEnviadaForaDaLojaInput() {
    return this.observacaoSubmissaoEnviadaForaDaLojaInput.getAttribute('value');
  }

  async setStatusSelect(status) {
    await this.statusSelect.sendKeys(status);
  }

  async getStatusSelect() {
    return this.statusSelect.element(by.css('option:checked')).getText();
  }

  async statusSelectLastOption() {
    await this.statusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setCriticidadePainelSelect(criticidadePainel) {
    await this.criticidadePainelSelect.sendKeys(criticidadePainel);
  }

  async getCriticidadePainelSelect() {
    return this.criticidadePainelSelect.element(by.css('option:checked')).getText();
  }

  async criticidadePainelSelectLastOption() {
    await this.criticidadePainelSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setNivelEficienciaGeralSelect(nivelEficienciaGeral) {
    await this.nivelEficienciaGeralSelect.sendKeys(nivelEficienciaGeral);
  }

  async getNivelEficienciaGeralSelect() {
    return this.nivelEficienciaGeralSelect.element(by.css('option:checked')).getText();
  }

  async nivelEficienciaGeralSelectLastOption() {
    await this.nivelEficienciaGeralSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setNivelEficienciaProcedimentoSelect(nivelEficienciaProcedimento) {
    await this.nivelEficienciaProcedimentoSelect.sendKeys(nivelEficienciaProcedimento);
  }

  async getNivelEficienciaProcedimentoSelect() {
    return this.nivelEficienciaProcedimentoSelect.element(by.css('option:checked')).getText();
  }

  async nivelEficienciaProcedimentoSelectLastOption() {
    await this.nivelEficienciaProcedimentoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setNivelEficienciaPessoaSelect(nivelEficienciaPessoa) {
    await this.nivelEficienciaPessoaSelect.sendKeys(nivelEficienciaPessoa);
  }

  async getNivelEficienciaPessoaSelect() {
    return this.nivelEficienciaPessoaSelect.element(by.css('option:checked')).getText();
  }

  async nivelEficienciaPessoaSelectLastOption() {
    await this.nivelEficienciaPessoaSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setNivelEficienciaProcessoSelect(nivelEficienciaProcesso) {
    await this.nivelEficienciaProcessoSelect.sendKeys(nivelEficienciaProcesso);
  }

  async getNivelEficienciaProcessoSelect() {
    return this.nivelEficienciaProcessoSelect.element(by.css('option:checked')).getText();
  }

  async nivelEficienciaProcessoSelectLastOption() {
    await this.nivelEficienciaProcessoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setNivelEficienciaProdutoSelect(nivelEficienciaProduto) {
    await this.nivelEficienciaProdutoSelect.sendKeys(nivelEficienciaProduto);
  }

  async getNivelEficienciaProdutoSelect() {
    return this.nivelEficienciaProdutoSelect.element(by.css('option:checked')).getText();
  }

  async nivelEficienciaProdutoSelectLastOption() {
    await this.nivelEficienciaProdutoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  async setCanceladoEmInput(canceladoEm) {
    await this.canceladoEmInput.sendKeys(canceladoEm);
  }

  async getCanceladoEmInput() {
    return this.canceladoEmInput.getAttribute('value');
  }

  async setMotivoCancelamentoInput(motivoCancelamento) {
    await this.motivoCancelamentoInput.sendKeys(motivoCancelamento);
  }

  async getMotivoCancelamentoInput() {
    return this.motivoCancelamentoInput.getAttribute('value');
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
  getImportadoViaPlanilhaInput() {
    return this.importadoViaPlanilhaInput;
  }
  async setCaminhoArquivoPlanilhaInput(caminhoArquivoPlanilha) {
    await this.caminhoArquivoPlanilhaInput.sendKeys(caminhoArquivoPlanilha);
  }

  async getCaminhoArquivoPlanilhaInput() {
    return this.caminhoArquivoPlanilhaInput.getAttribute('value');
  }

  async avaliadorSelectLastOption() {
    await this.avaliadorSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async avaliadorSelectOption(option) {
    await this.avaliadorSelect.sendKeys(option);
  }

  getAvaliadorSelect() {
    return this.avaliadorSelect;
  }

  async getAvaliadorSelectedOption() {
    return this.avaliadorSelect.element(by.css('option:checked')).getText();
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
