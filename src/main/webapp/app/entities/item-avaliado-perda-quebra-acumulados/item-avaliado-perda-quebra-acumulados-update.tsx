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
import { getEntity, updateEntity, createEntity, reset } from './item-avaliado-perda-quebra-acumulados.reducer';
import { IItemAvaliadoPerdaQuebraAcumulados } from 'app/shared/model/item-avaliado-perda-quebra-acumulados.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IItemAvaliadoPerdaQuebraAcumuladosUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IItemAvaliadoPerdaQuebraAcumuladosUpdateState {
  isNew: boolean;
  avaliacaoId: string;
}

export class ItemAvaliadoPerdaQuebraAcumuladosUpdate extends React.Component<
  IItemAvaliadoPerdaQuebraAcumuladosUpdateProps,
  IItemAvaliadoPerdaQuebraAcumuladosUpdateState
> {
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
      const { itemAvaliadoPerdaQuebraAcumuladosEntity } = this.props;
      const entity = {
        ...itemAvaliadoPerdaQuebraAcumuladosEntity,
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
    this.props.history.push('/entity/item-avaliado-perda-quebra-acumulados');
  };

  render() {
    const { itemAvaliadoPerdaQuebraAcumuladosEntity, avaliacaos, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.home.createOrEditLabel">
              <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.home.createOrEditLabel">
                Create or edit a ItemAvaliadoPerdaQuebraAcumulados
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : itemAvaliadoPerdaQuebraAcumuladosEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput
                      id="item-avaliado-perda-quebra-acumulados-id"
                      type="text"
                      className="form-control"
                      name="id"
                      required
                      readOnly
                    />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="tipoLabel">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.tipo">Tipo</Translate>
                  </Label>
                  <AvInput
                    id="item-avaliado-perda-quebra-acumulados-tipo"
                    type="select"
                    className="form-control"
                    name="tipo"
                    value={(!isNew && itemAvaliadoPerdaQuebraAcumuladosEntity.tipo) || 'PERDA'}
                  >
                    <option value="PERDA">
                      <Translate contentKey="dcpdesconformidadesApp.TipoItemAvaliadoPerdaQuebra.PERDA" />
                    </option>
                    <option value="QUEBRA">
                      <Translate contentKey="dcpdesconformidadesApp.TipoItemAvaliadoPerdaQuebra.QUEBRA" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="respondidoEmLabel" for="respondidoEm">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.respondidoEm">Respondido Em</Translate>
                  </Label>
                  <AvInput
                    id="item-avaliado-perda-quebra-acumulados-respondidoEm"
                    type="datetime-local"
                    className="form-control"
                    name="respondidoEm"
                    value={isNew ? null : convertDateTimeFromServer(this.props.itemAvaliadoPerdaQuebraAcumuladosEntity.respondidoEm)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ultimaAtualizacaoEmLabel" for="ultimaAtualizacaoEm">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.ultimaAtualizacaoEm">
                      Ultima Atualizacao Em
                    </Translate>
                  </Label>
                  <AvInput
                    id="item-avaliado-perda-quebra-acumulados-ultimaAtualizacaoEm"
                    type="datetime-local"
                    className="form-control"
                    name="ultimaAtualizacaoEm"
                    value={isNew ? null : convertDateTimeFromServer(this.props.itemAvaliadoPerdaQuebraAcumuladosEntity.ultimaAtualizacaoEm)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="percentualLabel" for="percentual">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.percentual">Percentual</Translate>
                  </Label>
                  <AvField
                    id="item-avaliado-perda-quebra-acumulados-percentual"
                    type="string"
                    className="form-control"
                    name="percentual"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="financeiroLabel" for="financeiro">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.financeiro">Financeiro</Translate>
                  </Label>
                  <AvField
                    id="item-avaliado-perda-quebra-acumulados-financeiro"
                    type="text"
                    name="financeiro"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pontuacaoLabel" for="pontuacao">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.pontuacao">Pontuacao</Translate>
                  </Label>
                  <AvField
                    id="item-avaliado-perda-quebra-acumulados-pontuacao"
                    type="string"
                    className="form-control"
                    name="pontuacao"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="latitudeLocalRespostaLabel" for="latitudeLocalResposta">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.latitudeLocalResposta">
                      Latitude Local Resposta
                    </Translate>
                  </Label>
                  <AvField
                    id="item-avaliado-perda-quebra-acumulados-latitudeLocalResposta"
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
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.longitudeLocalResposta">
                      Longitude Local Resposta
                    </Translate>
                  </Label>
                  <AvField
                    id="item-avaliado-perda-quebra-acumulados-longitudeLocalResposta"
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
                  <Label id="classificacaoLabel">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.classificacao">Classificacao</Translate>
                  </Label>
                  <AvInput
                    id="item-avaliado-perda-quebra-acumulados-classificacao"
                    type="select"
                    className="form-control"
                    name="classificacao"
                    value={(!isNew && itemAvaliadoPerdaQuebraAcumuladosEntity.classificacao) || 'CONFORMIDADE'}
                  >
                    <option value="CONFORMIDADE">
                      <Translate contentKey="dcpdesconformidadesApp.ClassificacaoPerdaQuebra.CONFORMIDADE" />
                    </option>
                    <option value="DESCONFORMIDADE">
                      <Translate contentKey="dcpdesconformidadesApp.ClassificacaoPerdaQuebra.DESCONFORMIDADE" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="categorizacaoLabel">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.categorizacao">Categorizacao</Translate>
                  </Label>
                  <AvInput
                    id="item-avaliado-perda-quebra-acumulados-categorizacao"
                    type="select"
                    className="form-control"
                    name="categorizacao"
                    value={(!isNew && itemAvaliadoPerdaQuebraAcumuladosEntity.categorizacao) || 'INADMISSIVEL'}
                  >
                    <option value="INADMISSIVEL">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.INADMISSIVEL" />
                    </option>
                    <option value="CRITICO">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.CRITICO" />
                    </option>
                    <option value="VALOR_ELEVADO">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.VALOR_ELEVADO" />
                    </option>
                    <option value="ATENCAO">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.ATENCAO" />
                    </option>
                    <option value="CONTROLE">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.CONTROLE" />
                    </option>
                    <option value="SOBRA_DESCONTROLE">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.SOBRA_DESCONTROLE" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="avaliacao.id">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.avaliacao">Avaliacao</Translate>
                  </Label>
                  <AvInput
                    id="item-avaliado-perda-quebra-acumulados-avaliacao"
                    type="select"
                    className="form-control"
                    name="avaliacao.id"
                    value={isNew ? avaliacaos[0] && avaliacaos[0].id : itemAvaliadoPerdaQuebraAcumuladosEntity.avaliacao.id}
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
                <Button tag={Link} id="cancel-save" to="/entity/item-avaliado-perda-quebra-acumulados" replace color="info">
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
  itemAvaliadoPerdaQuebraAcumuladosEntity: storeState.itemAvaliadoPerdaQuebraAcumulados.entity,
  loading: storeState.itemAvaliadoPerdaQuebraAcumulados.loading,
  updating: storeState.itemAvaliadoPerdaQuebraAcumulados.updating,
  updateSuccess: storeState.itemAvaliadoPerdaQuebraAcumulados.updateSuccess
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
)(ItemAvaliadoPerdaQuebraAcumuladosUpdate);
