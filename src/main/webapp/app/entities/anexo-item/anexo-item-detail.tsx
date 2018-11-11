import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './anexo-item.reducer';
import { IAnexoItem } from 'app/shared/model/anexo-item.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAnexoItemDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AnexoItemDetail extends React.Component<IAnexoItemDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { anexoItemEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dcpdesconformidadesApp.anexoItem.detail.title">AnexoItem</Translate> [<b>{anexoItemEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="tipo">
                <Translate contentKey="dcpdesconformidadesApp.anexoItem.tipo">Tipo</Translate>
              </span>
            </dt>
            <dd>{anexoItemEntity.tipo}</dd>
            <dt>
              <span id="caminhoArquivo">
                <Translate contentKey="dcpdesconformidadesApp.anexoItem.caminhoArquivo">Caminho Arquivo</Translate>
              </span>
            </dt>
            <dd>{anexoItemEntity.caminhoArquivo}</dd>
            <dt>
              <Translate contentKey="dcpdesconformidadesApp.anexoItem.itemAvaliado">Item Avaliado</Translate>
            </dt>
            <dd>{anexoItemEntity.itemAvaliado ? anexoItemEntity.itemAvaliado.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/anexo-item" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/anexo-item/${anexoItemEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ anexoItem }: IRootState) => ({
  anexoItemEntity: anexoItem.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AnexoItemDetail);
