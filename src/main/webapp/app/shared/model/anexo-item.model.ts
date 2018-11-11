import { IItemAvaliado } from 'app/shared/model//item-avaliado.model';

export const enum TipoAnexoItem {
  IMAGEM = 'IMAGEM'
}

export interface IAnexoItem {
  id?: number;
  tipo?: TipoAnexoItem;
  caminhoArquivo?: string;
  itemAvaliado?: IItemAvaliado;
}

export const defaultValue: Readonly<IAnexoItem> = {};
