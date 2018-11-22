import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './loja.reducer';
import { ILoja } from 'app/shared/model/loja.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILojaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LojaDetail extends React.Component<ILojaDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { lojaEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dcpdesconformidadesApp.loja.detail.title">Loja</Translate> [<b>{lojaEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="codigo">
                <Translate contentKey="dcpdesconformidadesApp.loja.codigo">Codigo</Translate>
              </span>
            </dt>
            <dd>{lojaEntity.codigo}</dd>
            <dt>
              <span id="nome">
                <Translate contentKey="dcpdesconformidadesApp.loja.nome">Nome</Translate>
              </span>
            </dt>
            <dd>{lojaEntity.nome}</dd>
            <dt>
              <span id="nomeResponsavel">
                <Translate contentKey="dcpdesconformidadesApp.loja.nomeResponsavel">Nome Responsavel</Translate>
              </span>
            </dt>
            <dd>{lojaEntity.nomeResponsavel}</dd>
            <dt>
              <span id="prontuarioResponsavel">
                <Translate contentKey="dcpdesconformidadesApp.loja.prontuarioResponsavel">Prontuario Responsavel</Translate>
              </span>
            </dt>
            <dd>{lojaEntity.prontuarioResponsavel}</dd>
            <dt>
              <span id="latitude">
                <Translate contentKey="dcpdesconformidadesApp.loja.latitude">Latitude</Translate>
              </span>
            </dt>
            <dd>{lojaEntity.latitude}</dd>
            <dt>
              <span id="longitude">
                <Translate contentKey="dcpdesconformidadesApp.loja.longitude">Longitude</Translate>
              </span>
            </dt>
            <dd>{lojaEntity.longitude}</dd>
            <dt>
              <Translate contentKey="dcpdesconformidadesApp.loja.user">User</Translate>
            </dt>
            <dd>
              {lojaEntity.avaliadores
                ? lojaEntity.avaliadores.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.name}</a>
                      {i === lojaEntity.avaliadores.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}{' '}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/loja" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/loja/${lojaEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ loja }: IRootState) => ({
  lojaEntity: loja.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LojaDetail);
