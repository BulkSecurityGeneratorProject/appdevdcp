import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './item-avaliado-perda-quebra-acumulados.reducer';
import { IItemAvaliadoPerdaQuebraAcumulados } from 'app/shared/model/item-avaliado-perda-quebra-acumulados.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IItemAvaliadoPerdaQuebraAcumuladosProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ItemAvaliadoPerdaQuebraAcumulados extends React.Component<IItemAvaliadoPerdaQuebraAcumuladosProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { itemAvaliadoPerdaQuebraAcumuladosList, match } = this.props;
    return (
      <div>
        <h2 id="item-avaliado-perda-quebra-acumulados-heading">
          <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.home.title">
            Item Avaliado Perda Quebra Acumulados
          </Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.home.createLabel">
              Create new Item Avaliado Perda Quebra Acumulados
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
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.tipo">Tipo</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.respondidoEm">Respondido Em</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.ultimaAtualizacaoEm">
                    Ultima Atualizacao Em
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.percentual">Percentual</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.financeiro">Financeiro</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.pontuacao">Pontuacao</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.latitudeLocalResposta">
                    Latitude Local Resposta
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.longitudeLocalResposta">
                    Longitude Local Resposta
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.avaliacao">Avaliacao</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {itemAvaliadoPerdaQuebraAcumuladosList.map((itemAvaliadoPerdaQuebraAcumulados, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${itemAvaliadoPerdaQuebraAcumulados.id}`} color="link" size="sm">
                      {itemAvaliadoPerdaQuebraAcumulados.id}
                    </Button>
                  </td>
                  <td>
                    <Translate
                      contentKey={`dcpdesconformidadesApp.TipoItemAvaliadoPerdaQuebra.${itemAvaliadoPerdaQuebraAcumulados.tipo}`}
                    />
                  </td>
                  <td>
                    <TextFormat type="date" value={itemAvaliadoPerdaQuebraAcumulados.respondidoEm} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={itemAvaliadoPerdaQuebraAcumulados.ultimaAtualizacaoEm} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{itemAvaliadoPerdaQuebraAcumulados.percentual}</td>
                  <td>{itemAvaliadoPerdaQuebraAcumulados.financeiro}</td>
                  <td>{itemAvaliadoPerdaQuebraAcumulados.pontuacao}</td>
                  <td>{itemAvaliadoPerdaQuebraAcumulados.latitudeLocalResposta}</td>
                  <td>{itemAvaliadoPerdaQuebraAcumulados.longitudeLocalResposta}</td>
                  <td>
                    {itemAvaliadoPerdaQuebraAcumulados.avaliacao ? (
                      <Link to={`avaliacao/${itemAvaliadoPerdaQuebraAcumulados.avaliacao.id}`}>
                        {itemAvaliadoPerdaQuebraAcumulados.avaliacao.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${itemAvaliadoPerdaQuebraAcumulados.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${itemAvaliadoPerdaQuebraAcumulados.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${itemAvaliadoPerdaQuebraAcumulados.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ itemAvaliadoPerdaQuebraAcumulados }: IRootState) => ({
  itemAvaliadoPerdaQuebraAcumuladosList: itemAvaliadoPerdaQuebraAcumulados.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ItemAvaliadoPerdaQuebraAcumulados);
