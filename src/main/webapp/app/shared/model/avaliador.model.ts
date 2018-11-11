import { IAvaliacao } from 'app/shared/model//avaliacao.model';
import { ILoja } from 'app/shared/model//loja.model';

export interface IAvaliador {
  id?: number;
  nome?: string;
  login?: string;
  prontuario?: number;
  avaliacaos?: IAvaliacao[];
  lojas?: ILoja[];
}

export const defaultValue: Readonly<IAvaliador> = {};
