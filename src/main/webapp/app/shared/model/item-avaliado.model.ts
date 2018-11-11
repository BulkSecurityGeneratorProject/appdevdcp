import { Moment } from 'moment';
import { IAnexoItem } from 'app/shared/model//anexo-item.model';
import { IItemAvaliacao } from 'app/shared/model//item-avaliacao.model';
import { IAvaliacao } from 'app/shared/model//avaliacao.model';

export const enum StatusItemAvaliado {
  OK = 'OK',
  NAO_OK = 'NAO_OK',
  N_A = 'N_A'
}

export interface IItemAvaliado {
  id?: number;
  respondidoEm?: Moment;
  ultimaAtualizacaoEm?: Moment;
  status?: StatusItemAvaliado;
  observacoes?: string;
  latitudeLocalResposta?: number;
  longitudeLocalResposta?: number;
  anexos?: IAnexoItem[];
  itemAvaliacao?: IItemAvaliacao;
  avaliacao?: IAvaliacao;
}

export const defaultValue: Readonly<IItemAvaliado> = {};
