import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './item-solicitado-ajuste.reducer';
import { IItemSolicitadoAjuste } from 'app/shared/model/item-solicitado-ajuste.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IItemSolicitadoAjusteDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ItemSolicitadoAjusteDetail extends React.Component<IItemSolicitadoAjusteDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { itemSolicitadoAjusteEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.detail.title">ItemSolicitadoAjuste</Translate> [
            <b>{itemSolicitadoAjusteEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="respondidoEm">
                <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.respondidoEm">Respondido Em</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={itemSolicitadoAjusteEntity.respondidoEm} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="ultimaAtualizacaoEm">
                <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.ultimaAtualizacaoEm">Ultima Atualizacao Em</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={itemSolicitadoAjusteEntity.ultimaAtualizacaoEm} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="departamento">
                <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.departamento">Departamento</Translate>
              </span>
            </dt>
            <dd>{itemSolicitadoAjusteEntity.departamento}</dd>
            <dt>
              <span id="codigoSap">
                <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.codigoSap">Codigo Sap</Translate>
              </span>
            </dt>
            <dd>{itemSolicitadoAjusteEntity.codigoSap}</dd>
            <dt>
              <span id="descricaoItem">
                <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.descricaoItem">Descricao Item</Translate>
              </span>
            </dt>
            <dd>{itemSolicitadoAjusteEntity.descricaoItem}</dd>
            <dt>
              <span id="saldoSap">
                <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.saldoSap">Saldo Sap</Translate>
              </span>
            </dt>
            <dd>{itemSolicitadoAjusteEntity.saldoSap}</dd>
            <dt>
              <span id="saldoFisico">
                <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.saldoFisico">Saldo Fisico</Translate>
              </span>
            </dt>
            <dd>{itemSolicitadoAjusteEntity.saldoFisico}</dd>
            <dt>
              <span id="motivoDivergencia">
                <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.motivoDivergencia">Motivo Divergencia</Translate>
              </span>
            </dt>
            <dd>{itemSolicitadoAjusteEntity.motivoDivergencia}</dd>
            <dt>
              <span id="acaoCorretivaOuPreventiva">
                <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.acaoCorretivaOuPreventiva">
                  Acao Corretiva Ou Preventiva
                </Translate>
              </span>
            </dt>
            <dd>{itemSolicitadoAjusteEntity.acaoCorretivaOuPreventiva}</dd>
            <dt>
              <span id="responsavel">
                <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.responsavel">Responsavel</Translate>
              </span>
            </dt>
            <dd>{itemSolicitadoAjusteEntity.responsavel}</dd>
            <dt>
              <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.avaliacao">Avaliacao</Translate>
            </dt>
            <dd>{itemSolicitadoAjusteEntity.avaliacao ? itemSolicitadoAjusteEntity.avaliacao.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/item-solicitado-ajuste" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/item-solicitado-ajuste/${itemSolicitadoAjusteEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ itemSolicitadoAjuste }: IRootState) => ({
  itemSolicitadoAjusteEntity: itemSolicitadoAjuste.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ItemSolicitadoAjusteDetail);
