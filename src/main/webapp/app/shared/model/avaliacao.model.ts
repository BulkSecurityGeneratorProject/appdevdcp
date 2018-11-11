import { Moment } from 'moment';
import { IItemAvaliado } from 'app/shared/model//item-avaliado.model';
import { IItemAvaliadoPerdaQuebraAcumulados } from 'app/shared/model//item-avaliado-perda-quebra-acumulados.model';
import { IItemAuditado } from 'app/shared/model//item-auditado.model';
import { IItemSolicitadoAjuste } from 'app/shared/model//item-solicitado-ajuste.model';

export const enum StatusAvaliacao {
  INICIADA = 'INICIADA',
  RELATORIO_FINALIZADO = 'RELATORIO_FINALIZADO',
  ANEXO_AUDITORIA_FINALIZADO = 'ANEXO_AUDITORIA_FINALIZADO',
  ANEXO_SOLICITACAO_AJUSTE_FINALIZADO = 'ANEXO_SOLICITACAO_AJUSTE_FINALIZADO',
  SUBMETIDO = 'SUBMETIDO'
}

export interface IAvaliacao {
  id?: number;
  dataInicio?: Moment;
  latitudeInicioAvaliacao?: number;
  longitudeInicioAvaliacao?: number;
  nomeResponsavelLoja?: string;
  prontuarioResponsavelLoja?: number;
  submetidoEm?: Moment;
  latitudeSubmissaoAvaliacao?: number;
  longitudeSubmissaoAvaliacao?: number;
  observacaoSubmissaoEnviadaForaDaLoja?: string;
  status?: StatusAvaliacao;
  itemAvaliados?: IItemAvaliado[];
  itensPerdaEQuebraAcumulados?: IItemAvaliadoPerdaQuebraAcumulados[];
  itensAuditados?: IItemAuditado[];
  itensComAjusteSolicitados?: IItemSolicitadoAjuste[];
  questionarioNome?: string;
  questionarioId?: number;
  avaliadorNome?: string;
  avaliadorId?: number;
}

export const defaultValue: Readonly<IAvaliacao> = {};
