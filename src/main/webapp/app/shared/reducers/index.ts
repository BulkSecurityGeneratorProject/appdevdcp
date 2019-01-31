import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
// prettier-ignore
import questionario, {
  QuestionarioState
} from 'app/entities/questionario/questionario.reducer';
// prettier-ignore
import loja, {
  LojaState
} from 'app/entities/loja/loja.reducer';
// prettier-ignore
import avaliacao, {
  AvaliacaoState
} from 'app/entities/avaliacao/avaliacao.reducer';
// prettier-ignore
import itemAvaliacao, {
  ItemAvaliacaoState
} from 'app/entities/item-avaliacao/item-avaliacao.reducer';
// prettier-ignore
import grupoItens, {
  GrupoItensState
} from 'app/entities/grupo-itens/grupo-itens.reducer';
// prettier-ignore
import perdaQuebraAcumuladosAnoLoja, {
  PerdaQuebraAcumuladosAnoLojaState
} from 'app/entities/perda-quebra-acumulados-ano-loja/perda-quebra-acumulados-ano-loja.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly settings: SettingsState;
  readonly questionario: QuestionarioState;
  readonly loja: LojaState;
  readonly avaliacao: AvaliacaoState;
  readonly itemAvaliacao: ItemAvaliacaoState;
  readonly grupoItens: GrupoItensState;
  readonly perdaQuebraAcumuladosAnoLoja: PerdaQuebraAcumuladosAnoLojaState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  settings,
  questionario,
  loja,
  avaliacao,
  itemAvaliacao,
  grupoItens,
  perdaQuebraAcumuladosAnoLoja,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
