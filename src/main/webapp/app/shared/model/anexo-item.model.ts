export const enum TipoAnexoItem {
  IMAGEM = 'IMAGEM'
}

export interface IAnexoItem {
  id?: number;
  tipo?: TipoAnexoItem;
  caminhoArquivo?: string;
  itemAvaliadoId?: number;
}

export const defaultValue: Readonly<IAnexoItem> = {};
