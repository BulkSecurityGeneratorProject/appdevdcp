import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IItemAvaliacao } from 'app/shared/model/item-avaliacao.model';
import { getEntities as getItemAvaliacaos } from 'app/entities/item-avaliacao/item-avaliacao.reducer';
import { IAvaliacao } from 'app/shared/model/avaliacao.model';
import { getEntities as getAvaliacaos } from 'app/entities/avaliacao/avaliacao.reducer';
import { getEntity, updateEntity, createEntity, reset } from './item-avaliado.reducer';
import { IItemAvaliado } from 'app/shared/model/item-avaliado.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IItemAvaliadoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IItemAvaliadoUpdateState {
  isNew: boolean;
  itemAvaliacaoId: string;
  avaliacaoId: string;
}

export class ItemAvaliadoUpdate extends React.Component<IItemAvaliadoUpdateProps, IItemAvaliadoUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      itemAvaliacaoId: '0',
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

    this.props.getItemAvaliacaos();
    this.props.getAvaliacaos();
  }

  saveEntity = (event, errors, values) => {
    values.respondidoEm = new Date(values.respondidoEm);
    values.ultimaAtualizacaoEm = new Date(values.ultimaAtualizacaoEm);

    if (errors.length === 0) {
      const { itemAvaliadoEntity } = this.props;
      const entity = {
        ...itemAvaliadoEntity,
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
    this.props.history.push('/entity/item-avaliado');
  };

  render() {
    const { itemAvaliadoEntity, itemAvaliacaos, avaliacaos, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dcpdesconformidadesApp.itemAvaliado.home.createOrEditLabel">
              <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.home.createOrEditLabel">Create or edit a ItemAvaliado</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : itemAvaliadoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="item-avaliado-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="respondidoEmLabel" for="respondidoEm">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.respondidoEm">Respondido Em</Translate>
                  </Label>
                  <AvInput
                    id="item-avaliado-respondidoEm"
                    type="datetime-local"
                    className="form-control"
                    name="respondidoEm"
                    value={isNew ? null : convertDateTimeFromServer(this.props.itemAvaliadoEntity.respondidoEm)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ultimaAtualizacaoEmLabel" for="ultimaAtualizacaoEm">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.ultimaAtualizacaoEm">Ultima Atualizacao Em</Translate>
                  </Label>
                  <AvInput
                    id="item-avaliado-ultimaAtualizacaoEm"
                    type="datetime-local"
                    className="form-control"
                    name="ultimaAtualizacaoEm"
                    value={isNew ? null : convertDateTimeFromServer(this.props.itemAvaliadoEntity.ultimaAtualizacaoEm)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.status">Status</Translate>
                  </Label>
                  <AvInput
                    id="item-avaliado-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && itemAvaliadoEntity.status) || 'OK'}
                  >
                    <option value="OK">
                      <Translate contentKey="dcpdesconformidadesApp.StatusItemAvaliado.OK" />
                    </option>
                    <option value="NAO_OK">
                      <Translate contentKey="dcpdesconformidadesApp.StatusItemAvaliado.NAO_OK" />
                    </option>
                    <option value="N_A">
                      <Translate contentKey="dcpdesconformidadesApp.StatusItemAvaliado.N_A" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="observacoesLabel" for="observacoes">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.observacoes">Observacoes</Translate>
                  </Label>
                  <AvField id="item-avaliado-observacoes" type="text" name="observacoes" />
                </AvGroup>
                <AvGroup>
                  <Label id="latitudeLocalRespostaLabel" for="latitudeLocalResposta">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.latitudeLocalResposta">Latitude Local Resposta</Translate>
                  </Label>
                  <AvField
                    id="item-avaliado-latitudeLocalResposta"
                    type="string"
                    className="form-control"
                    name="latitudeLocalResposta"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="longitudeLocalRespostaLabel" for="longitudeLocalResposta">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.longitudeLocalResposta">Longitude Local Resposta</Translate>
                  </Label>
                  <AvField
                    id="item-avaliado-longitudeLocalResposta"
                    type="string"
                    className="form-control"
                    name="longitudeLocalResposta"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pontosProcedimentoLabel" for="pontosProcedimento">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.pontosProcedimento">Pontos Procedimento</Translate>
                  </Label>
                  <AvField
                    id="item-avaliado-pontosProcedimento"
                    type="string"
                    className="form-control"
                    name="pontosProcedimento"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pontosPessoaLabel" for="pontosPessoa">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.pontosPessoa">Pontos Pessoa</Translate>
                  </Label>
                  <AvField
                    id="item-avaliado-pontosPessoa"
                    type="string"
                    className="form-control"
                    name="pontosPessoa"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pontosProcessoLabel" for="pontosProcesso">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.pontosProcesso">Pontos Processo</Translate>
                  </Label>
                  <AvField
                    id="item-avaliado-pontosProcesso"
                    type="string"
                    className="form-control"
                    name="pontosProcesso"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pontosProdutoLabel" for="pontosProduto">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.pontosProduto">Pontos Produto</Translate>
                  </Label>
                  <AvField
                    id="item-avaliado-pontosProduto"
                    type="string"
                    className="form-control"
                    name="pontosProduto"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pontosObtidosProcedimentoLabel" for="pontosObtidosProcedimento">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.pontosObtidosProcedimento">
                      Pontos Obtidos Procedimento
                    </Translate>
                  </Label>
                  <AvField
                    id="item-avaliado-pontosObtidosProcedimento"
                    type="string"
                    className="form-control"
                    name="pontosObtidosProcedimento"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pontosObtidosPessoaLabel" for="pontosObtidosPessoa">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.pontosObtidosPessoa">Pontos Obtidos Pessoa</Translate>
                  </Label>
                  <AvField id="item-avaliado-pontosObtidosPessoa" type="string" className="form-control" name="pontosObtidosPessoa" />
                </AvGroup>
                <AvGroup>
                  <Label id="pontosObtidosProcessoLabel" for="pontosObtidosProcesso">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.pontosObtidosProcesso">Pontos Obtidos Processo</Translate>
                  </Label>
                  <AvField id="item-avaliado-pontosObtidosProcesso" type="string" className="form-control" name="pontosObtidosProcesso" />
                </AvGroup>
                <AvGroup>
                  <Label id="pontosObtidosProdutoLabel" for="pontosObtidosProduto">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.pontosObtidosProduto">Pontos Obtidos Produto</Translate>
                  </Label>
                  <AvField id="item-avaliado-pontosObtidosProduto" type="string" className="form-control" name="pontosObtidosProduto" />
                </AvGroup>
                <AvGroup>
                  <Label for="itemAvaliacao.descricao">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.itemAvaliacao">Item Avaliacao</Translate>
                  </Label>
                  <AvInput id="item-avaliado-itemAvaliacao" type="select" className="form-control" name="itemAvaliacaoId">
                    {itemAvaliacaos
                      ? itemAvaliacaos.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.descricao}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="avaliacao.id">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.avaliacao">Avaliacao</Translate>
                  </Label>
                  <AvInput id="item-avaliado-avaliacao" type="select" className="form-control" name="avaliacaoId">
                    {avaliacaos
                      ? avaliacaos.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/item-avaliado" replace color="info">
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
  itemAvaliacaos: storeState.itemAvaliacao.entities,
  avaliacaos: storeState.avaliacao.entities,
  itemAvaliadoEntity: storeState.itemAvaliado.entity,
  loading: storeState.itemAvaliado.loading,
  updating: storeState.itemAvaliado.updating,
  updateSuccess: storeState.itemAvaliado.updateSuccess
});

const mapDispatchToProps = {
  getItemAvaliacaos,
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
)(ItemAvaliadoUpdate);
