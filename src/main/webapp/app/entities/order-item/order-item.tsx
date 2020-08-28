import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './order-item.reducer';
import { IOrderItem } from 'app/shared/model/order-item.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrderItemProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const OrderItem = (props: IOrderItemProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { orderItemList, match, loading } = props;
  return (
    <div>
      <h2 id="order-item-heading">
        Order Items
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Order Item
        </Link>
      </h2>
      <div className="table-responsive">
        {orderItemList && orderItemList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Number</th>
                <th>Menu Item Id</th>
                <th>Order</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {orderItemList.map((orderItem, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${orderItem.id}`} color="link" size="sm">
                      {orderItem.id}
                    </Button>
                  </td>
                  <td>{orderItem.number}</td>
                  <td>{orderItem.menuItemId}</td>
                  <td>{orderItem.orderId ? <Link to={`order/${orderItem.orderId}`}>{orderItem.orderId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${orderItem.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${orderItem.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${orderItem.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Order Items found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ orderItem }: IRootState) => ({
  orderItemList: orderItem.entities,
  loading: orderItem.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrderItem);
