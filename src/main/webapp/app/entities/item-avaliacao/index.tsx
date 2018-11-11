import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ItemAvaliacao from './item-avaliacao';
import ItemAvaliacaoDetail from './item-avaliacao-detail';
import ItemAvaliacaoUpdate from './item-avaliacao-update';
import ItemAvaliacaoDeleteDialog from './item-avaliacao-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ItemAvaliacaoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ItemAvaliacaoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ItemAvaliacaoDetail} />
      <ErrorBoundaryRoute path={match.url} component={ItemAvaliacao} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ItemAvaliacaoDeleteDialog} />
  </>
);

export default Routes;
