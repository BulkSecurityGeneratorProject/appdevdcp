import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAvaliador } from 'app/shared/model/avaliador.model';
import { getEntities as getAvaliadors } from 'app/entities/avaliador/avaliador.reducer';
import { getEntity, updateEntity, createEntity, reset } from './loja.reducer';
import { ILoja } from 'app/shared/model/loja.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILojaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILojaUpdateState {
  isNew: boolean;
  idsavaliador: any[];
}

export class LojaUpdate extends React.Component<ILojaUpdateProps, ILojaUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idsavaliador: [],
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

    this.props.getAvaliadors();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { lojaEntity } = this.props;
      const entity = {
        ...lojaEntity,
        ...values,
        avaliadors: mapIdList(values.avaliadors)
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

  render() {
    const { lojaEntity, avaliadors, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dcpdesconformidadesApp.loja.home.createOrEditLabel">
              <Translate contentKey="dcpdesconformidadesApp.loja.home.createOrEditLabel">Create or edit a Loja</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
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
                  <AvField
                    id="loja-nome"
                    type="text"
                    name="nome"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nomeResponsavelLabel" for="nomeResponsavel">
                    <Translate contentKey="dcpdesconformidadesApp.loja.nomeResponsavel">Nome Responsavel</Translate>
                  </Label>
                  <AvField
                    id="loja-nomeResponsavel"
                    type="text"
                    name="nomeResponsavel"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="prontuarioResponsavelLabel" for="prontuarioResponsavel">
                    <Translate contentKey="dcpdesconformidadesApp.loja.prontuarioResponsavel">Prontuario Responsavel</Translate>
                  </Label>
                  <AvField
                    id="loja-prontuarioResponsavel"
                    type="string"
                    className="form-control"
                    name="prontuarioResponsavel"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="latitudeLabel" for="latitude">
                    <Translate contentKey="dcpdesconformidadesApp.loja.latitude">Latitude</Translate>
                  </Label>
                  <AvField
                    id="loja-latitude"
                    type="string"
                    className="form-control"
                    name="latitude"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="longitudeLabel" for="longitude">
                    <Translate contentKey="dcpdesconformidadesApp.loja.longitude">Longitude</Translate>
                  </Label>
                  <AvField
                    id="loja-longitude"
                    type="string"
                    className="form-control"
                    name="longitude"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="avaliadors">
                    <Translate contentKey="dcpdesconformidadesApp.loja.avaliador">Avaliador</Translate>
                  </Label>
                  <AvInput
                    id="loja-avaliador"
                    type="select"
                    multiple
                    className="form-control"
                    name="avaliadors"
                    value={lojaEntity.avaliadors && lojaEntity.avaliadors.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {avaliadors
                      ? avaliadors.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.nome}
                          </option>
                        ))
                      : null}
                  </AvInput>
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
  avaliadors: storeState.avaliador.entities,
  lojaEntity: storeState.loja.entity,
  loading: storeState.loja.loading,
  updating: storeState.loja.updating,
  updateSuccess: storeState.loja.updateSuccess
});

const mapDispatchToProps = {
  getAvaliadors,
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
