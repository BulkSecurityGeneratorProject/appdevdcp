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

export const enum CriticidadePainel {
  INADMISSIVEL = 'INADMISSIVEL',
  CONTROLE = 'CONTROLE',
  VALOR_ELEVADO = 'VALOR_ELEVADO',
  CRITICO = 'CRITICO',
  ATENCAO = 'ATENCAO'
}

export const enum NivelEficiencia {
  A = 'A',
  B = 'B',
  C = 'C',
  D = 'D',
  E = 'E'
}

export interface IAvaliacao {
  id?: number;
  iniciadaEm?: Moment;
  latitudeInicioAvaliacao?: number;
  longitudeInicioAvaliacao?: number;
  nomeResponsavelLoja?: string;
  prontuarioResponsavelLoja?: number;
  submetidoEm?: Moment;
  latitudeSubmissaoAvaliacao?: number;
  longitudeSubmissaoAvaliacao?: number;
  observacaoSubmissaoEnviadaForaDaLoja?: string;
  status?: StatusAvaliacao;
  criticidadePainel?: CriticidadePainel;
  nivelEficienciaGeral?: NivelEficiencia;
  nivelEficienciaProcedimento?: NivelEficiencia;
  nivelEficienciaPessoa?: NivelEficiencia;
  nivelEficienciaProcesso?: NivelEficiencia;
  nivelEficienciaProduto?: NivelEficiencia;
  canceladoEm?: Moment;
  motivoCancelamento?: string;
  itensAvaliados?: IItemAvaliado[];
  itensPerdaEQuebraAcumulados?: IItemAvaliadoPerdaQuebraAcumulados[];
  itensAuditados?: IItemAuditado[];
  itensComAjusteSolicitados?: IItemSolicitadoAjuste[];
  userName?: string;
  userId?: number;
  questionarioNome?: string;
  questionarioId?: number;
}

export const defaultValue: Readonly<IAvaliacao> = {};
