import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Loja from './loja';
import LojaDetail from './loja-detail';
import LojaUpdate from './loja-update';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LojaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LojaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LojaDetail} />
      <ErrorBoundaryRoute path={match.url} component={Loja} />
    </Switch>
  </>
);

export default Routes;
