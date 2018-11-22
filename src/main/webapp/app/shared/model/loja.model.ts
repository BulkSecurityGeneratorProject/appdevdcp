import { IUser } from 'app/shared/model/user.model';

export interface ILoja {
  id?: number;
  codigo?: string;
  nome?: string;
  nomeResponsavel?: string;
  prontuarioResponsavel?: number;
  latitude?: number;
  longitude?: number;
  avaliadores?: IUser[];
}

export const defaultValue: Readonly<ILoja> = {};
