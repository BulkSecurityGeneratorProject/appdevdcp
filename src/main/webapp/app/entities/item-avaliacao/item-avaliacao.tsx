import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './item-avaliacao.reducer';
import { IItemAvaliacao } from 'app/shared/model/item-avaliacao.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IItemAvaliacaoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ItemAvaliacao extends React.Component<IItemAvaliacaoProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { itemAvaliacaoList, match } = this.props;
    return (
      <div>
        <h2 id="item-avaliacao-heading">
          <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.home.title">Item Avaliacaos</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.home.createLabel">Create new Item Avaliacao</Translate>
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
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.descricao">Descricao</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.anexoObrigatorio">Anexo Obrigatorio</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.pontosProcedimento">Pontos Procedimento</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.pontosPessoa">Pontos Pessoa</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.pontosProcesso">Pontos Processo</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.itemAvaliacao.pontosProduto">Pontos Produto</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {itemAvaliacaoList.map((itemAvaliacao, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${itemAvaliacao.id}`} color="link" size="sm">
                      {itemAvaliacao.id}
                    </Button>
                  </td>
                  <td>{itemAvaliacao.descricao}</td>
                  <td>{itemAvaliacao.anexoObrigatorio ? 'true' : 'false'}</td>
                  <td>{itemAvaliacao.pontosProcedimento}</td>
                  <td>{itemAvaliacao.pontosPessoa}</td>
                  <td>{itemAvaliacao.pontosProcesso}</td>
                  <td>{itemAvaliacao.pontosProduto}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${itemAvaliacao.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${itemAvaliacao.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${itemAvaliacao.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ itemAvaliacao }: IRootState) => ({
  itemAvaliacaoList: itemAvaliacao.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ItemAvaliacao);
