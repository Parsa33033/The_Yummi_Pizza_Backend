import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Rating from './rating';
import RatingDetail from './rating-detail';
import RatingUpdate from './rating-update';
import RatingDeleteDialog from './rating-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RatingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RatingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RatingDetail} />
      <ErrorBoundaryRoute path={match.url} component={Rating} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={RatingDeleteDialog} />
  </>
);

export default Routes;
