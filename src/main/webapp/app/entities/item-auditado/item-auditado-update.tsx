import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAvaliacao } from 'app/shared/model/avaliacao.model';
import { getEntities as getAvaliacaos } from 'app/entities/avaliacao/avaliacao.reducer';
import { getEntity, updateEntity, createEntity, reset } from './item-auditado.reducer';
import { IItemAuditado } from 'app/shared/model/item-auditado.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IItemAuditadoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IItemAuditadoUpdateState {
  isNew: boolean;
  avaliacaoId: string;
}

export class ItemAuditadoUpdate extends React.Component<IItemAuditadoUpdateProps, IItemAuditadoUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      avaliacaoId: '0',
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

    this.props.getAvaliacaos();
  }

  saveEntity = (event, errors, values) => {
    values.respondidoEm = new Date(values.respondidoEm);
    values.ultimaAtualizacaoEm = new Date(values.ultimaAtualizacaoEm);

    if (errors.length === 0) {
      const { itemAuditadoEntity } = this.props;
      const entity = {
        ...itemAuditadoEntity,
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
    this.props.history.push('/entity/item-auditado');
  };

  render() {
    const { itemAuditadoEntity, avaliacaos, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dcpdesconformidadesApp.itemAuditado.home.createOrEditLabel">
              <Translate contentKey="dcpdesconformidadesApp.itemAuditado.home.createOrEditLabel">Create or edit a ItemAuditado</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : itemAuditadoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="item-auditado-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="respondidoEmLabel" for="respondidoEm">
                    <Translate contentKey="dcpdesconformidadesApp.itemAuditado.respondidoEm">Respondido Em</Translate>
                  </Label>
                  <AvInput
                    id="item-auditado-respondidoEm"
                    type="datetime-local"
                    className="form-control"
                    name="respondidoEm"
                    value={isNew ? null : convertDateTimeFromServer(this.props.itemAuditadoEntity.respondidoEm)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ultimaAtualizacaoEmLabel" for="ultimaAtualizacaoEm">
                    <Translate contentKey="dcpdesconformidadesApp.itemAuditado.ultimaAtualizacaoEm">Ultima Atualizacao Em</Translate>
                  </Label>
                  <AvInput
                    id="item-auditado-ultimaAtualizacaoEm"
                    type="datetime-local"
                    className="form-control"
                    name="ultimaAtualizacaoEm"
                    value={isNew ? null : convertDateTimeFromServer(this.props.itemAuditadoEntity.ultimaAtualizacaoEm)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tipoLabel">
                    <Translate contentKey="dcpdesconformidadesApp.itemAuditado.tipo">Tipo</Translate>
                  </Label>
                  <AvInput
                    id="item-auditado-tipo"
                    type="select"
                    className="form-control"
                    name="tipo"
                    value={(!isNew && itemAuditadoEntity.tipo) || 'TOP_5_PERDAS'}
                  >
                    <option value="TOP_5_PERDAS">
                      <Translate contentKey="dcpdesconformidadesApp.TipoItemAuditado.TOP_5_PERDAS" />
                    </option>
                    <option value="ALTO_RISCO">
                      <Translate contentKey="dcpdesconformidadesApp.TipoItemAuditado.ALTO_RISCO" />
                    </option>
                    <option value="TROCA_CANCELAMENTO_DVC">
                      <Translate contentKey="dcpdesconformidadesApp.TipoItemAuditado.TROCA_CANCELAMENTO_DVC" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="departamentoLabel" for="departamento">
                    <Translate contentKey="dcpdesconformidadesApp.itemAuditado.departamento">Departamento</Translate>
                  </Label>
                  <AvField
                    id="item-auditado-departamento"
                    type="string"
                    className="form-control"
                    name="departamento"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="codigoSapLabel" for="codigoSap">
                    <Translate contentKey="dcpdesconformidadesApp.itemAuditado.codigoSap">Codigo Sap</Translate>
                  </Label>
                  <AvField
                    id="item-auditado-codigoSap"
                    type="string"
                    className="form-control"
                    name="codigoSap"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="descricaoItemLabel" for="descricaoItem">
                    <Translate contentKey="dcpdesconformidadesApp.itemAuditado.descricaoItem">Descricao Item</Translate>
                  </Label>
                  <AvField
                    id="item-auditado-descricaoItem"
                    type="text"
                    name="descricaoItem"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="saldoSapLabel" for="saldoSap">
                    <Translate contentKey="dcpdesconformidadesApp.itemAuditado.saldoSap">Saldo Sap</Translate>
                  </Label>
                  <AvField
                    id="item-auditado-saldoSap"
                    type="string"
                    className="form-control"
                    name="saldoSap"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="saldoFisicoLabel" for="saldoFisico">
                    <Translate contentKey="dcpdesconformidadesApp.itemAuditado.saldoFisico">Saldo Fisico</Translate>
                  </Label>
                  <AvField
                    id="item-auditado-saldoFisico"
                    type="string"
                    className="form-control"
                    name="saldoFisico"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="motivoDivergenciaLabel" for="motivoDivergencia">
                    <Translate contentKey="dcpdesconformidadesApp.itemAuditado.motivoDivergencia">Motivo Divergencia</Translate>
                  </Label>
                  <AvField id="item-auditado-motivoDivergencia" type="text" name="motivoDivergencia" />
                </AvGroup>
                <AvGroup>
                  <Label for="avaliacao.id">
                    <Translate contentKey="dcpdesconformidadesApp.itemAuditado.avaliacao">Avaliacao</Translate>
                  </Label>
                  <AvInput id="item-auditado-avaliacao" type="select" className="form-control" name="avaliacao.id">
                    <option value="" key="0" />
                    {avaliacaos
                      ? avaliacaos.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/item-auditado" replace color="info">
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
  avaliacaos: storeState.avaliacao.entities,
  itemAuditadoEntity: storeState.itemAuditado.entity,
  loading: storeState.itemAuditado.loading,
  updating: storeState.itemAuditado.updating,
  updateSuccess: storeState.itemAuditado.updateSuccess
});

const mapDispatchToProps = {
  getAvaliacaos,
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
)(ItemAuditadoUpdate);
