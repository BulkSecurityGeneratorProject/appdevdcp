export interface IAuthority {
  name?: string;
  descricao?: string;
}

export const defaultValue: Readonly<IAuthority> = {
  name: '',
  descricao: ''
};
