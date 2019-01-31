import { IItemAvaliado } from 'app/shared/model/item-avaliado.model';

export interface IItemAvaliacao {
  id?: number;
  descricao?: string;
  anexoObrigatorio?: boolean;
  pontosProcedimento?: number;
  pontosPessoa?: number;
  pontosProcesso?: number;
  pontosProduto?: number;
  ordemExibicao?: number;
  itensAvaliados?: IItemAvaliado[];
  grupoNome?: string;
  grupoId?: number;
}

export const defaultValue: Readonly<IItemAvaliacao> = {
  anexoObrigatorio: false
};
