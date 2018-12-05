import { Moment } from 'moment';
import { IAvaliacao } from 'app/shared/model//avaliacao.model';

export const enum TipoItemAuditado {
  TOP_5_PERDAS = 'TOP_5_PERDAS',
  ALTO_RISCO = 'ALTO_RISCO',
  TROCA_CANCELAMENTO_DVC = 'TROCA_CANCELAMENTO_DVC'
}

export interface IItemAuditado {
  id?: number;
  respondidoEm?: Moment;
  ultimaAtualizacaoEm?: Moment;
  tipo?: TipoItemAuditado;
  codigoDepartamento?: number;
  codigoSap?: number;
  descricaoItem?: string;
  saldoSap0001?: number;
  saldoFisico0001?: number;
  saldoSap9000?: number;
  saldoFisico9000?: number;
  motivoDivergencia?: string;
  avaliacao?: IAvaliacao;
}

export const defaultValue: Readonly<IItemAuditado> = {};
