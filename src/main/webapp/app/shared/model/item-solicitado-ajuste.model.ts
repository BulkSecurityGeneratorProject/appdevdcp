import { Moment } from 'moment';
import { IAvaliacao } from 'app/shared/model//avaliacao.model';

export interface IItemSolicitadoAjuste {
  id?: number;
  respondidoEm?: Moment;
  ultimaAtualizacaoEm?: Moment;
  departamento?: number;
  codigoSap?: number;
  descricaoItem?: string;
  saldoSap?: number;
  saldoFisico?: number;
  motivoDivergencia?: string;
  acaoCorretivaOuPreventiva?: string;
  responsavel?: string;
  avaliacao?: IAvaliacao;
}

export const defaultValue: Readonly<IItemSolicitadoAjuste> = {};
