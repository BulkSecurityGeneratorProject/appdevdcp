import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ItemAuditado from './item-auditado';
import ItemAuditadoDetail from './item-auditado-detail';
import ItemAuditadoUpdate from './item-auditado-update';
import ItemAuditadoDeleteDialog from './item-auditado-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ItemAuditadoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ItemAuditadoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ItemAuditadoDetail} />
      <ErrorBoundaryRoute path={match.url} component={ItemAuditado} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ItemAuditadoDeleteDialog} />
  </>
);

export default Routes;
