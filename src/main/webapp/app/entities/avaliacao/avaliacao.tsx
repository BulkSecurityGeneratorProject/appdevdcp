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
          <Translate contentKey="dcpdesconformidadesApp.avaliacao.home.title">Avaliações</Translate>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.loja">Loja</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('nomeResponsavelLoja')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.nomeResponsavelLoja">Nome Responsavel Loja</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.nomeAvaliador">Avaliador</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('status')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('iniciadaEm')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.iniciadaEm">Iniciada Em</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('submetidoEm')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.submetidoEm">Submetido Em</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('criticidadePainel')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.criticidadePainel">Criticidade Painel</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('nivelEficienciaGeral')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.nivelEficienciaGeral">Nivel Eficiencia Geral</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('importadoViaPlanilha')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.importadoViaPlanilha">Importado Via Planilha</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('caminhoArquivoPlanilha')}>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.caminhoArquivoPlanilha">Caminho Arquivo Planilha</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.questionario">Questionario</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
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
                  <td>{avaliacao.lojaNome ? <Link to={`loja/${avaliacao.lojaId}`}>{avaliacao.lojaNome}</Link> : ''}</td>
                  <td>{avaliacao.nomeResponsavelLoja}</td>
                  <td>{avaliacao.avaliadorName ? avaliacao.avaliadorName : ''}</td>
                  <td>
                    <Translate contentKey={`dcpdesconformidadesApp.StatusAvaliacao.${avaliacao.status}`} />
                  </td>
                  <td>
                    <TextFormat type="date" value={avaliacao.iniciadaEm} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={avaliacao.submetidoEm} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <Translate contentKey={`dcpdesconformidadesApp.CriticidadePainel.${avaliacao.criticidadePainel}`} />
                  </td>
                  <td>
                    <Translate contentKey={`dcpdesconformidadesApp.NivelEficiencia.${avaliacao.nivelEficienciaGeral}`} />
                  </td>
                  <td>{avaliacao.importadoViaPlanilha ? 'Sim' : 'Não'}</td>
                  <td>{avaliacao.caminhoArquivoPlanilha}</td>
                  <td>
                    {avaliacao.questionario.nome ? (
                      <Link to={`questionario/${avaliacao.questionario.id}`}>{avaliacao.questionario.nome}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${avaliacao.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${avaliacao.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.cancel">Cancel</Translate>
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
