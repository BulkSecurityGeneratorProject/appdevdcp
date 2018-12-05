import { IUser } from 'app/shared/model/user.model';

export interface ILoja {
  id?: number;
  nome?: string;
  endereco?: string;
  cidade?: string;
  cep?: string;
  latitude?: number;
  longitude?: number;
  avaliadores?: IUser[];
}

export const defaultValue: Readonly<ILoja> = {};
