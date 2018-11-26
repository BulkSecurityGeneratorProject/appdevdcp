import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IGrupoItens } from 'app/shared/model/grupo-itens.model';
import { getEntities as getGrupoItens } from 'app/entities/grupo-itens/grupo-itens.reducer';
import { getEntity, updateEntity, createEntity, reset } from './item-avaliacao.reducer';
import { IItemAvaliacao } from 'app/shared/model/item-avaliacao.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IItemAvaliacaoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IItemAvaliacaoUpdateState {
  isNew: boolean;
  grupoId: string;
}

export class ItemAvaliacaoUpdate extends React.Component<IItemAvaliacaoUpdateProps, IItemAvaliacaoUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      grupoId: '0',
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

    this.props.getGrupoItens();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { itemAvaliacaoEntity } = this.props;
      const entity = {
        ...itemAvaliacaoEntity,
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
    this.props.history.push('/entity/item-avaliacao');
  };

  render() {
    const { itemAvaliacaoEntity, grupoItens, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dcpdesconformidadesApp.itemAvaliacao.home.createOrEditLabel">
              <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.home.createOrEditLabel">Create or edit a ItemAvaliacao</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : itemAvaliacaoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="item-avaliacao-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="descricaoLabel" for="descricao">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.descricao">Descricao</Translate>
                  </Label>
                  <AvField
                    id="item-avaliacao-descricao"
                    type="text"
                    name="descricao"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="anexoObrigatorioLabel" check>
                    <AvInput id="item-avaliacao-anexoObrigatorio" type="checkbox" className="form-control" name="anexoObrigatorio" />
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.anexoObrigatorio">Anexo Obrigatorio</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="pontosProcedimentoLabel" for="pontosProcedimento">
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.pontosProcedimento">Pontos Procedimento</Translate>
                  </Label>
                  <AvField
                    id="item-avaliacao-pontosProcedimento"
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
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.pontosPessoa">Pontos Pessoa</Translate>
                  </Label>
                  <AvField
                    id="item-avaliacao-pontosPessoa"
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
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.pontosProcesso">Pontos Processo</Translate>
                  </Label>
                  <AvField
                    id="item-avaliacao-pontosProcesso"
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
                    <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.pontosProduto">Pontos Produto</Translate>
                  </Label>
                  <AvField
                    id="item-avaliacao-pontosProduto"
                    type="string"
                    className="form-control"
                    name="pontosProduto"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/item-avaliacao" replace color="info">
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
  grupoItens: storeState.grupoItens.entities,
  itemAvaliacaoEntity: storeState.itemAvaliacao.entity,
  loading: storeState.itemAvaliacao.loading,
  updating: storeState.itemAvaliacao.updating,
  updateSuccess: storeState.itemAvaliacao.updateSuccess
});

const mapDispatchToProps = {
  getGrupoItens,
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
)(ItemAvaliacaoUpdate);
