import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Label, Row, Col, InputGroup, InputGroupAddon } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import AvSelect, { AvSelectField } from '@availity/reactstrap-validation-select';

import { locales, languages } from 'app/config/translation';
import { getUser, getRoles, updateUser, createUser, getUserLdap, reset } from './user-management.reducer';
import { getEntities as getLojas } from 'app/entities/loja/loja.reducer';
import { IRootState } from 'app/shared/reducers';
import { IAuthority } from 'app/shared/model/authority.model';
import { ILoja } from 'app/shared/model/loja.model';

export interface IUserManagementUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ login: string }> {}

export interface IUserManagementUpdateState {
  isNew: boolean;
  authorities: IAuthority[];
  lojasSelecionadas: ILoja[];
  login: string;
}

export class UserManagementUpdate extends React.Component<IUserManagementUpdateProps, IUserManagementUpdateState> {
  state: IUserManagementUpdateState = {
    isNew: !this.props.match.params || !this.props.match.params.login,
    authorities: [],
    lojasSelecionadas: [],
    login: null
  };

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getUser(this.props.match.params.login);
    }
    this.props.getRoles();
    this.props.getLojas();
  }

  componentWillReceiveProps(nextProps) {
    if (!this.state.isNew && nextProps.user.authorities && nextProps.user.authorities.length) {
      this.state.authorities = nextProps.user.authorities.map(a => a.name);
    }
    if (!this.state.isNew && nextProps.user.lojas && nextProps.user.lojas.length) {
      this.state.lojasSelecionadas = nextProps.user.lojas.map(a => a.id);
    }
    if (nextProps.user.login) {
      this.state.login = nextProps.user.login;
    }
  }

  componentWillUnmount() {
    this.props.reset();
  }

  saveUser = (event, values) => {
    if (this.state.isNew) {
      this.props.createUser(values);
    } else {
      this.props.updateUser(values);
    }
    this.handleClose();
  };

  handleClose = () => {
    this.props.history.push('/admin/user-management');
  };

  handleAuthoritiesChange = selectedOption => {
    this.setState({ authorities: selectedOption });
  };

  handleLojasChange = selectedOption => {
    this.setState({ lojasSelecionadas: selectedOption });
  };

  handleObterLogin = event => {
    event.stopPropagation();
    this.props.getUserLdap(this.state.login);
  };

  handleLoginChange = event => {
    this.setState({ login: event.target.value });
  };

  render() {
    const isInvalid = false;
    const { user, loading, updating, roles, lojas } = this.props;
    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h1>
              <Translate contentKey="userManagement.home.createOrEditLabel">Create or edit a User</Translate>
            </h1>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p className="loading-message" />
            ) : (
              <AvForm onValidSubmit={this.saveUser}>
                {user.id ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvField type="text" className="form-control" name="id" required readOnly value={user.id} />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label for="login">
                    <Translate contentKey="userManagement.login">Login</Translate>
                  </Label>
                  <Row>
                    <Col>
                      <AvField
                        type="text"
                        className="form-control"
                        name="login"
                        validate={{
                          required: {
                            value: true,
                            errorMessage: translate('register.messages.validate.login.required')
                          },
                          pattern: {
                            value: '^[_.@A-Za-z0-9-]*$',
                            errorMessage: translate('register.messages.validate.login.pattern')
                          },
                          minLength: {
                            value: 1,
                            errorMessage: translate('register.messages.validate.login.minlength')
                          },
                          maxLength: {
                            value: 50,
                            errorMessage: translate('register.messages.validate.login.maxlength')
                          }
                        }}
                        onChange={this.handleLoginChange}
                        value={this.state.login}
                        readOnly={!this.state.isNew}
                      />
                    </Col>
                    {this.state.isNew ? (
                      <Col>
                        <Button color="info" onClick={this.handleObterLogin}>
                          <FontAwesomeIcon icon="search" />
                          &nbsp;
                          <Translate contentKey="entity.action.search">Search</Translate>
                        </Button>
                      </Col>
                    ) : null}
                  </Row>
                </AvGroup>
                <AvGroup>
                  <Label for="name">
                    <Translate contentKey="userManagement.name">Name</Translate>
                  </Label>
                  <AvField
                    type="text"
                    className="form-control"
                    name="name"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: {
                        value: 255,
                        errorMessage: translate('entity.validation.maxlength', { max: 255 })
                      }
                    }}
                    value={user.name}
                    readOnly
                  />
                </AvGroup>
                <AvGroup>
                  <AvField
                    name="email"
                    label={translate('global.form.email')}
                    type="email"
                    validate={{
                      required: {
                        value: true,
                        errorMessage: translate('global.messages.validate.email.required')
                      },
                      email: {
                        errorMessage: translate('global.messages.validate.email.invalid')
                      },
                      minLength: {
                        value: 5,
                        errorMessage: translate('global.messages.validate.email.minlength')
                      },
                      maxLength: {
                        value: 254,
                        errorMessage: translate('global.messages.validate.email.maxlength')
                      }
                    }}
                    value={user.email}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="prontuario">
                    <Translate contentKey="userManagement.prontuario">Prontuario</Translate>
                  </Label>
                  <AvField
                    name="prontuario"
                    type="number"
                    validate={{
                      minLength: { value: 5, errorMessage: translate('entity.validation.minlength', { min: 5 }) },
                      maxLength: { value: 10, errorMessage: translate('entity.validation.maxlength', { max: 10 }) }
                    }}
                    value={user.prontuario}
                  />
                </AvGroup>
                <AvGroup check inline>
                  <Label>
                    <AvInput type="checkbox" name="activated" value={user.activated} />
                    <Translate contentKey="userManagement.activated">Activated</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="langKey">
                    <Translate contentKey="userManagement.langKey">Language Key</Translate>
                  </Label>
                  <AvField type="select" className="form-control" name="langKey" value={user.langKey}>
                    {locales.map(locale => (
                      <option value={locale} key={locale}>
                        {languages[locale].name}
                      </option>
                    ))}
                  </AvField>
                </AvGroup>
                <AvGroup>
                  <Label for="authorities">
                    <Translate contentKey="userManagement.profiles">Profiles</Translate>
                  </Label>
                  <AvSelect
                    placeholder="Selecione"
                    name="authorities"
                    options={roles}
                    value={this.state.authorities}
                    onChange={this.handleAuthoritiesChange}
                    labelKey="descricao"
                    valueKey="name"
                    isMulti
                    isSearchable
                    required
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="lojas">
                    <Translate contentKey="userManagement.lojas">Lojas</Translate>
                  </Label>
                  <AvSelect
                    placeholder="Selecione"
                    name="lojas"
                    options={lojas}
                    value={this.state.lojasSelecionadas}
                    onChange={this.handleLojasChange}
                    labelKey="nomeFormatado"
                    valueKey="id"
                    isMulti
                    isSearchable
                    required
                  />
                </AvGroup>
                <Button tag={Link} to="/admin/user-management" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" type="submit" disabled={isInvalid || updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  user: storeState.userManagement.user,
  roles: storeState.userManagement.authorities,
  lojas: storeState.loja.entities,
  loading: storeState.userManagement.loading,
  updating: storeState.userManagement.updating
});

const mapDispatchToProps = { getUser, getRoles, getLojas, updateUser, createUser, getUserLdap, reset };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UserManagementUpdate);
