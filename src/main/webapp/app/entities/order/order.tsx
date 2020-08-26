import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './order.reducer';
import { IOrder } from 'app/shared/model/order.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrderProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Order = (props: IOrderProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { orderList, match, loading } = props;
  return (
    <div>
      <h2 id="order-heading">
        Orders
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Order
        </Link>
      </h2>
      <div className="table-responsive">
        {orderList && orderList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Date</th>
                <th>Total Price</th>
                <th>Delivered</th>
                <th>Address</th>
                <th>Customer</th>
                <th>Pizzaria</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {orderList.map((order, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${order.id}`} color="link" size="sm">
                      {order.id}
                    </Button>
                  </td>
                  <td>{order.date ? <TextFormat type="date" value={order.date} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{order.totalPrice}</td>
                  <td>{order.delivered ? 'true' : 'false'}</td>
                  <td>{order.addressId ? <Link to={`address/${order.addressId}`}>{order.addressId}</Link> : ''}</td>
                  <td>{order.customerId ? <Link to={`customer/${order.customerId}`}>{order.customerId}</Link> : ''}</td>
                  <td>{order.pizzariaId ? <Link to={`pizzaria/${order.pizzariaId}`}>{order.pizzariaId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${order.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${order.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${order.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Orders found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ order }: IRootState) => ({
  orderList: order.entities,
  loading: order.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Order);
