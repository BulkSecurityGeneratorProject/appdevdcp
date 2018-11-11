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
import { getEntity, updateEntity, createEntity, reset } from './questionario.reducer';
import { IQuestionario } from 'app/shared/model/questionario.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IQuestionarioUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IQuestionarioUpdateState {
  isNew: boolean;
  idsgrupo: any[];
}

export class QuestionarioUpdate extends React.Component<IQuestionarioUpdateProps, IQuestionarioUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idsgrupo: [],
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
    values.criadoEm = new Date(values.criadoEm);

    if (errors.length === 0) {
      const { questionarioEntity } = this.props;
      const entity = {
        ...questionarioEntity,
        ...values,
        grupos: mapIdList(values.grupos)
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/questionario');
  };

  render() {
    const { questionarioEntity, grupoItens, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dcpdesconformidadesApp.questionario.home.createOrEditLabel">
              <Translate contentKey="dcpdesconformidadesApp.questionario.home.createOrEditLabel">Create or edit a Questionario</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : questionarioEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="questionario-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nomeLabel" for="nome">
                    <Translate contentKey="dcpdesconformidadesApp.questionario.nome">Nome</Translate>
                  </Label>
                  <AvField
                    id="questionario-nome"
                    type="text"
                    name="nome"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="descricaoLabel" for="descricao">
                    <Translate contentKey="dcpdesconformidadesApp.questionario.descricao">Descricao</Translate>
                  </Label>
                  <AvField id="questionario-descricao" type="text" name="descricao" />
                </AvGroup>
                <AvGroup>
                  <Label id="ativoLabel" check>
                    <AvInput id="questionario-ativo" type="checkbox" className="form-control" name="ativo" />
                    <Translate contentKey="dcpdesconformidadesApp.questionario.ativo">Ativo</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="criadoEmLabel" for="criadoEm">
                    <Translate contentKey="dcpdesconformidadesApp.questionario.criadoEm">Criado Em</Translate>
                  </Label>
                  <AvInput
                    id="questionario-criadoEm"
                    type="datetime-local"
                    className="form-control"
                    name="criadoEm"
                    value={isNew ? null : convertDateTimeFromServer(this.props.questionarioEntity.criadoEm)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="grupoItens">
                    <Translate contentKey="dcpdesconformidadesApp.questionario.grupo">Grupo</Translate>
                  </Label>
                  <AvInput
                    id="questionario-grupo"
                    type="select"
                    multiple
                    className="form-control"
                    name="grupos"
                    value={questionarioEntity.grupos && questionarioEntity.grupos.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {grupoItens
                      ? grupoItens.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.nome}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/questionario" replace color="info">
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
  questionarioEntity: storeState.questionario.entity,
  loading: storeState.questionario.loading,
  updating: storeState.questionario.updating,
  updateSuccess: storeState.questionario.updateSuccess
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
)(QuestionarioUpdate);
