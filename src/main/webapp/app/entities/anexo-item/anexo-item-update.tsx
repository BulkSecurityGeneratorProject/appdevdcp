import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IItemAvaliado } from 'app/shared/model/item-avaliado.model';
import { getEntities as getItemAvaliados } from 'app/entities/item-avaliado/item-avaliado.reducer';
import { getEntity, updateEntity, createEntity, reset } from './anexo-item.reducer';
import { IAnexoItem } from 'app/shared/model/anexo-item.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAnexoItemUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAnexoItemUpdateState {
  isNew: boolean;
  itemAvaliadoId: string;
}

export class AnexoItemUpdate extends React.Component<IAnexoItemUpdateProps, IAnexoItemUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      itemAvaliadoId: '0',
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

    this.props.getItemAvaliados();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { anexoItemEntity } = this.props;
      const entity = {
        ...anexoItemEntity,
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
    this.props.history.push('/entity/anexo-item');
  };

  render() {
    const { anexoItemEntity, itemAvaliados, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dcpdesconformidadesApp.anexoItem.home.createOrEditLabel">
              <Translate contentKey="dcpdesconformidadesApp.anexoItem.home.createOrEditLabel">Create or edit a AnexoItem</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : anexoItemEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="anexo-item-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="tipoLabel">
                    <Translate contentKey="dcpdesconformidadesApp.anexoItem.tipo">Tipo</Translate>
                  </Label>
                  <AvInput
                    id="anexo-item-tipo"
                    type="select"
                    className="form-control"
                    name="tipo"
                    value={(!isNew && anexoItemEntity.tipo) || 'IMAGEM'}
                  >
                    <option value="IMAGEM">
                      <Translate contentKey="dcpdesconformidadesApp.TipoAnexoItem.IMAGEM" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="caminhoArquivoLabel" for="caminhoArquivo">
                    <Translate contentKey="dcpdesconformidadesApp.anexoItem.caminhoArquivo">Caminho Arquivo</Translate>
                  </Label>
                  <AvField
                    id="anexo-item-caminhoArquivo"
                    type="text"
                    name="caminhoArquivo"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="itemAvaliado.id">
                    <Translate contentKey="dcpdesconformidadesApp.anexoItem.itemAvaliado">Item Avaliado</Translate>
                  </Label>
                  <AvInput id="anexo-item-itemAvaliado" type="select" className="form-control" name="itemAvaliadoId">
                    {itemAvaliados
                      ? itemAvaliados.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/anexo-item" replace color="info">
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
  itemAvaliados: storeState.itemAvaliado.entities,
  anexoItemEntity: storeState.anexoItem.entity,
  loading: storeState.anexoItem.loading,
  updating: storeState.anexoItem.updating,
  updateSuccess: storeState.anexoItem.updateSuccess
});

const mapDispatchToProps = {
  getItemAvaliados,
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
)(AnexoItemUpdate);
