import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ItemAvaliadoPerdaQuebraAcumulados from './item-avaliado-perda-quebra-acumulados';
import ItemAvaliadoPerdaQuebraAcumuladosDetail from './item-avaliado-perda-quebra-acumulados-detail';
import ItemAvaliadoPerdaQuebraAcumuladosUpdate from './item-avaliado-perda-quebra-acumulados-update';
import ItemAvaliadoPerdaQuebraAcumuladosDeleteDialog from './item-avaliado-perda-quebra-acumulados-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ItemAvaliadoPerdaQuebraAcumuladosUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ItemAvaliadoPerdaQuebraAcumuladosUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ItemAvaliadoPerdaQuebraAcumuladosDetail} />
      <ErrorBoundaryRoute path={match.url} component={ItemAvaliadoPerdaQuebraAcumulados} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ItemAvaliadoPerdaQuebraAcumuladosDeleteDialog} />
  </>
);

export default Routes;
