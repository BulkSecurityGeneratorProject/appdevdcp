import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Badge, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { languages } from 'app/config/translation';
import { getUser } from './user-management.reducer';
import { IRootState } from 'app/shared/reducers';

export interface IUserManagementDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ login: string }> {}

export class UserManagementDetail extends React.Component<IUserManagementDetailProps> {
  componentDidMount() {
    this.props.getUser(this.props.match.params.login);
  }

  render() {
    const { user } = this.props;
    return (
      <Row>
        <Col>
          <h2>
            <Translate contentKey="userManagement.detail.title">User</Translate> [<b>{user.login}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <Translate contentKey="userManagement.login">Login</Translate>
            </dt>
            <dd>
              <span>{user.login}</span>
              &nbsp;
              {user.activated ? (
                <Badge color="success">
                  <Translate contentKey="userManagement.activated">Activated</Translate>
                </Badge>
              ) : (
                <Badge color="danger">
                  <Translate contentKey="userManagement.deactivated">Deactivated</Translate>
                </Badge>
              )}
            </dd>
            <dt>
              <Translate contentKey="userManagement.name">Name</Translate>
            </dt>
            <dd>{user.name}</dd>
            <dt>
              <Translate contentKey="userManagement.email">Email</Translate>
            </dt>
            <dd>{user.email}</dd>
            <dt>
              <Translate contentKey="userManagement.prontuario">Prontuario</Translate>
            </dt>
            <dd>{user.prontuario}</dd>
            <dt>
              <Translate contentKey="userManagement.createdBy">Created By</Translate>
            </dt>
            <dd>{user.createdBy}</dd>
            <dt>
              <Translate contentKey="userManagement.createdDate">Created Date</Translate>
            </dt>
            <dd>
              <TextFormat value={user.createdDate} type="date" format={APP_DATE_FORMAT} blankOnInvalid />
            </dd>
            <dt>
              <Translate contentKey="userManagement.lastModifiedBy">Last Modified By</Translate>
            </dt>
            <dd>{user.lastModifiedBy}</dd>
            <dt>
              <Translate contentKey="userManagement.lastModifiedDate">Last Modified Date</Translate>
            </dt>
            <dd>
              <TextFormat value={user.lastModifiedDate} type="date" format={APP_DATE_FORMAT} blankOnInvalid />
            </dd>
            <dt>
              <Translate contentKey="userManagement.profiles">Profiles</Translate>
            </dt>
            <dd>
              <ul className="list-unstyled">
                {user.authorities
                  ? user.authorities.map((authority, i) => (
                      <li key={`user-auth-${i}`}>
                        <Badge color="info">{authority.descricao}</Badge>
                      </li>
                    ))
                  : null}
              </ul>
            </dd>
            <dt>
              <Translate contentKey="userManagement.lojas">Lojas</Translate>
            </dt>
            <dd>
              <ul className="list-unstyled">
                {user.lojas
                  ? user.lojas.map((loja, i) => (
                      <li key={`user-loja-${i}`}>
                        <Badge color="info">{loja.nomeFormatado}</Badge>
                      </li>
                    ))
                  : null}
              </ul>
            </dd>
          </dl>
          <Button tag={Link} to="/admin/user-management" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  user: storeState.userManagement.user
});

const mapDispatchToProps = { getUser };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UserManagementDetail);
