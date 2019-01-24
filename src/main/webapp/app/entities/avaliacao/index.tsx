import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Avaliacao from './avaliacao';
import AvaliacaoDetail from './avaliacao-detail';
import AvaliacaoDeleteDialog from './avaliacao-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AvaliacaoDetail} />
      <ErrorBoundaryRoute path={match.url} component={Avaliacao} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AvaliacaoDeleteDialog} />
  </>
);

export default Routes;
