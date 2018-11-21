import { Moment } from 'moment';
import { IAnexoItem } from 'app/shared/model//anexo-item.model';

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
  pontosProcedimento?: number;
  pontosPessoa?: number;
  pontosProcesso?: number;
  pontosProduto?: number;
  pontosObtidosProcedimento?: number;
  pontosObtidosPessoa?: number;
  pontosObtidosProcesso?: number;
  pontosObtidosProduto?: number;
  anexos?: IAnexoItem[];
  itemAvaliacaoDescricao?: string;
  itemAvaliacaoId?: number;
  avaliacaoId?: number;
}

export const defaultValue: Readonly<IItemAvaliado> = {};
