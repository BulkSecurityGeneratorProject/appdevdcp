import { Moment } from 'moment';
import { IAvaliacao } from 'app/shared/model//avaliacao.model';

export const enum TipoItemAvaliadoPerdaQuebra {
  PERDA = 'PERDA',
  QUEBRA = 'QUEBRA'
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
  avaliacao?: IAvaliacao;
}

export const defaultValue: Readonly<IItemAvaliadoPerdaQuebraAcumulados> = {};
