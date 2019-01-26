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

import { IItemAvaliacao } from 'app/shared/model/item-avaliacao.model';
import { getEntities as getItemAvaliacaos } from 'app/entities/item-avaliacao/item-avaliacao.reducer';
import { IQuestionario } from 'app/shared/model/questionario.model';
import { getEntities as getQuestionarios } from 'app/entities/questionario/questionario.reducer';
import { getEntity, updateEntity, createEntity, reset } from './grupo-itens.reducer';
import { IGrupoItens } from 'app/shared/model/grupo-itens.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IGrupoItensUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IGrupoItensUpdateState {
  isNew: boolean;
  questionarioId: string;
  itens: IItemAvaliacao[];
}

export class GrupoItensUpdate extends React.Component<IGrupoItensUpdateProps, IGrupoItensUpdateState> {
  state: IGrupoItensUpdateState = {
    isNew: !this.props.match.params || !this.props.match.params.id,
    questionarioId: '0',
    itens: []
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

    this.props.getItemAvaliacaos();
    this.props.getQuestionarios();
  }

  componentWillReceiveProps(nextProps) {
    if (!this.state.isNew && nextProps.grupoItensEntity.itens && nextProps.grupoItensEntity.itens.length) {
      this.state.itens = nextProps.grupoItensEntity.itens.map(a => a.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { grupoItensEntity } = this.props;
      const entity = {
        ...grupoItensEntity,
        ...values,
        itens: mapIdList(values.itens)
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/grupo-itens');
  };

  handleItensChange = selectedOption => {
    this.setState({ itens: selectedOption });
  };

  render() {
    const { grupoItensEntity, itemAvaliacaos, questionarios, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dcpdesconformidadesApp.grupoItens.home.createOrEditLabel">
              <Translate contentKey="dcpdesconformidadesApp.grupoItens.home.createOrEditLabel">Create or edit a GrupoItens</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p className="loading-message" />
            ) : (
              <AvForm model={isNew ? {} : grupoItensEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="grupo-itens-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nomeLabel" for="nome">
                    <Translate contentKey="dcpdesconformidadesApp.grupoItens.nome">Nome</Translate>
                  </Label>
                  <AvField
                    id="grupo-itens-nome"
                    type="text"
                    name="nome"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                    readOnly={!this.state.isNew}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="questionario.nome">
                    <Translate contentKey="dcpdesconformidadesApp.grupoItens.questionario">Questionário</Translate>
                  </Label>
                  <AvInput id="grupo-itens-questionario" type="select" className="form-control" name="questionarioId">
                    <option value="" key="0" />
                    {questionarios
                      ? questionarios.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.nome}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="itemAvaliacaos">
                    <Translate contentKey="dcpdesconformidadesApp.grupoItens.itens">Itens</Translate>
                  </Label>
                  <AvSelect
                    placeholder="Selecione"
                    name="itens"
                    options={itemAvaliacaos}
                    value={this.state.itens}
                    onChange={this.handleItensChange}
                    labelKey="descricao"
                    valueKey="id"
                    isMulti
                    isSearchable
                    required
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/grupo-itens" replace color="info">
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
  questionarios: storeState.questionario.entities,
  grupoItensEntity: storeState.grupoItens.entity,
  loading: storeState.grupoItens.loading,
  updating: storeState.grupoItens.updating,
  updateSuccess: storeState.grupoItens.updateSuccess
});

const mapDispatchToProps = {
  getItemAvaliacaos,
  getQuestionarios,
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
)(GrupoItensUpdate);
