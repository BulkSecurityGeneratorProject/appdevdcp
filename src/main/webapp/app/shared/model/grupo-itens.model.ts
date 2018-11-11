import { Moment } from 'moment';
import { IItemAvaliacao } from 'app/shared/model//item-avaliacao.model';
import { IQuestionario } from 'app/shared/model//questionario.model';

export interface IGrupoItens {
  id?: number;
  nome?: string;
  criadoEm?: Moment;
  itens?: IItemAvaliacao[];
  questionarios?: IQuestionario[];
}

export const defaultValue: Readonly<IGrupoItens> = {};
