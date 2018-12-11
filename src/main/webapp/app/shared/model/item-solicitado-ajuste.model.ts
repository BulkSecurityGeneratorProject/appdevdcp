import { Moment } from 'moment';
import { IAvaliacao } from 'app/shared/model/avaliacao.model';

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
  avaliacao?: IAvaliacao;
}

export const defaultValue: Readonly<IItemSolicitadoAjuste> = {};
