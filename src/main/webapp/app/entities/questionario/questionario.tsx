import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './questionario.reducer';
import { IQuestionario } from 'app/shared/model/questionario.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IQuestionarioProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Questionario extends React.Component<IQuestionarioProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { questionarioList, match } = this.props;
    return (
      <div>
        <h2 id="questionario-heading">
          <Translate contentKey="dcpdesconformidadesApp.questionario.home.title">Questionarios</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="dcpdesconformidadesApp.questionario.home.createLabel">Create new Questionario</Translate>
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
                  <Translate contentKey="dcpdesconformidadesApp.questionario.nome">Nome</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.questionario.descricao">Descricao</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.questionario.ativo">Ativo</Translate>
                </th>
                <th>
                  <Translate contentKey="dcpdesconformidadesApp.questionario.grupo">Grupo</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {questionarioList.map((questionario, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${questionario.id}`} color="link" size="sm">
                      {questionario.id}
                    </Button>
                  </td>
                  <td>{questionario.nome}</td>
                  <td>{questionario.descricao}</td>
                  <td>{questionario.ativo ? 'true' : 'false'}</td>
                  <td>
                    {questionario.grupos
                      ? questionario.grupos.map((val, j) => (
                          <span key={j}>
                            <Link to={`grupo-itens/${val.id}`}>{val.nome}</Link>
                            {j === questionario.grupos.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${questionario.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${questionario.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${questionario.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ questionario }: IRootState) => ({
  questionarioList: questionario.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Questionario);
