import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Questionario from './questionario';
import Loja from './loja';
import Avaliacao from './avaliacao';
import ItemAvaliacao from './item-avaliacao';
import GrupoItens from './grupo-itens';
import PerdaQuebraAcumuladosAnoLoja from './perda-quebra-acumulados-ano-loja';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/questionario`} component={Questionario} />
      <ErrorBoundaryRoute path={`${match.url}/loja`} component={Loja} />
      <ErrorBoundaryRoute path={`${match.url}/avaliacao`} component={Avaliacao} />
      <ErrorBoundaryRoute path={`${match.url}/item-avaliacao`} component={ItemAvaliacao} />
      <ErrorBoundaryRoute path={`${match.url}/grupo-itens`} component={GrupoItens} />
      <ErrorBoundaryRoute path={`${match.url}/perda-quebra-acumulados-ano-loja`} component={PerdaQuebraAcumuladosAnoLoja} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
