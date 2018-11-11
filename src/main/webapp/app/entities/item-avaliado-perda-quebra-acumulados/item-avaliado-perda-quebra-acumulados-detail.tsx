import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './item-avaliado-perda-quebra-acumulados.reducer';
import { IItemAvaliadoPerdaQuebraAcumulados } from 'app/shared/model/item-avaliado-perda-quebra-acumulados.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IItemAvaliadoPerdaQuebraAcumuladosDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ItemAvaliadoPerdaQuebraAcumuladosDetail extends React.Component<IItemAvaliadoPerdaQuebraAcumuladosDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { itemAvaliadoPerdaQuebraAcumuladosEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.detail.title">
              ItemAvaliadoPerdaQuebraAcumulados
            </Translate>{' '}
            [<b>{itemAvaliadoPerdaQuebraAcumuladosEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="tipo">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.tipo">Tipo</Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoPerdaQuebraAcumuladosEntity.tipo}</dd>
            <dt>
              <span id="respondidoEm">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.respondidoEm">Respondido Em</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={itemAvaliadoPerdaQuebraAcumuladosEntity.respondidoEm} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="ultimaAtualizacaoEm">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.ultimaAtualizacaoEm">
                  Ultima Atualizacao Em
                </Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={itemAvaliadoPerdaQuebraAcumuladosEntity.ultimaAtualizacaoEm} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="percentual">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.percentual">Percentual</Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoPerdaQuebraAcumuladosEntity.percentual}</dd>
            <dt>
              <span id="financeiro">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.financeiro">Financeiro</Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoPerdaQuebraAcumuladosEntity.financeiro}</dd>
            <dt>
              <span id="pontuacao">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.pontuacao">Pontuacao</Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoPerdaQuebraAcumuladosEntity.pontuacao}</dd>
            <dt>
              <span id="latitudeLocalResposta">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.latitudeLocalResposta">
                  Latitude Local Resposta
                </Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoPerdaQuebraAcumuladosEntity.latitudeLocalResposta}</dd>
            <dt>
              <span id="longitudeLocalResposta">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.longitudeLocalResposta">
                  Longitude Local Resposta
                </Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoPerdaQuebraAcumuladosEntity.longitudeLocalResposta}</dd>
            <dt>
              <Translate contentKey="dcpdesconformidadesApp.itemAvaliadoPerdaQuebraAcumulados.avaliacao">Avaliacao</Translate>
            </dt>
            <dd>{itemAvaliadoPerdaQuebraAcumuladosEntity.avaliacao ? itemAvaliadoPerdaQuebraAcumuladosEntity.avaliacao.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/item-avaliado-perda-quebra-acumulados" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button
            tag={Link}
            to={`/entity/item-avaliado-perda-quebra-acumulados/${itemAvaliadoPerdaQuebraAcumuladosEntity.id}/edit`}
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

const mapStateToProps = ({ itemAvaliadoPerdaQuebraAcumulados }: IRootState) => ({
  itemAvaliadoPerdaQuebraAcumuladosEntity: itemAvaliadoPerdaQuebraAcumulados.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ItemAvaliadoPerdaQuebraAcumuladosDetail);
