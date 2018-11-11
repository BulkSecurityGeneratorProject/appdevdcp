import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './anexo-item.reducer';
import { IAnexoItem } from 'app/shared/model/anexo-item.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAnexoItemProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class AnexoItem extends React.Component<IAnexoItemProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { anexoItemList, match } = this.props;
    return (
      <div>
        <h2 id="anexo-item-heading">
          <Translate contentKey="dcpdesconformidadesApp.anexoItem.home.title">Anexo Items</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="dcpdesconformidadesApp.anexoItem.home.createLabel">Create new Anexo Item</Translate>
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
                  <Translate contentKey="dcpdesconformidadesApp.anexoItem.tipo">Tipo</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.anexoItem.caminhoArquivo">Caminho Arquivo</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.anexoItem.itemAvaliado">Item Avaliado</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {anexoItemList.map((anexoItem, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${anexoItem.id}`} color="link" size="sm">
                      {anexoItem.id}
                    </Button>
                  </td>
                  <td>
                    <Translate contentKey={`dcpdesconformidadesApp.TipoAnexoItem.${anexoItem.tipo}`} />
                  </td>
                  <td>{anexoItem.caminhoArquivo}</td>
                  <td>
                    {anexoItem.itemAvaliado ? (
                      <Link to={`item-avaliado/${anexoItem.itemAvaliado.id}`}>{anexoItem.itemAvaliado.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${anexoItem.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${anexoItem.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${anexoItem.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ anexoItem }: IRootState) => ({
  anexoItemList: anexoItem.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AnexoItem);
