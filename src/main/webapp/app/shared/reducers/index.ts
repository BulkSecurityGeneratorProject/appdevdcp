import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
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
import itemAvaliado, {
  ItemAvaliadoState
} from 'app/entities/item-avaliado/item-avaliado.reducer';
// prettier-ignore
import itemAvaliadoPerdaQuebraAcumulados, {
  ItemAvaliadoPerdaQuebraAcumuladosState
} from 'app/entities/item-avaliado-perda-quebra-acumulados/item-avaliado-perda-quebra-acumulados.reducer';
// prettier-ignore
import grupoItens, {
  GrupoItensState
} from 'app/entities/grupo-itens/grupo-itens.reducer';
// prettier-ignore
import anexoItem, {
  AnexoItemState
} from 'app/entities/anexo-item/anexo-item.reducer';
// prettier-ignore
import itemAuditado, {
  ItemAuditadoState
} from 'app/entities/item-auditado/item-auditado.reducer';
// prettier-ignore
import itemSolicitadoAjuste, {
  ItemSolicitadoAjusteState
} from 'app/entities/item-solicitado-ajuste/item-solicitado-ajuste.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly questionario: QuestionarioState;
  readonly loja: LojaState;
  readonly avaliacao: AvaliacaoState;
  readonly itemAvaliacao: ItemAvaliacaoState;
  readonly itemAvaliado: ItemAvaliadoState;
  readonly itemAvaliadoPerdaQuebraAcumulados: ItemAvaliadoPerdaQuebraAcumuladosState;
  readonly grupoItens: GrupoItensState;
  readonly anexoItem: AnexoItemState;
  readonly itemAuditado: ItemAuditadoState;
  readonly itemSolicitadoAjuste: ItemSolicitadoAjusteState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  questionario,
  loja,
  avaliacao,
  itemAvaliacao,
  itemAvaliado,
  itemAvaliadoPerdaQuebraAcumulados,
  grupoItens,
  anexoItem,
  itemAuditado,
  itemSolicitadoAjuste,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
