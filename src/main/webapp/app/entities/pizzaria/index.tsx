import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Pizzaria from './pizzaria';
import PizzariaDetail from './pizzaria-detail';
import PizzariaUpdate from './pizzaria-update';
import PizzariaDeleteDialog from './pizzaria-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PizzariaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PizzariaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PizzariaDetail} />
      <ErrorBoundaryRoute path={match.url} component={Pizzaria} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PizzariaDeleteDialog} />
  </>
);

export default Routes;
