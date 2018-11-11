import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AnexoItem from './anexo-item';
import AnexoItemDetail from './anexo-item-detail';
import AnexoItemUpdate from './anexo-item-update';
import AnexoItemDeleteDialog from './anexo-item-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AnexoItemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AnexoItemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AnexoItemDetail} />
      <ErrorBoundaryRoute path={match.url} component={AnexoItem} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AnexoItemDeleteDialog} />
  </>
);

export default Routes;
