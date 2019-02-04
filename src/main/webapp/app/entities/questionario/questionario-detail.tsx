import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './questionario.reducer';
import { IQuestionario } from 'app/shared/model/questionario.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IQuestionarioDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class QuestionarioDetail extends React.Component<IQuestionarioDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { questionarioEntity } = this.props;
    return (
      <Row>
        <Col>
          <h2>
            <Translate contentKey="dcpdesconformidadesApp.questionario.detail.title">Questionario</Translate> [
            <b>{questionarioEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="nome">
                <Translate contentKey="dcpdesconformidadesApp.questionario.nome">Nome</Translate>
              </span>
            </dt>
            <dd>{questionarioEntity.nome}</dd>
            <dt>
              <span id="descricao">
                <Translate contentKey="dcpdesconformidadesApp.questionario.descricao">Descricao</Translate>
              </span>
            </dt>
            <dd>{questionarioEntity.descricao}</dd>
            <dt>
              <span id="ativo">
                <Translate contentKey="dcpdesconformidadesApp.questionario.ativo">Ativo</Translate>
              </span>
            </dt>
            <dd>{questionarioEntity.ativo ? 'true' : 'false'}</dd>
            <dt>
              <span id="versao">
                <Translate contentKey="dcpdesconformidadesApp.questionario.versao">Versão</Translate>
              </span>
            </dt>
            <dd>{questionarioEntity.versao}</dd>
            <dt>
              <Translate contentKey="dcpdesconformidadesApp.questionario.grupo">Grupo</Translate>
            </dt>
            <dd>
              {questionarioEntity.grupos
                ? questionarioEntity.grupos.map((val, i) => (
                    <div key={val.id}>
                      <a>{val.nome}</a>
                    </div>
                  ))
                : null}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/questionario" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/questionario/${questionarioEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ questionario }: IRootState) => ({
  questionarioEntity: questionario.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(QuestionarioDetail);
