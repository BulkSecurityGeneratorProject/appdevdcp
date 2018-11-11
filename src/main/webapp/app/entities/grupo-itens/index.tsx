import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import GrupoItens from './grupo-itens';
import GrupoItensDetail from './grupo-itens-detail';
import GrupoItensUpdate from './grupo-itens-update';
import GrupoItensDeleteDialog from './grupo-itens-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={GrupoItensUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={GrupoItensUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={GrupoItensDetail} />
      <ErrorBoundaryRoute path={match.url} component={GrupoItens} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={GrupoItensDeleteDialog} />
  </>
);

export default Routes;
