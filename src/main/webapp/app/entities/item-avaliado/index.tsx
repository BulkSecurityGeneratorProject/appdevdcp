import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ItemAvaliado from './item-avaliado';
import ItemAvaliadoDetail from './item-avaliado-detail';
import ItemAvaliadoUpdate from './item-avaliado-update';
import ItemAvaliadoDeleteDialog from './item-avaliado-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ItemAvaliadoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ItemAvaliadoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ItemAvaliadoDetail} />
      <ErrorBoundaryRoute path={match.url} component={ItemAvaliado} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ItemAvaliadoDeleteDialog} />
  </>
);

export default Routes;
