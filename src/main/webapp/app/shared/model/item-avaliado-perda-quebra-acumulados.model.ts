import { Moment } from 'moment';
import { IAvaliacao } from 'app/shared/model//avaliacao.model';

export const enum TipoItemAvaliadoPerdaQuebra {
  PERDA = 'PERDA',
  QUEBRA = 'QUEBRA'
}

export const enum ClassificacaoPerdaQuebra {
  CONFORMIDADE = 'CONFORMIDADE',
  DESCONFORMIDADE = 'DESCONFORMIDADE'
}

export const enum CategorizacaoPerdaQuebra {
  INADMISSIVEL = 'INADMISSIVEL',
  CRITICO = 'CRITICO',
  VALOR_ELEVADO = 'VALOR_ELEVADO',
  ATENCAO = 'ATENCAO',
  CONTROLE = 'CONTROLE',
  SOBRA_DESCONTROLE = 'SOBRA_DESCONTROLE'
}

export interface IItemAvaliadoPerdaQuebraAcumulados {
  id?: number;
  tipo?: TipoItemAvaliadoPerdaQuebra;
  respondidoEm?: Moment;
  ultimaAtualizacaoEm?: Moment;
  percentual?: number;
  financeiro?: number;
  pontuacao?: number;
  latitudeLocalResposta?: number;
  longitudeLocalResposta?: number;
  classificacao?: ClassificacaoPerdaQuebra;
  categorizacao?: CategorizacaoPerdaQuebra;
  avaliacao?: IAvaliacao;
}

export const defaultValue: Readonly<IItemAvaliadoPerdaQuebraAcumulados> = {};
