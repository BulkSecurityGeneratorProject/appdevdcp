import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './item-auditado.reducer';
import { IItemAuditado } from 'app/shared/model/item-auditado.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IItemAuditadoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ItemAuditadoDetail extends React.Component<IItemAuditadoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { itemAuditadoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dcpdesconformidadesApp.itemAuditado.detail.title">ItemAuditado</Translate> [
            <b>{itemAuditadoEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="respondidoEm">
                <Translate contentKey="dcpdesconformidadesApp.itemAuditado.respondidoEm">Respondido Em</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={itemAuditadoEntity.respondidoEm} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="ultimaAtualizacaoEm">
                <Translate contentKey="dcpdesconformidadesApp.itemAuditado.ultimaAtualizacaoEm">Ultima Atualizacao Em</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={itemAuditadoEntity.ultimaAtualizacaoEm} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="tipo">
                <Translate contentKey="dcpdesconformidadesApp.itemAuditado.tipo">Tipo</Translate>
              </span>
            </dt>
            <dd>{itemAuditadoEntity.tipo}</dd>
            <dt>
              <span id="departamento">
                <Translate contentKey="dcpdesconformidadesApp.itemAuditado.departamento">Departamento</Translate>
              </span>
            </dt>
            <dd>{itemAuditadoEntity.departamento}</dd>
            <dt>
              <span id="codigoSap">
                <Translate contentKey="dcpdesconformidadesApp.itemAuditado.codigoSap">Codigo Sap</Translate>
              </span>
            </dt>
            <dd>{itemAuditadoEntity.codigoSap}</dd>
            <dt>
              <span id="descricaoItem">
                <Translate contentKey="dcpdesconformidadesApp.itemAuditado.descricaoItem">Descricao Item</Translate>
              </span>
            </dt>
            <dd>{itemAuditadoEntity.descricaoItem}</dd>
            <dt>
              <span id="saldoSap">
                <Translate contentKey="dcpdesconformidadesApp.itemAuditado.saldoSap">Saldo Sap</Translate>
              </span>
            </dt>
            <dd>{itemAuditadoEntity.saldoSap}</dd>
            <dt>
              <span id="saldoFisico">
                <Translate contentKey="dcpdesconformidadesApp.itemAuditado.saldoFisico">Saldo Fisico</Translate>
              </span>
            </dt>
            <dd>{itemAuditadoEntity.saldoFisico}</dd>
            <dt>
              <span id="motivoDivergencia">
                <Translate contentKey="dcpdesconformidadesApp.itemAuditado.motivoDivergencia">Motivo Divergencia</Translate>
              </span>
            </dt>
            <dd>{itemAuditadoEntity.motivoDivergencia}</dd>
            <dt>
              <Translate contentKey="dcpdesconformidadesApp.itemAuditado.avaliacao">Avaliacao</Translate>
            </dt>
            <dd>{itemAuditadoEntity.avaliacao ? itemAuditadoEntity.avaliacao.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/item-auditado" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/item-auditado/${itemAuditadoEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ itemAuditado }: IRootState) => ({
  itemAuditadoEntity: itemAuditado.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ItemAuditadoDetail);
