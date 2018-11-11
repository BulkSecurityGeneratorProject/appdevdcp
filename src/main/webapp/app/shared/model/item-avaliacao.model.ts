import { Moment } from 'moment';
import { IItemAvaliado } from 'app/shared/model//item-avaliado.model';
import { IGrupoItens } from 'app/shared/model//grupo-itens.model';

export interface IItemAvaliacao {
  id?: number;
  descricao?: string;
  anexoObrigatorio?: boolean;
  criadoEm?: Moment;
  itemAvaliados?: IItemAvaliado[];
  grupos?: IGrupoItens[];
}

export const defaultValue: Readonly<IItemAvaliacao> = {
  anexoObrigatorio: false
};
