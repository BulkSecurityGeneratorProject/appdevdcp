import { IAuthority } from 'app/shared/model/authority.model';
import { ILoja } from 'app/shared/model/loja.model';
export interface IUser {
  id?: any;
  login?: string;
  name?: string;
  email?: string;
  activated?: boolean;
  authorities?: IAuthority[];
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  password?: string;
  prontuario?: number;
  lojas?: ILoja[];
}

export const defaultValue: Readonly<IUser> = {
  id: '',
  login: '',
  name: '',
  email: '',
  activated: true,
  authorities: [],
  createdBy: '',
  createdDate: null,
  lastModifiedBy: '',
  lastModifiedDate: null,
  password: '',
  prontuario: 0,
  lojas: []
};
