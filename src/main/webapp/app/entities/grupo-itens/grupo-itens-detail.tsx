import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './grupo-itens.reducer';
import { IGrupoItens } from 'app/shared/model/grupo-itens.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IGrupoItensDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class GrupoItensDetail extends React.Component<IGrupoItensDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { grupoItensEntity } = this.props;
    return (
      <Row>
        <Col>
          <h2>
            <Translate contentKey="dcpdesconformidadesApp.grupoItens.detail.title">GrupoItens</Translate> [<b>{grupoItensEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="nome">
                <Translate contentKey="dcpdesconformidadesApp.grupoItens.nome">Nome</Translate>
              </span>
            </dt>
            <dd>{grupoItensEntity.nome}</dd>
            <dt>
              <span id="ordemExibicao">
                <Translate contentKey="dcpdesconformidadesApp.grupoItens.ordemExibicao">Ordem Exibicao</Translate>
              </span>
            </dt>
            <dd>{grupoItensEntity.ordemExibicao}</dd>
            <dt>
              <Translate contentKey="dcpdesconformidadesApp.grupoItens.questionario">Questionário</Translate>
            </dt>
            <dd>{grupoItensEntity.questionarioNome ? grupoItensEntity.questionarioNome : ''}</dd>
            <dt>
              <Translate contentKey="dcpdesconformidadesApp.grupoItens.itens">Itens</Translate>
            </dt>
            <dd>
              {grupoItensEntity.itens
                ? grupoItensEntity.itens.map((val, i) => (
                    <div key={val.id}>
                      <a>{val.descricao}</a>
                    </div>
                  ))
                : null}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/grupo-itens" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/grupo-itens/${grupoItensEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ grupoItens }: IRootState) => ({
  grupoItensEntity: grupoItens.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(GrupoItensDetail);
