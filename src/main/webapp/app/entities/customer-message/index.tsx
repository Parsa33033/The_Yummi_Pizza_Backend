import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CustomerMessage from './customer-message';
import CustomerMessageDetail from './customer-message-detail';
import CustomerMessageUpdate from './customer-message-update';
import CustomerMessageDeleteDialog from './customer-message-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CustomerMessageUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CustomerMessageUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CustomerMessageDetail} />
      <ErrorBoundaryRoute path={match.url} component={CustomerMessage} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CustomerMessageDeleteDialog} />
  </>
);

export default Routes;
