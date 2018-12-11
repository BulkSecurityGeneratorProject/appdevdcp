import { IAuthority } from 'app/shared/model/authority.model';
export interface IUser {
  id?: any;
  login?: string;
  name?: string;
  email?: string;
  activated?: boolean;
  langKey?: string;
  authorities?: IAuthority[];
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  password?: string;
}

export const defaultValue: Readonly<IUser> = {
  id: '',
  login: '',
  name: '',
  email: '',
  activated: false,
  langKey: '',
  authorities: [],
  createdBy: '',
  createdDate: null,
  lastModifiedBy: '',
  lastModifiedDate: null,
  password: ''
};
