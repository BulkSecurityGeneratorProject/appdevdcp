import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './item-auditado.reducer';
import { IItemAuditado } from 'app/shared/model/item-auditado.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IItemAuditadoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ItemAuditado extends React.Component<IItemAuditadoProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { itemAuditadoList, match } = this.props;
    return (
      <div>
        <h2 id="item-auditado-heading">
          <Translate contentKey="dcpdesconformidadesApp.itemAuditado.home.title">Item Auditados</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="dcpdesconformidadesApp.itemAuditado.home.createLabel">Create new Item Auditado</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAuditado.respondidoEm">Respondido Em</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAuditado.ultimaAtualizacaoEm">Ultima Atualizacao Em</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAuditado.tipo">Tipo</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAuditado.departamento">Departamento</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAuditado.codigoSap">Codigo Sap</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAuditado.descricaoItem">Descricao Item</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAuditado.saldoSap">Saldo Sap</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAuditado.saldoFisico">Saldo Fisico</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAuditado.motivoDivergencia">Motivo Divergencia</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAuditado.avaliacao">Avaliacao</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {itemAuditadoList.map((itemAuditado, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${itemAuditado.id}`} color="link" size="sm">
                      {itemAuditado.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={itemAuditado.respondidoEm} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={itemAuditado.ultimaAtualizacaoEm} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <Translate contentKey={`dcpdesconformidadesApp.TipoItemAuditado.${itemAuditado.tipo}`} />
                  </td>
                  <td>{itemAuditado.departamento}</td>
                  <td>{itemAuditado.codigoSap}</td>
                  <td>{itemAuditado.descricaoItem}</td>
                  <td>{itemAuditado.saldoSap}</td>
                  <td>{itemAuditado.saldoFisico}</td>
                  <td>{itemAuditado.motivoDivergencia}</td>
                  <td>
                    {itemAuditado.avaliacao ? <Link to={`avaliacao/${itemAuditado.avaliacao.id}`}>{itemAuditado.avaliacao.id}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${itemAuditado.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${itemAuditado.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${itemAuditado.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ itemAuditado }: IRootState) => ({
  itemAuditadoList: itemAuditado.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ItemAuditado);
