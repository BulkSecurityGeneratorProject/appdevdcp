import { Moment } from 'moment';

export interface IItemSolicitadoAjuste {
  id?: number;
  respondidoEm?: Moment;
  ultimaAtualizacaoEm?: Moment;
  codigoDepartamento?: number;
  codigoSap?: number;
  descricaoItem?: string;
  saldoSap0001?: number;
  saldoFisico0001?: number;
  saldoSap9000?: number;
  saldoFisico9000?: number;
  motivoDivergencia?: string;
  acaoCorretivaOuPreventiva?: string;
  responsavel?: string;
  avaliacaoId?: number;
}

export const defaultValue: Readonly<IItemSolicitadoAjuste> = {};
