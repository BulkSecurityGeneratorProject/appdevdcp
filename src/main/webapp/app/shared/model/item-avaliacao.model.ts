import { IItemAvaliado } from 'app/shared/model//item-avaliado.model';
import { IGrupoItens } from 'app/shared/model//grupo-itens.model';

export interface IItemAvaliacao {
  id?: number;
  descricao?: string;
  anexoObrigatorio?: boolean;
  pontosProcedimento?: number;
  pontosPessoa?: number;
  pontosProcesso?: number;
  pontosProduto?: number;
  itensAvaliados?: IItemAvaliado[];
  grupos?: IGrupoItens[];
}

export const defaultValue: Readonly<IItemAvaliacao> = {
  anexoObrigatorio: false
};
