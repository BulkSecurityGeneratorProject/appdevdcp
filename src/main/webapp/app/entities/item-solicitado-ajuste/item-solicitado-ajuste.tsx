import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './item-solicitado-ajuste.reducer';
import { IItemSolicitadoAjuste } from 'app/shared/model/item-solicitado-ajuste.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IItemSolicitadoAjusteProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ItemSolicitadoAjuste extends React.Component<IItemSolicitadoAjusteProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { itemSolicitadoAjusteList, match } = this.props;
    return (
      <div>
        <h2 id="item-solicitado-ajuste-heading">
          <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.home.title">Item Solicitado Ajustes</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.home.createLabel">
              Create new Item Solicitado Ajuste
            </Translate>
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
                  <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.respondidoEm">Respondido Em</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.ultimaAtualizacaoEm">Ultima Atualizacao Em</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.codigoDepartamento">Codigo Departamento</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.codigoSap">Codigo Sap</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.descricaoItem">Descricao Item</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.saldoSap0001">Saldo Sap 0001</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.saldoFisico0001">Saldo Fisico 0001</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.saldoSap9000">Saldo Sap 9000</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.saldoFisico9000">Saldo Fisico 9000</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.motivoDivergencia">Motivo Divergencia</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.acaoCorretivaOuPreventiva">
                    Acao Corretiva Ou Preventiva
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.responsavel">Responsavel</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.avaliacao">Avaliacao</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {itemSolicitadoAjusteList.map((itemSolicitadoAjuste, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${itemSolicitadoAjuste.id}`} color="link" size="sm">
                      {itemSolicitadoAjuste.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={itemSolicitadoAjuste.respondidoEm} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={itemSolicitadoAjuste.ultimaAtualizacaoEm} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{itemSolicitadoAjuste.codigoDepartamento}</td>
                  <td>{itemSolicitadoAjuste.codigoSap}</td>
                  <td>{itemSolicitadoAjuste.descricaoItem}</td>
                  <td>{itemSolicitadoAjuste.saldoSap0001}</td>
                  <td>{itemSolicitadoAjuste.saldoFisico0001}</td>
                  <td>{itemSolicitadoAjuste.saldoSap9000}</td>
                  <td>{itemSolicitadoAjuste.saldoFisico9000}</td>
                  <td>{itemSolicitadoAjuste.motivoDivergencia}</td>
                  <td>{itemSolicitadoAjuste.acaoCorretivaOuPreventiva}</td>
                  <td>{itemSolicitadoAjuste.responsavel}</td>
                  <td>
                    {itemSolicitadoAjuste.avaliacao ? (
                      <Link to={`avaliacao/${itemSolicitadoAjuste.avaliacao.id}`}>{itemSolicitadoAjuste.avaliacao.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${itemSolicitadoAjuste.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${itemSolicitadoAjuste.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${itemSolicitadoAjuste.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ itemSolicitadoAjuste }: IRootState) => ({
  itemSolicitadoAjusteList: itemSolicitadoAjuste.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ItemSolicitadoAjuste);
