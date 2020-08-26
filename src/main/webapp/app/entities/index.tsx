import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Customer from './customer';
import Manager from './manager';
import Pizzaria from './pizzaria';
import Order from './order';
import OrderItem from './order-item';
import Address from './address';
import MenuItem from './menu-item';
import Rating from './rating';
import CustomerMessage from './customer-message';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}customer`} component={Customer} />
      <ErrorBoundaryRoute path={`${match.url}manager`} component={Manager} />
      <ErrorBoundaryRoute path={`${match.url}pizzaria`} component={Pizzaria} />
      <ErrorBoundaryRoute path={`${match.url}order`} component={Order} />
      <ErrorBoundaryRoute path={`${match.url}order-item`} component={OrderItem} />
      <ErrorBoundaryRoute path={`${match.url}address`} component={Address} />
      <ErrorBoundaryRoute path={`${match.url}menu-item`} component={MenuItem} />
      <ErrorBoundaryRoute path={`${match.url}rating`} component={Rating} />
      <ErrorBoundaryRoute path={`${match.url}customer-message`} component={CustomerMessage} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
