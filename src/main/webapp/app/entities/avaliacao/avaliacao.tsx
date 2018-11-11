import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import {
  Translate,
  ICrudGetAllAction,
  TextFormat,
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './avaliacao.reducer';
import { IAvaliacao } from 'app/shared/model/avaliacao.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IAvaliacaoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IAvaliacaoState = IPaginationBaseState;

export class Avaliacao extends React.Component<IAvaliacaoProps, IAvaliacaoState> {
  state: IAvaliacaoState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { avaliacaoList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="avaliacao-heading">
          <Translate contentKey="dcpdesconformidadesApp.avaliacao.home.title">Avaliacaos</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="dcpdesconformidadesApp.avaliacao.home.createLabel">Create new Avaliacao</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dataInicio')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.dataInicio">Data Inicio</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('latitudeInicioAvaliacao')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.latitudeInicioAvaliacao">Latitude Inicio Avaliacao</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('longitudeInicioAvaliacao')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.longitudeInicioAvaliacao">Longitude Inicio Avaliacao</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('nomeResponsavelLoja')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.nomeResponsavelLoja">Nome Responsavel Loja</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('prontuarioResponsavelLoja')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.prontuarioResponsavelLoja">Prontuario Responsavel Loja</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('submetidoEm')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.submetidoEm">Submetido Em</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('latitudeSubmissaoAvaliacao')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.latitudeSubmissaoAvaliacao">
                    Latitude Submissao Avaliacao
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('longitudeSubmissaoAvaliacao')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.longitudeSubmissaoAvaliacao">
                    Longitude Submissao Avaliacao
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('observacaoSubmissaoEnviadaForaDaLoja')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.observacaoSubmissaoEnviadaForaDaLoja">
                    Observacao Submissao Enviada Fora Da Loja
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('status')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.questionario">Questionario</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.avaliador">Avaliador</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {avaliacaoList.map((avaliacao, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${avaliacao.id}`} color="link" size="sm">
                      {avaliacao.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={avaliacao.dataInicio} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{avaliacao.latitudeInicioAvaliacao}</td>
                  <td>{avaliacao.longitudeInicioAvaliacao}</td>
                  <td>{avaliacao.nomeResponsavelLoja}</td>
                  <td>{avaliacao.prontuarioResponsavelLoja}</td>
                  <td>
                    <TextFormat type="date" value={avaliacao.submetidoEm} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{avaliacao.latitudeSubmissaoAvaliacao}</td>
                  <td>{avaliacao.longitudeSubmissaoAvaliacao}</td>
                  <td>{avaliacao.observacaoSubmissaoEnviadaForaDaLoja}</td>
                  <td>
                    <Translate contentKey={`dcpdesconformidadesApp.StatusAvaliacao.${avaliacao.status}`} />
                  </td>
                  <td>
                    {avaliacao.questionarioNome ? (
                      <Link to={`questionario/${avaliacao.questionarioId}`}>{avaliacao.questionarioNome}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{avaliacao.avaliadorNome ? <Link to={`avaliador/${avaliacao.avaliadorId}`}>{avaliacao.avaliadorNome}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${avaliacao.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${avaliacao.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${avaliacao.id}/delete`} color="danger" size="sm">
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
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ avaliacao }: IRootState) => ({
  avaliacaoList: avaliacao.entities,
  totalItems: avaliacao.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Avaliacao);
