import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './address.reducer';
import { IAddress } from 'app/shared/model/address.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAddressProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Address = (props: IAddressProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { addressList, match, loading } = props;
  return (
    <div>
      <h2 id="address-heading">
        Addresses
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Address
        </Link>
      </h2>
      <div className="table-responsive">
        {addressList && addressList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Phone Number</th>
                <th>Country</th>
                <th>State</th>
                <th>City</th>
                <th>Address 1</th>
                <th>Address 2</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {addressList.map((address, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${address.id}`} color="link" size="sm">
                      {address.id}
                    </Button>
                  </td>
                  <td>{address.phoneNumber}</td>
                  <td>{address.country}</td>
                  <td>{address.state}</td>
                  <td>{address.city}</td>
                  <td>{address.address1}</td>
                  <td>{address.address2}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${address.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${address.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${address.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Addresses found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ address }: IRootState) => ({
  addressList: address.entities,
  loading: address.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Address);
