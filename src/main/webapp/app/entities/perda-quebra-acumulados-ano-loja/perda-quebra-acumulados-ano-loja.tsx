import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './perda-quebra-acumulados-ano-loja.reducer';
import { IPerdaQuebraAcumuladosAnoLoja } from 'app/shared/model/perda-quebra-acumulados-ano-loja.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPerdaQuebraAcumuladosAnoLojaProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class PerdaQuebraAcumuladosAnoLoja extends React.Component<IPerdaQuebraAcumuladosAnoLojaProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { perdaQuebraAcumuladosAnoLojaList, match } = this.props;
    return (
      <div>
        <h2 id="perda-quebra-acumulados-ano-loja-heading">
          <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.home.title">
            Perda Quebra Acumulados Ano Lojas
          </Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.home.createLabel">
              Create new Perda Quebra Acumulados Ano Loja
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
                  <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.ano">Ano</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.percentualPerda">Percentual Perda</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.financeiroPerda">Financeiro Perda</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.pontuacaoPerda">Pontuacao Perda</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.statusPerda">Status Perda</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.categorizacaoPerda">
                    Categorizacao Perda
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.percentualQuebra">Percentual Quebra</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.financeiroQuebra">Financeiro Quebra</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.pontuacaoQuebra">Pontuacao Quebra</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.statusQuebra">Status Quebra</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.categorizacaoQuebra">
                    Categorizacao Quebra
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.loja">Loja</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {perdaQuebraAcumuladosAnoLojaList.map((perdaQuebraAcumuladosAnoLoja, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${perdaQuebraAcumuladosAnoLoja.id}`} color="link" size="sm">
                      {perdaQuebraAcumuladosAnoLoja.id}
                    </Button>
                  </td>
                  <td>{perdaQuebraAcumuladosAnoLoja.ano}</td>
                  <td>{perdaQuebraAcumuladosAnoLoja.percentualPerda}</td>
                  <td>{perdaQuebraAcumuladosAnoLoja.financeiroPerda}</td>
                  <td>{perdaQuebraAcumuladosAnoLoja.pontuacaoPerda}</td>
                  <td>
                    <Translate contentKey={`dcpdesconformidadesApp.StatusItemAvaliado.${perdaQuebraAcumuladosAnoLoja.statusPerda}`} />
                  </td>
                  <td>
                    <Translate
                      contentKey={`dcpdesconformidadesApp.CategorizacaoPerdaQuebra.${perdaQuebraAcumuladosAnoLoja.categorizacaoPerda}`}
                    />
                  </td>
                  <td>{perdaQuebraAcumuladosAnoLoja.percentualQuebra}</td>
                  <td>{perdaQuebraAcumuladosAnoLoja.financeiroQuebra}</td>
                  <td>{perdaQuebraAcumuladosAnoLoja.pontuacaoQuebra}</td>
                  <td>
                    <Translate contentKey={`dcpdesconformidadesApp.StatusItemAvaliado.${perdaQuebraAcumuladosAnoLoja.statusQuebra}`} />
                  </td>
                  <td>
                    <Translate
                      contentKey={`dcpdesconformidadesApp.CategorizacaoPerdaQuebra.${perdaQuebraAcumuladosAnoLoja.categorizacaoQuebra}`}
                    />
                  </td>
                  <td>
                    {perdaQuebraAcumuladosAnoLoja.loja ? (
                      <Link to={`loja/${perdaQuebraAcumuladosAnoLoja.loja.id}`}>{perdaQuebraAcumuladosAnoLoja.loja.nomeFormatado}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${perdaQuebraAcumuladosAnoLoja.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${perdaQuebraAcumuladosAnoLoja.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${perdaQuebraAcumuladosAnoLoja.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ perdaQuebraAcumuladosAnoLoja }: IRootState) => ({
  perdaQuebraAcumuladosAnoLojaList: perdaQuebraAcumuladosAnoLoja.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PerdaQuebraAcumuladosAnoLoja);
