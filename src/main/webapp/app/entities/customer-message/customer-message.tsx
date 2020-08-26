import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { byteSize, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './customer-message.reducer';
import { ICustomerMessage } from 'app/shared/model/customer-message.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICustomerMessageProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const CustomerMessage = (props: ICustomerMessageProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { customerMessageList, match, loading } = props;
  return (
    <div>
      <h2 id="customer-message-heading">
        Customer Messages
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Customer Message
        </Link>
      </h2>
      <div className="table-responsive">
        {customerMessageList && customerMessageList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Subject</th>
                <th>Message</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {customerMessageList.map((customerMessage, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${customerMessage.id}`} color="link" size="sm">
                      {customerMessage.id}
                    </Button>
                  </td>
                  <td>{customerMessage.name}</td>
                  <td>{customerMessage.email}</td>
                  <td>{customerMessage.subject}</td>
                  <td>{customerMessage.message}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${customerMessage.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${customerMessage.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${customerMessage.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Customer Messages found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ customerMessage }: IRootState) => ({
  customerMessageList: customerMessage.entities,
  loading: customerMessage.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CustomerMessage);
