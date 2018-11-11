import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './item-avaliado.reducer';
import { IItemAvaliado } from 'app/shared/model/item-avaliado.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IItemAvaliadoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ItemAvaliado extends React.Component<IItemAvaliadoProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { itemAvaliadoList, match } = this.props;
    return (
      <div>
        <h2 id="item-avaliado-heading">
          <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.home.title">Item Avaliados</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.home.createLabel">Create new Item Avaliado</Translate>
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
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.respondidoEm">Respondido Em</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.ultimaAtualizacaoEm">Ultima Atualizacao Em</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.observacoes">Observacoes</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.latitudeLocalResposta">Latitude Local Resposta</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.longitudeLocalResposta">Longitude Local Resposta</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.avaliacao">Avaliacao</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.itemAvaliacao">Item Avaliacao</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {itemAvaliadoList.map((itemAvaliado, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${itemAvaliado.id}`} color="link" size="sm">
                      {itemAvaliado.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={itemAvaliado.respondidoEm} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={itemAvaliado.ultimaAtualizacaoEm} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <Translate contentKey={`dcpdesconformidadesApp.StatusItemAvaliado.${itemAvaliado.status}`} />
                  </td>
                  <td>{itemAvaliado.observacoes}</td>
                  <td>{itemAvaliado.latitudeLocalResposta}</td>
                  <td>{itemAvaliado.longitudeLocalResposta}</td>
                  <td>
                    {itemAvaliado.avaliacao ? <Link to={`avaliacao/${itemAvaliado.avaliacao.id}`}>{itemAvaliado.avaliacao.id}</Link> : ''}
                  </td>
                  <td>
                    {itemAvaliado.itemAvaliacao ? (
                      <Link to={`item-avaliacao/${itemAvaliado.itemAvaliacao.id}`}>{itemAvaliado.itemAvaliacao.descricao}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${itemAvaliado.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${itemAvaliado.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${itemAvaliado.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ itemAvaliado }: IRootState) => ({
  itemAvaliadoList: itemAvaliado.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ItemAvaliado);
