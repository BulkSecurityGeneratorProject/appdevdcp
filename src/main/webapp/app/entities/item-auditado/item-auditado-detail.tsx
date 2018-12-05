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
              <span id="codigoDepartamento">
                <Translate contentKey="dcpdesconformidadesApp.itemAuditado.codigoDepartamento">Codigo Departamento</Translate>
              </span>
            </dt>
            <dd>{itemAuditadoEntity.codigoDepartamento}</dd>
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
              <span id="saldoSap0001">
                <Translate contentKey="dcpdesconformidadesApp.itemAuditado.saldoSap0001">Saldo Sap 0001</Translate>
              </span>
            </dt>
            <dd>{itemAuditadoEntity.saldoSap0001}</dd>
            <dt>
              <span id="saldoFisico0001">
                <Translate contentKey="dcpdesconformidadesApp.itemAuditado.saldoFisico0001">Saldo Fisico 0001</Translate>
              </span>
            </dt>
            <dd>{itemAuditadoEntity.saldoFisico0001}</dd>
            <dt>
              <span id="saldoSap9000">
                <Translate contentKey="dcpdesconformidadesApp.itemAuditado.saldoSap9000">Saldo Sap 9000</Translate>
              </span>
            </dt>
            <dd>{itemAuditadoEntity.saldoSap9000}</dd>
            <dt>
              <span id="saldoFisico9000">
                <Translate contentKey="dcpdesconformidadesApp.itemAuditado.saldoFisico9000">Saldo Fisico 9000</Translate>
              </span>
            </dt>
            <dd>{itemAuditadoEntity.saldoFisico9000}</dd>
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
