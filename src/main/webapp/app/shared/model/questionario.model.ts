import { Moment } from 'moment';
import { IAvaliacao } from 'app/shared/model//avaliacao.model';
import { IGrupoItens } from 'app/shared/model//grupo-itens.model';

export interface IQuestionario {
  id?: number;
  nome?: string;
  descricao?: string;
  ativo?: boolean;
  criadoEm?: Moment;
  avaliacoesRealizadas?: IAvaliacao[];
  grupos?: IGrupoItens[];
}

export const defaultValue: Readonly<IQuestionario> = {
  ativo: false
};
