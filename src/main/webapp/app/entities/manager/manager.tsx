import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './manager.reducer';
import { IManager } from 'app/shared/model/manager.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IManagerProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Manager = (props: IManagerProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { managerList, match, loading } = props;
  return (
    <div>
      <h2 id="manager-heading">
        Managers
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Manager
        </Link>
      </h2>
      <div className="table-responsive">
        {managerList && managerList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Mobile Number</th>
                <th>Gender</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {managerList.map((manager, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${manager.id}`} color="link" size="sm">
                      {manager.id}
                    </Button>
                  </td>
                  <td>{manager.username}</td>
                  <td>{manager.email}</td>
                  <td>{manager.firstName}</td>
                  <td>{manager.lastName}</td>
                  <td>{manager.mobileNumber}</td>
                  <td>{manager.gender}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${manager.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${manager.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${manager.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Managers found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ manager }: IRootState) => ({
  managerList: manager.entities,
  loading: manager.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Manager);
