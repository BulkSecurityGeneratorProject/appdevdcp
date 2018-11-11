import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Questionario from './questionario';
import QuestionarioDetail from './questionario-detail';
import QuestionarioUpdate from './questionario-update';
import QuestionarioDeleteDialog from './questionario-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={QuestionarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={QuestionarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={QuestionarioDetail} />
      <ErrorBoundaryRoute path={match.url} component={Questionario} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={QuestionarioDeleteDialog} />
  </>
);

export default Routes;
