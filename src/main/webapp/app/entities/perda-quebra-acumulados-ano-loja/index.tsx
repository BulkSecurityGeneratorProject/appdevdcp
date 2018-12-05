import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PerdaQuebraAcumuladosAnoLoja from './perda-quebra-acumulados-ano-loja';
import PerdaQuebraAcumuladosAnoLojaDetail from './perda-quebra-acumulados-ano-loja-detail';
import PerdaQuebraAcumuladosAnoLojaUpdate from './perda-quebra-acumulados-ano-loja-update';
import PerdaQuebraAcumuladosAnoLojaDeleteDialog from './perda-quebra-acumulados-ano-loja-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PerdaQuebraAcumuladosAnoLojaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PerdaQuebraAcumuladosAnoLojaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PerdaQuebraAcumuladosAnoLojaDetail} />
      <ErrorBoundaryRoute path={match.url} component={PerdaQuebraAcumuladosAnoLoja} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PerdaQuebraAcumuladosAnoLojaDeleteDialog} />
  </>
);

export default Routes;
