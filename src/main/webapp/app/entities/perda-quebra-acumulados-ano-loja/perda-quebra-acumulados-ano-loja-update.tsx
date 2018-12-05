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
import { getEntity, updateEntity, createEntity, reset } from './perda-quebra-acumulados-ano-loja.reducer';
import { IPerdaQuebraAcumuladosAnoLoja } from 'app/shared/model/perda-quebra-acumulados-ano-loja.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPerdaQuebraAcumuladosAnoLojaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPerdaQuebraAcumuladosAnoLojaUpdateState {
  isNew: boolean;
  lojaId: string;
}

export class PerdaQuebraAcumuladosAnoLojaUpdate extends React.Component<
  IPerdaQuebraAcumuladosAnoLojaUpdateProps,
  IPerdaQuebraAcumuladosAnoLojaUpdateState
> {
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
      const { perdaQuebraAcumuladosAnoLojaEntity } = this.props;
      const entity = {
        ...perdaQuebraAcumuladosAnoLojaEntity,
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
    this.props.history.push('/entity/perda-quebra-acumulados-ano-loja');
  };

  render() {
    const { perdaQuebraAcumuladosAnoLojaEntity, lojas, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.home.createOrEditLabel">
              <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.home.createOrEditLabel">
                Create or edit a PerdaQuebraAcumuladosAnoLoja
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : perdaQuebraAcumuladosAnoLojaEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="perda-quebra-acumulados-ano-loja-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="anoLabel" for="ano">
                    <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.ano">Ano</Translate>
                  </Label>
                  <AvField
                    id="perda-quebra-acumulados-ano-loja-ano"
                    type="string"
                    className="form-control"
                    name="ano"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="percentualPerdaLabel" for="percentualPerda">
                    <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.percentualPerda">Percentual Perda</Translate>
                  </Label>
                  <AvField
                    id="perda-quebra-acumulados-ano-loja-percentualPerda"
                    type="string"
                    className="form-control"
                    name="percentualPerda"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="financeiroPerdaLabel" for="financeiroPerda">
                    <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.financeiroPerda">Financeiro Perda</Translate>
                  </Label>
                  <AvField
                    id="perda-quebra-acumulados-ano-loja-financeiroPerda"
                    type="text"
                    name="financeiroPerda"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pontuacaoPerdaLabel" for="pontuacaoPerda">
                    <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.pontuacaoPerda">Pontuacao Perda</Translate>
                  </Label>
                  <AvField
                    id="perda-quebra-acumulados-ano-loja-pontuacaoPerda"
                    type="string"
                    className="form-control"
                    name="pontuacaoPerda"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusPerdaLabel">
                    <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.statusPerda">Status Perda</Translate>
                  </Label>
                  <AvInput
                    id="perda-quebra-acumulados-ano-loja-statusPerda"
                    type="select"
                    className="form-control"
                    name="statusPerda"
                    value={(!isNew && perdaQuebraAcumuladosAnoLojaEntity.statusPerda) || 'OK'}
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
                  <Label id="categorizacaoPerdaLabel">
                    <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.categorizacaoPerda">
                      Categorizacao Perda
                    </Translate>
                  </Label>
                  <AvInput
                    id="perda-quebra-acumulados-ano-loja-categorizacaoPerda"
                    type="select"
                    className="form-control"
                    name="categorizacaoPerda"
                    value={(!isNew && perdaQuebraAcumuladosAnoLojaEntity.categorizacaoPerda) || 'INADMISSIVEL'}
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
                  <Label id="percentualQuebraLabel" for="percentualQuebra">
                    <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.percentualQuebra">
                      Percentual Quebra
                    </Translate>
                  </Label>
                  <AvField
                    id="perda-quebra-acumulados-ano-loja-percentualQuebra"
                    type="string"
                    className="form-control"
                    name="percentualQuebra"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="financeiroQuebraLabel" for="financeiroQuebra">
                    <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.financeiroQuebra">
                      Financeiro Quebra
                    </Translate>
                  </Label>
                  <AvField
                    id="perda-quebra-acumulados-ano-loja-financeiroQuebra"
                    type="text"
                    name="financeiroQuebra"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pontuacaoQuebraLabel" for="pontuacaoQuebra">
                    <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.pontuacaoQuebra">Pontuacao Quebra</Translate>
                  </Label>
                  <AvField
                    id="perda-quebra-acumulados-ano-loja-pontuacaoQuebra"
                    type="string"
                    className="form-control"
                    name="pontuacaoQuebra"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusQuebraLabel">
                    <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.statusQuebra">Status Quebra</Translate>
                  </Label>
                  <AvInput
                    id="perda-quebra-acumulados-ano-loja-statusQuebra"
                    type="select"
                    className="form-control"
                    name="statusQuebra"
                    value={(!isNew && perdaQuebraAcumuladosAnoLojaEntity.statusQuebra) || 'OK'}
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
                  <Label id="categorizacaoQuebraLabel">
                    <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.categorizacaoQuebra">
                      Categorizacao Quebra
                    </Translate>
                  </Label>
                  <AvInput
                    id="perda-quebra-acumulados-ano-loja-categorizacaoQuebra"
                    type="select"
                    className="form-control"
                    name="categorizacaoQuebra"
                    value={(!isNew && perdaQuebraAcumuladosAnoLojaEntity.categorizacaoQuebra) || 'INADMISSIVEL'}
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
                  <Label for="loja.nome">
                    <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.loja">Loja</Translate>
                  </Label>
                  <AvInput
                    id="perda-quebra-acumulados-ano-loja-loja"
                    type="select"
                    className="form-control"
                    name="loja.id"
                    value={isNew ? lojas[0] && lojas[0].id : perdaQuebraAcumuladosAnoLojaEntity.loja.id}
                  >
                    {lojas
                      ? lojas.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.nome}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/perda-quebra-acumulados-ano-loja" replace color="info">
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
  perdaQuebraAcumuladosAnoLojaEntity: storeState.perdaQuebraAcumuladosAnoLoja.entity,
  loading: storeState.perdaQuebraAcumuladosAnoLoja.loading,
  updating: storeState.perdaQuebraAcumuladosAnoLoja.updating,
  updateSuccess: storeState.perdaQuebraAcumuladosAnoLoja.updateSuccess
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
)(PerdaQuebraAcumuladosAnoLojaUpdate);
