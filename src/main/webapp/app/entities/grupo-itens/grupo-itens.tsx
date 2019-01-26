import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './grupo-itens.reducer';
import { IGrupoItens } from 'app/shared/model/grupo-itens.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IGrupoItensProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class GrupoItens extends React.Component<IGrupoItensProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { grupoItensList, match } = this.props;
    return (
      <div>
        <h2 id="grupo-itens-heading">
          <Translate contentKey="dcpdesconformidadesApp.grupoItens.home.title">Grupo Itens</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="dcpdesconformidadesApp.grupoItens.home.createLabel">Create new Grupo Itens</Translate>
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
                  <Translate contentKey="dcpdesconformidadesApp.grupoItens.nome">Nome</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.grupoItens.questionario">Questionário</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.grupoItens.itens">Itens</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {grupoItensList.map((grupoItens, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${grupoItens.id}`} color="link" size="sm">
                      {grupoItens.id}
                    </Button>
                  </td>
                  <td>{grupoItens.nome}</td>
                  <td>
                    {grupoItens.questionarioNome ? (
                      <Link to={`questionario/${grupoItens.questionarioId}`}>{grupoItens.questionarioNome}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {grupoItens.itens
                      ? grupoItens.itens.map((val, j) => (
                          <div key={j}>
                            <Link to={`item-avaliacao/${val.id}`}>{val.descricao}</Link>
                          </div>
                        ))
                      : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${grupoItens.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${grupoItens.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${grupoItens.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ grupoItens }: IRootState) => ({
  grupoItensList: grupoItens.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(GrupoItens);
