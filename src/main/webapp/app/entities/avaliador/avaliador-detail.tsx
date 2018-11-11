import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './avaliador.reducer';
import { IAvaliador } from 'app/shared/model/avaliador.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAvaliadorDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AvaliadorDetail extends React.Component<IAvaliadorDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { avaliadorEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dcpdesconformidadesApp.avaliador.detail.title">Avaliador</Translate> [<b>{avaliadorEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="nome">
                <Translate contentKey="dcpdesconformidadesApp.avaliador.nome">Nome</Translate>
              </span>
            </dt>
            <dd>{avaliadorEntity.nome}</dd>
            <dt>
              <span id="login">
                <Translate contentKey="dcpdesconformidadesApp.avaliador.login">Login</Translate>
              </span>
            </dt>
            <dd>{avaliadorEntity.login}</dd>
            <dt>
              <span id="prontuario">
                <Translate contentKey="dcpdesconformidadesApp.avaliador.prontuario">Prontuario</Translate>
              </span>
            </dt>
            <dd>{avaliadorEntity.prontuario}</dd>
          </dl>
          <Button tag={Link} to="/entity/avaliador" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/avaliador/${avaliadorEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ avaliador }: IRootState) => ({
  avaliadorEntity: avaliador.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AvaliadorDetail);
