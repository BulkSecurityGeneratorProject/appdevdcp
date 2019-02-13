import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';
import AvSelect from '@availity/reactstrap-validation-select';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntity, updateEntity, createEntity, reset } from './loja.reducer';
import { ILoja } from 'app/shared/model/loja.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILojaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILojaUpdateState {
  isNew: boolean;
  avaliadores: IUser[];
}

export class LojaUpdate extends React.Component<ILojaUpdateProps, ILojaUpdateState> {
  state: ILojaUpdateState = {
    isNew: !this.props.match.params || !this.props.match.params.id,
    avaliadores: []
  };

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getUsers();
  }

  componentWillReceiveProps(nextProps) {
    if (!this.state.isNew && nextProps.lojaEntity.avaliadores && nextProps.lojaEntity.avaliadores.length) {
      this.state.avaliadores = nextProps.lojaEntity.avaliadores.map(a => a.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { lojaEntity } = this.props;
      const entity = {
        ...lojaEntity,
        ...values,
        avaliadores: mapIdList(values.avaliadores)
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/loja');
  };

  handleAvaliadoresChange = selectedOption => {
    this.setState({ avaliadores: selectedOption });
  };

  render() {
    const { lojaEntity, users, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dcpdesconformidadesApp.loja.home.editLabel">
              <Translate contentKey="dcpdesconformidadesApp.loja.home.editLabel">Edit Loja</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p className="loading-message" />
            ) : (
              <AvForm model={isNew ? {} : lojaEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="loja-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nomeLabel" for="nome">
                    <Translate contentKey="dcpdesconformidadesApp.loja.nome">Nome</Translate>
                  </Label>
                  <AvInput id="loja-nome" type="text" className="form-control" name="nome" readOnly />
                </AvGroup>
                <AvGroup>
                  <Label id="enderecoLabel" for="endereco">
                    <Translate contentKey="dcpdesconformidadesApp.loja.endereco">Endereco</Translate>
                  </Label>
                  <AvInput id="loja-endereco" type="text" className="form-control" name="endereco" readOnly />
                </AvGroup>
                <AvGroup>
                  <Label id="cidadeLabel" for="cidade">
                    <Translate contentKey="dcpdesconformidadesApp.loja.cidade">Cidade</Translate>
                  </Label>
                  <AvInput id="loja-cidade" type="text" className="form-control" name="cidade" readOnly />
                </AvGroup>
                <AvGroup>
                  <Label id="cepLabel" for="cep">
                    <Translate contentKey="dcpdesconformidadesApp.loja.cep">Cep</Translate>
                  </Label>
                  <AvInput id="loja-cep" type="text" className="form-control" name="cep" readOnly />
                </AvGroup>
                <AvGroup>
                  <Label id="latitudeLabel" for="latitude">
                    <Translate contentKey="dcpdesconformidadesApp.loja.latitude">Latitude</Translate>
                  </Label>
                  <AvInput id="loja-latitude" type="string" className="form-control" name="latitude" readOnly />
                </AvGroup>
                <AvGroup>
                  <Label id="longitudeLabel" for="longitude">
                    <Translate contentKey="dcpdesconformidadesApp.loja.longitude">Longitude</Translate>
                  </Label>
                  <AvInput id="loja-longitude" type="string" className="form-control" name="longitude" readOnly />
                </AvGroup>
                <AvGroup>
                  <Label for="users">
                    <Translate contentKey="dcpdesconformidadesApp.loja.avaliadores">Avaliadores</Translate>
                  </Label>
                  <AvSelect
                    placeholder="Selecione"
                    name="avaliadores"
                    options={users}
                    value={this.state.avaliadores}
                    onChange={this.handleAvaliadoresChange}
                    labelKey="name"
                    valueKey="id"
                    isMulti
                    isSearchable
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/loja" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
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
  users: storeState.userManagement.users,
  lojaEntity: storeState.loja.entity,
  loading: storeState.loja.loading,
  updating: storeState.loja.updating,
  updateSuccess: storeState.loja.updateSuccess
});

const mapDispatchToProps = {
  getUsers,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LojaUpdate);
