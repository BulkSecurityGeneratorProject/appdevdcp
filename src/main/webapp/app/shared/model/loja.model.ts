import { IAvaliacao } from 'app/shared/model/avaliacao.model';
import { IPerdaQuebraAcumuladosAnoLoja } from 'app/shared/model/perda-quebra-acumulados-ano-loja.model';
import { IUser } from 'app/shared/model/user.model';

export interface ILoja {
  id?: number;
  nome?: string;
  endereco?: string;
  cidade?: string;
  cep?: string;
  latitude?: number;
  longitude?: number;
  avaliacoes?: IAvaliacao[];
  perdaQuebraAcumuladosAnos?: IPerdaQuebraAcumuladosAnoLoja[];
  avaliadores?: IUser[];
  nomeFormatado?: string;
}

export const defaultValue: Readonly<ILoja> = {};
