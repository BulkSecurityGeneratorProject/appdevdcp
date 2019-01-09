import './home.scss';

import React from 'react';
import { Link } from 'react-router-dom';
import { Translate } from 'react-jhipster';
import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';

import { IRootState } from 'app/shared/reducers';
import { getSession } from 'app/shared/reducers/authentication';

export interface IHomeProp extends StateProps, DispatchProps {}

export class Home extends React.Component<IHomeProp> {
  componentDidMount() {
    this.props.getSession();
  }

  render() {
    const { account } = this.props;
    return (
      <Row>
        <Col md="9">
          <h2>
            <Translate contentKey="home.title">Welcome, Java Hipster!</Translate>
          </h2>
          <p className="lead">
            <Translate contentKey="home.subtitle">This is your homepage</Translate>
          </p>
          {account && account.login ? (
            <div>
              <Alert color="success">
                <Translate contentKey="home.logged.message" interpolate={{ username: account.login }}>
                  You are logged in as user {account.login}.
                </Translate>
              </Alert>
            </div>
          ) : (
            <div>
              <Alert color="warning">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                Rhoncus est pellentesque elit ullamcorper dignissim cras tincidunt lobortis. Ut tristique et egestas quis ipsum suspendisse
                ultrices. Mi bibendum neque egestas congue quisque egestas diam in arcu. Cras tincidunt lobortis feugiat vivamus. Id aliquet
                lectus proin nibh nisl. Purus ut faucibus pulvinar elementum. Duis ultricies lacus sed turpis tincidunt.
              </Alert>
            </div>
          )}
          <p>
            <Translate contentKey="home.links" />
          </p>

          <ul>
            <li>
              <a href="https://www.americanas.com.br/" target="_blank" rel="noopener noreferrer">
                Americanas 1
              </a>
            </li>
            <li>
              <a href="https://www.americanas.com.br/" target="_blank" rel="noopener noreferrer">
                Americanas 2
              </a>
            </li>
            <li>
              <a href="https://www.americanas.com.br/" target="_blank" rel="noopener noreferrer">
                Americanas 3
              </a>
            </li>
            <li>
              <a href="https://www.americanas.com.br/" target="_blank" rel="noopener noreferrer">
                Americanas 4
              </a>
            </li>
            <li>
              <a href="https://www.americanas.com.br/" target="_blank" rel="noopener noreferrer">
                Americanas 5
              </a>
            </li>
          </ul>

          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sed
            enim ut sem viverra aliquet eget sit amet. A iaculis at erat pellentesque adipiscing commodo elit at. Dignissim diam quis enim
            lobortis scelerisque fermentum. Semper risus in hendrerit gravida.
          </p>
        </Col>
        <Col md="3" className="pad">
          <span className="hipster rounded" />
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated
});

const mapDispatchToProps = { getSession };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Home);
