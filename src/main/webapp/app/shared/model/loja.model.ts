import { IAvaliador } from 'app/shared/model//avaliador.model';

export interface ILoja {
  id?: number;
  nome?: string;
  nomeResponsavel?: string;
  prontuarioResponsavel?: number;
  latitude?: number;
  longitude?: number;
  avaliadors?: IAvaliador[];
}

export const defaultValue: Readonly<ILoja> = {};
