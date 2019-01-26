import { IItemAvaliacao } from 'app/shared/model/item-avaliacao.model';

export interface IGrupoItens {
  id?: number;
  nome?: string;
  itens?: IItemAvaliacao[];
  questionarioNome?: string;
  questionarioId?: number;
}

export const defaultValue: Readonly<IGrupoItens> = {};
