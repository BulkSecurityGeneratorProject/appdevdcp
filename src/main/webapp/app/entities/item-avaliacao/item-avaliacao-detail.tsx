import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './item-avaliacao.reducer';
import { IItemAvaliacao } from 'app/shared/model/item-avaliacao.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IItemAvaliacaoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ItemAvaliacaoDetail extends React.Component<IItemAvaliacaoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { itemAvaliacaoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.detail.title">ItemAvaliacao</Translate> [
            <b>{itemAvaliacaoEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="descricao">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.descricao">Descricao</Translate>
              </span>
            </dt>
            <dd>{itemAvaliacaoEntity.descricao}</dd>
            <dt>
              <span id="anexoObrigatorio">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.anexoObrigatorio">Anexo Obrigatorio</Translate>
              </span>
            </dt>
            <dd>{itemAvaliacaoEntity.anexoObrigatorio ? 'true' : 'false'}</dd>
            <dt>
              <span id="criadoEm">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.criadoEm">Criado Em</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={itemAvaliacaoEntity.criadoEm} type="date" format={APP_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/item-avaliacao" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/item-avaliacao/${itemAvaliacaoEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ itemAvaliacao }: IRootState) => ({
  itemAvaliacaoEntity: itemAvaliacao.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ItemAvaliacaoDetail);
