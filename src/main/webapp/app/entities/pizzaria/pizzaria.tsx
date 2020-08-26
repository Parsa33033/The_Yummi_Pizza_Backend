import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { byteSize, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './pizzaria.reducer';
import { IPizzaria } from 'app/shared/model/pizzaria.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPizzariaProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Pizzaria = (props: IPizzariaProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { pizzariaList, match, loading } = props;
  return (
    <div>
      <h2 id="pizzaria-heading">
        Pizzarias
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Pizzaria
        </Link>
      </h2>
      <div className="table-responsive">
        {pizzariaList && pizzariaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Open Hours</th>
                <th>Open Days</th>
                <th>Delivery Price</th>
                <th>Staff</th>
                <th>Customers</th>
                <th>Number Of Awards</th>
                <th>Pizza Branches</th>
                <th>Manager</th>
                <th>Address</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {pizzariaList.map((pizzaria, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${pizzaria.id}`} color="link" size="sm">
                      {pizzaria.id}
                    </Button>
                  </td>
                  <td>{pizzaria.name}</td>
                  <td>{pizzaria.description}</td>
                  <td>{pizzaria.openHours}</td>
                  <td>{pizzaria.openDays}</td>
                  <td>{pizzaria.deliveryPrice}</td>
                  <td>{pizzaria.staff}</td>
                  <td>{pizzaria.customers}</td>
                  <td>{pizzaria.numberOfAwards}</td>
                  <td>{pizzaria.pizzaBranches}</td>
                  <td>{pizzaria.managerId ? <Link to={`manager/${pizzaria.managerId}`}>{pizzaria.managerId}</Link> : ''}</td>
                  <td>{pizzaria.addressId ? <Link to={`address/${pizzaria.addressId}`}>{pizzaria.addressId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${pizzaria.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pizzaria.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pizzaria.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Pizzarias found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ pizzaria }: IRootState) => ({
  pizzariaList: pizzaria.entities,
  loading: pizzaria.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Pizzaria);
