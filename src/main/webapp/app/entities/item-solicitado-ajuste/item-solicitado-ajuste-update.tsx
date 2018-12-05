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
import { getEntity, updateEntity, createEntity, reset } from './item-solicitado-ajuste.reducer';
import { IItemSolicitadoAjuste } from 'app/shared/model/item-solicitado-ajuste.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IItemSolicitadoAjusteUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IItemSolicitadoAjusteUpdateState {
  isNew: boolean;
  avaliacaoId: string;
}

export class ItemSolicitadoAjusteUpdate extends React.Component<IItemSolicitadoAjusteUpdateProps, IItemSolicitadoAjusteUpdateState> {
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
      const { itemSolicitadoAjusteEntity } = this.props;
      const entity = {
        ...itemSolicitadoAjusteEntity,
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
    this.props.history.push('/entity/item-solicitado-ajuste');
  };

  render() {
    const { itemSolicitadoAjusteEntity, avaliacaos, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dcpdesconformidadesApp.itemSolicitadoAjuste.home.createOrEditLabel">
              <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.home.createOrEditLabel">
                Create or edit a ItemSolicitadoAjuste
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : itemSolicitadoAjusteEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="item-solicitado-ajuste-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="respondidoEmLabel" for="respondidoEm">
                    <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.respondidoEm">Respondido Em</Translate>
                  </Label>
                  <AvInput
                    id="item-solicitado-ajuste-respondidoEm"
                    type="datetime-local"
                    className="form-control"
                    name="respondidoEm"
                    value={isNew ? null : convertDateTimeFromServer(this.props.itemSolicitadoAjusteEntity.respondidoEm)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ultimaAtualizacaoEmLabel" for="ultimaAtualizacaoEm">
                    <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.ultimaAtualizacaoEm">
                      Ultima Atualizacao Em
                    </Translate>
                  </Label>
                  <AvInput
                    id="item-solicitado-ajuste-ultimaAtualizacaoEm"
                    type="datetime-local"
                    className="form-control"
                    name="ultimaAtualizacaoEm"
                    value={isNew ? null : convertDateTimeFromServer(this.props.itemSolicitadoAjusteEntity.ultimaAtualizacaoEm)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="codigoDepartamentoLabel" for="codigoDepartamento">
                    <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.codigoDepartamento">Codigo Departamento</Translate>
                  </Label>
                  <AvField
                    id="item-solicitado-ajuste-codigoDepartamento"
                    type="string"
                    className="form-control"
                    name="codigoDepartamento"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="codigoSapLabel" for="codigoSap">
                    <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.codigoSap">Codigo Sap</Translate>
                  </Label>
                  <AvField
                    id="item-solicitado-ajuste-codigoSap"
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
                    <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.descricaoItem">Descricao Item</Translate>
                  </Label>
                  <AvField
                    id="item-solicitado-ajuste-descricaoItem"
                    type="text"
                    name="descricaoItem"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="saldoSap0001Label" for="saldoSap0001">
                    <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.saldoSap0001">Saldo Sap 0001</Translate>
                  </Label>
                  <AvField
                    id="item-solicitado-ajuste-saldoSap0001"
                    type="string"
                    className="form-control"
                    name="saldoSap0001"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="saldoFisico0001Label" for="saldoFisico0001">
                    <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.saldoFisico0001">Saldo Fisico 0001</Translate>
                  </Label>
                  <AvField
                    id="item-solicitado-ajuste-saldoFisico0001"
                    type="string"
                    className="form-control"
                    name="saldoFisico0001"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="saldoSap9000Label" for="saldoSap9000">
                    <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.saldoSap9000">Saldo Sap 9000</Translate>
                  </Label>
                  <AvField id="item-solicitado-ajuste-saldoSap9000" type="string" className="form-control" name="saldoSap9000" />
                </AvGroup>
                <AvGroup>
                  <Label id="saldoFisico9000Label" for="saldoFisico9000">
                    <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.saldoFisico9000">Saldo Fisico 9000</Translate>
                  </Label>
                  <AvField id="item-solicitado-ajuste-saldoFisico9000" type="string" className="form-control" name="saldoFisico9000" />
                </AvGroup>
                <AvGroup>
                  <Label id="motivoDivergenciaLabel" for="motivoDivergencia">
                    <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.motivoDivergencia">Motivo Divergencia</Translate>
                  </Label>
                  <AvField id="item-solicitado-ajuste-motivoDivergencia" type="text" name="motivoDivergencia" />
                </AvGroup>
                <AvGroup>
                  <Label id="acaoCorretivaOuPreventivaLabel" for="acaoCorretivaOuPreventiva">
                    <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.acaoCorretivaOuPreventiva">
                      Acao Corretiva Ou Preventiva
                    </Translate>
                  </Label>
                  <AvField id="item-solicitado-ajuste-acaoCorretivaOuPreventiva" type="text" name="acaoCorretivaOuPreventiva" />
                </AvGroup>
                <AvGroup>
                  <Label id="responsavelLabel" for="responsavel">
                    <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.responsavel">Responsavel</Translate>
                  </Label>
                  <AvField id="item-solicitado-ajuste-responsavel" type="text" name="responsavel" />
                </AvGroup>
                <AvGroup>
                  <Label for="avaliacao.id">
                    <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.avaliacao">Avaliacao</Translate>
                  </Label>
                  <AvInput
                    id="item-solicitado-ajuste-avaliacao"
                    type="select"
                    className="form-control"
                    name="avaliacao.id"
                    value={isNew ? avaliacaos[0] && avaliacaos[0].id : itemSolicitadoAjusteEntity.avaliacao.id}
                  >
                    {avaliacaos
                      ? avaliacaos.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/item-solicitado-ajuste" replace color="info">
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
  itemSolicitadoAjusteEntity: storeState.itemSolicitadoAjuste.entity,
  loading: storeState.itemSolicitadoAjuste.loading,
  updating: storeState.itemSolicitadoAjuste.updating,
  updateSuccess: storeState.itemSolicitadoAjuste.updateSuccess
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
)(ItemSolicitadoAjusteUpdate);
