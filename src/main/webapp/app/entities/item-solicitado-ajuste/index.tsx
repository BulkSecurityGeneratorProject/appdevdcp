import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ItemSolicitadoAjuste from './item-solicitado-ajuste';
import ItemSolicitadoAjusteDetail from './item-solicitado-ajuste-detail';
import ItemSolicitadoAjusteUpdate from './item-solicitado-ajuste-update';
import ItemSolicitadoAjusteDeleteDialog from './item-solicitado-ajuste-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ItemSolicitadoAjusteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ItemSolicitadoAjusteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ItemSolicitadoAjusteDetail} />
      <ErrorBoundaryRoute path={match.url} component={ItemSolicitadoAjuste} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ItemSolicitadoAjusteDeleteDialog} />
  </>
);

export default Routes;
