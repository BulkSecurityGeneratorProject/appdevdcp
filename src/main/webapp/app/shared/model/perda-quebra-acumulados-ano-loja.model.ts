import { ILoja } from 'app/shared/model//loja.model';

export const enum StatusItemAvaliado {
  OK = 'OK',
  NAO_OK = 'NAO_OK',
  N_A = 'N_A'
}

export const enum CategorizacaoPerdaQuebra {
  INADMISSIVEL = 'INADMISSIVEL',
  CRITICO = 'CRITICO',
  VALOR_ELEVADO = 'VALOR_ELEVADO',
  ATENCAO = 'ATENCAO',
  CONTROLE = 'CONTROLE',
  SOBRA_DESCONTROLE = 'SOBRA_DESCONTROLE'
}

export interface IPerdaQuebraAcumuladosAnoLoja {
  id?: number;
  ano?: number;
  percentualPerda?: number;
  financeiroPerda?: number;
  pontuacaoPerda?: number;
  statusPerda?: StatusItemAvaliado;
  categorizacaoPerda?: CategorizacaoPerdaQuebra;
  percentualQuebra?: number;
  financeiroQuebra?: number;
  pontuacaoQuebra?: number;
  statusQuebra?: StatusItemAvaliado;
  categorizacaoQuebra?: CategorizacaoPerdaQuebra;
  loja?: ILoja;
}

export const defaultValue: Readonly<IPerdaQuebraAcumuladosAnoLoja> = {};
