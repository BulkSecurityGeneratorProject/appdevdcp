import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ILoja } from 'app/shared/model/loja.model';
import { getEntities as getLojas } from 'app/entities/loja/loja.reducer';
import { getEntity, updateEntity, createEntity, reset } from './avaliador.reducer';
import { IAvaliador } from 'app/shared/model/avaliador.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAvaliadorUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAvaliadorUpdateState {
  isNew: boolean;
  lojaId: string;
}

export class AvaliadorUpdate extends React.Component<IAvaliadorUpdateProps, IAvaliadorUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      lojaId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

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

    this.props.getLojas();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { avaliadorEntity } = this.props;
      const entity = {
        ...avaliadorEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/avaliador');
  };

  render() {
    const { avaliadorEntity, lojas, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dcpdesconformidadesApp.avaliador.home.createOrEditLabel">
              <Translate contentKey="dcpdesconformidadesApp.avaliador.home.createOrEditLabel">Create or edit a Avaliador</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : avaliadorEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="avaliador-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nomeLabel" for="nome">
                    <Translate contentKey="dcpdesconformidadesApp.avaliador.nome">Nome</Translate>
                  </Label>
                  <AvField
                    id="avaliador-nome"
                    type="text"
                    name="nome"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="loginLabel" for="login">
                    <Translate contentKey="dcpdesconformidadesApp.avaliador.login">Login</Translate>
                  </Label>
                  <AvField
                    id="avaliador-login"
                    type="text"
                    name="login"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="prontuarioLabel" for="prontuario">
                    <Translate contentKey="dcpdesconformidadesApp.avaliador.prontuario">Prontuario</Translate>
                  </Label>
                  <AvField
                    id="avaliador-prontuario"
                    type="string"
                    className="form-control"
                    name="prontuario"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/avaliador" replace color="info">
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
  lojas: storeState.loja.entities,
  avaliadorEntity: storeState.avaliador.entity,
  loading: storeState.avaliador.loading,
  updating: storeState.avaliador.updating,
  updateSuccess: storeState.avaliador.updateSuccess
});

const mapDispatchToProps = {
  getLojas,
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
)(AvaliadorUpdate);
