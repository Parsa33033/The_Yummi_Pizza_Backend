import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Manager from './manager';
import ManagerDetail from './manager-detail';
import ManagerUpdate from './manager-update';
import ManagerDeleteDialog from './manager-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ManagerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ManagerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ManagerDetail} />
      <ErrorBoundaryRoute path={match.url} component={Manager} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ManagerDeleteDialog} />
  </>
);

export default Routes;
