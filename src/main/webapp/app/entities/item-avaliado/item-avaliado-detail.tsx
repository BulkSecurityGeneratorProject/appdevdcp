import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './item-avaliado.reducer';
import { IItemAvaliado } from 'app/shared/model/item-avaliado.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IItemAvaliadoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ItemAvaliadoDetail extends React.Component<IItemAvaliadoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { itemAvaliadoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.detail.title">ItemAvaliado</Translate> [
            <b>{itemAvaliadoEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="respondidoEm">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.respondidoEm">Respondido Em</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={itemAvaliadoEntity.respondidoEm} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="ultimaAtualizacaoEm">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.ultimaAtualizacaoEm">Ultima Atualizacao Em</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={itemAvaliadoEntity.ultimaAtualizacaoEm} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="status">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.status">Status</Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoEntity.status}</dd>
            <dt>
              <span id="observacoes">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.observacoes">Observacoes</Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoEntity.observacoes}</dd>
            <dt>
              <span id="latitudeLocalResposta">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.latitudeLocalResposta">Latitude Local Resposta</Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoEntity.latitudeLocalResposta}</dd>
            <dt>
              <span id="longitudeLocalResposta">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.longitudeLocalResposta">Longitude Local Resposta</Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoEntity.longitudeLocalResposta}</dd>
            <dt>
              <span id="pontosProcedimento">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.pontosProcedimento">Pontos Procedimento</Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoEntity.pontosProcedimento}</dd>
            <dt>
              <span id="pontosPessoa">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.pontosPessoa">Pontos Pessoa</Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoEntity.pontosPessoa}</dd>
            <dt>
              <span id="pontosProcesso">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.pontosProcesso">Pontos Processo</Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoEntity.pontosProcesso}</dd>
            <dt>
              <span id="pontosProduto">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.pontosProduto">Pontos Produto</Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoEntity.pontosProduto}</dd>
            <dt>
              <span id="pontosObtidosProcedimento">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.pontosObtidosProcedimento">
                  Pontos Obtidos Procedimento
                </Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoEntity.pontosObtidosProcedimento}</dd>
            <dt>
              <span id="pontosObtidosPessoa">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.pontosObtidosPessoa">Pontos Obtidos Pessoa</Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoEntity.pontosObtidosPessoa}</dd>
            <dt>
              <span id="pontosObtidosProcesso">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.pontosObtidosProcesso">Pontos Obtidos Processo</Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoEntity.pontosObtidosProcesso}</dd>
            <dt>
              <span id="pontosObtidosProduto">
                <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.pontosObtidosProduto">Pontos Obtidos Produto</Translate>
              </span>
            </dt>
            <dd>{itemAvaliadoEntity.pontosObtidosProduto}</dd>
            <dt>
              <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.itemAvaliacao">Item Avaliacao</Translate>
            </dt>
            <dd>{itemAvaliadoEntity.itemAvaliacaoDescricao ? itemAvaliadoEntity.itemAvaliacaoDescricao : ''}</dd>
            <dt>
              <Translate contentKey="dcpdesconformidadesApp.itemAvaliado.avaliacao">Avaliacao</Translate>
            </dt>
            <dd>{itemAvaliadoEntity.avaliacaoId ? itemAvaliadoEntity.avaliacaoId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/item-avaliado" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/item-avaliado/${itemAvaliadoEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ itemAvaliado }: IRootState) => ({
  itemAvaliadoEntity: itemAvaliado.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ItemAvaliadoDetail);
