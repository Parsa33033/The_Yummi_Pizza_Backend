import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICustomer } from 'app/shared/model/customer.model';
import { getEntities as getCustomers } from 'app/entities/customer/customer.reducer';
import { IOrder } from 'app/shared/model/order.model';
import { getEntities as getOrders } from 'app/entities/order/order.reducer';
import { IPizzaria } from 'app/shared/model/pizzaria.model';
import { getEntities as getPizzarias } from 'app/entities/pizzaria/pizzaria.reducer';
import { getEntity, updateEntity, createEntity, reset } from './address.reducer';
import { IAddress } from 'app/shared/model/address.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAddressUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AddressUpdate = (props: IAddressUpdateProps) => {
  const [customerId, setCustomerId] = useState('0');
  const [orderId, setOrderId] = useState('0');
  const [pizzariaId, setPizzariaId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { addressEntity, customers, orders, pizzarias, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/address');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCustomers();
    props.getOrders();
    props.getPizzarias();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...addressEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="theYummiPizzaBackendApp.address.home.createOrEditLabel">Create or edit a Address</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : addressEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="address-id">ID</Label>
                  <AvInput id="address-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="phoneNumberLabel" for="address-phoneNumber">
                  Phone Number
                </Label>
                <AvField id="address-phoneNumber" type="text" name="phoneNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="countryLabel" for="address-country">
                  Country
                </Label>
                <AvField id="address-country" type="text" name="country" />
              </AvGroup>
              <AvGroup>
                <Label id="stateLabel" for="address-state">
                  State
                </Label>
                <AvField id="address-state" type="text" name="state" />
              </AvGroup>
              <AvGroup>
                <Label id="cityLabel" for="address-city">
                  City
                </Label>
                <AvField id="address-city" type="text" name="city" />
              </AvGroup>
              <AvGroup>
                <Label id="address1Label" for="address-address1">
                  Address 1
                </Label>
                <AvField id="address-address1" type="text" name="address1" />
              </AvGroup>
              <AvGroup>
                <Label id="address2Label" for="address-address2">
                  Address 2
                </Label>
                <AvField id="address-address2" type="text" name="address2" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/address" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  customers: storeState.customer.entities,
  orders: storeState.order.entities,
  pizzarias: storeState.pizzaria.entities,
  addressEntity: storeState.address.entity,
  loading: storeState.address.loading,
  updating: storeState.address.updating,
  updateSuccess: storeState.address.updateSuccess,
});

const mapDispatchToProps = {
  getCustomers,
  getOrders,
  getPizzarias,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AddressUpdate);
