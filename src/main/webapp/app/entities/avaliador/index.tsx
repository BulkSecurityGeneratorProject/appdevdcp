import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Avaliador from './avaliador';
import AvaliadorDetail from './avaliador-detail';
import AvaliadorUpdate from './avaliador-update';
import AvaliadorDeleteDialog from './avaliador-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AvaliadorUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AvaliadorUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AvaliadorDetail} />
      <ErrorBoundaryRoute path={match.url} component={Avaliador} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AvaliadorDeleteDialog} />
  </>
);

export default Routes;
