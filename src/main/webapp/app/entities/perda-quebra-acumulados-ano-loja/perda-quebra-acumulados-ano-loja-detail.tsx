import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './perda-quebra-acumulados-ano-loja.reducer';
import { IPerdaQuebraAcumuladosAnoLoja } from 'app/shared/model/perda-quebra-acumulados-ano-loja.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPerdaQuebraAcumuladosAnoLojaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PerdaQuebraAcumuladosAnoLojaDetail extends React.Component<IPerdaQuebraAcumuladosAnoLojaDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { perdaQuebraAcumuladosAnoLojaEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.detail.title">
              PerdaQuebraAcumuladosAnoLoja
            </Translate>{' '}
            [<b>{perdaQuebraAcumuladosAnoLojaEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="ano">
                <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.ano">Ano</Translate>
              </span>
            </dt>
            <dd>{perdaQuebraAcumuladosAnoLojaEntity.ano}</dd>
            <dt>
              <span id="percentualPerda">
                <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.percentualPerda">Percentual Perda</Translate>
              </span>
            </dt>
            <dd>{perdaQuebraAcumuladosAnoLojaEntity.percentualPerda}</dd>
            <dt>
              <span id="financeiroPerda">
                <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.financeiroPerda">Financeiro Perda</Translate>
              </span>
            </dt>
            <dd>{perdaQuebraAcumuladosAnoLojaEntity.financeiroPerda}</dd>
            <dt>
              <span id="pontuacaoPerda">
                <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.pontuacaoPerda">Pontuacao Perda</Translate>
              </span>
            </dt>
            <dd>{perdaQuebraAcumuladosAnoLojaEntity.pontuacaoPerda}</dd>
            <dt>
              <span id="statusPerda">
                <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.statusPerda">Status Perda</Translate>
              </span>
            </dt>
            <dd>{perdaQuebraAcumuladosAnoLojaEntity.statusPerda}</dd>
            <dt>
              <span id="categorizacaoPerda">
                <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.categorizacaoPerda">
                  Categorizacao Perda
                </Translate>
              </span>
            </dt>
            <dd>{perdaQuebraAcumuladosAnoLojaEntity.categorizacaoPerda}</dd>
            <dt>
              <span id="percentualQuebra">
                <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.percentualQuebra">Percentual Quebra</Translate>
              </span>
            </dt>
            <dd>{perdaQuebraAcumuladosAnoLojaEntity.percentualQuebra}</dd>
            <dt>
              <span id="financeiroQuebra">
                <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.financeiroQuebra">Financeiro Quebra</Translate>
              </span>
            </dt>
            <dd>{perdaQuebraAcumuladosAnoLojaEntity.financeiroQuebra}</dd>
            <dt>
              <span id="pontuacaoQuebra">
                <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.pontuacaoQuebra">Pontuacao Quebra</Translate>
              </span>
            </dt>
            <dd>{perdaQuebraAcumuladosAnoLojaEntity.pontuacaoQuebra}</dd>
            <dt>
              <span id="statusQuebra">
                <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.statusQuebra">Status Quebra</Translate>
              </span>
            </dt>
            <dd>{perdaQuebraAcumuladosAnoLojaEntity.statusQuebra}</dd>
            <dt>
              <span id="categorizacaoQuebra">
                <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.categorizacaoQuebra">
                  Categorizacao Quebra
                </Translate>
              </span>
            </dt>
            <dd>{perdaQuebraAcumuladosAnoLojaEntity.categorizacaoQuebra}</dd>
            <dt>
              <Translate contentKey="dcpdesconformidadesApp.perdaQuebraAcumuladosAnoLoja.loja">Loja</Translate>
            </dt>
            <dd>{perdaQuebraAcumuladosAnoLojaEntity.loja ? perdaQuebraAcumuladosAnoLojaEntity.loja.nomeFormatado : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/perda-quebra-acumulados-ano-loja" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button
            tag={Link}
            to={`/entity/perda-quebra-acumulados-ano-loja/${perdaQuebraAcumuladosAnoLojaEntity.id}/edit`}
            replace
            color="primary"
          >
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ perdaQuebraAcumuladosAnoLoja }: IRootState) => ({
  perdaQuebraAcumuladosAnoLojaEntity: perdaQuebraAcumuladosAnoLoja.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PerdaQuebraAcumuladosAnoLojaDetail);
