import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IManager } from 'app/shared/model/manager.model';
import { getEntities as getManagers } from 'app/entities/manager/manager.reducer';
import { IAddress } from 'app/shared/model/address.model';
import { getEntities as getAddresses } from 'app/entities/address/address.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './pizzaria.reducer';
import { IPizzaria } from 'app/shared/model/pizzaria.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPizzariaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PizzariaUpdate = (props: IPizzariaUpdateProps) => {
  const [managerId, setManagerId] = useState('0');
  const [addressId, setAddressId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { pizzariaEntity, managers, addresses, loading, updating } = props;

  const { description } = pizzariaEntity;

  const handleClose = () => {
    props.history.push('/pizzaria');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getManagers();
    props.getAddresses();
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...pizzariaEntity,
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
          <h2 id="theYummiPizzaBackendApp.pizzaria.home.createOrEditLabel">Create or edit a Pizzaria</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : pizzariaEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="pizzaria-id">ID</Label>
                  <AvInput id="pizzaria-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="pizzaria-name">
                  Name
                </Label>
                <AvField id="pizzaria-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="pizzaria-description">
                  Description
                </Label>
                <AvInput id="pizzaria-description" type="textarea" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="openHoursLabel" for="pizzaria-openHours">
                  Open Hours
                </Label>
                <AvField id="pizzaria-openHours" type="text" name="openHours" />
              </AvGroup>
              <AvGroup>
                <Label id="openDaysLabel" for="pizzaria-openDays">
                  Open Days
                </Label>
                <AvField id="pizzaria-openDays" type="text" name="openDays" />
              </AvGroup>
              <AvGroup>
                <Label id="deliveryPriceLabel" for="pizzaria-deliveryPrice">
                  Delivery Price
                </Label>
                <AvField id="pizzaria-deliveryPrice" type="string" className="form-control" name="deliveryPrice" />
              </AvGroup>
              <AvGroup>
                <Label id="staffLabel" for="pizzaria-staff">
                  Staff
                </Label>
                <AvField id="pizzaria-staff" type="string" className="form-control" name="staff" />
              </AvGroup>
              <AvGroup>
                <Label id="customersLabel" for="pizzaria-customers">
                  Customers
                </Label>
                <AvField id="pizzaria-customers" type="string" className="form-control" name="customers" />
              </AvGroup>
              <AvGroup>
                <Label id="numberOfAwardsLabel" for="pizzaria-numberOfAwards">
                  Number Of Awards
                </Label>
                <AvField id="pizzaria-numberOfAwards" type="string" className="form-control" name="numberOfAwards" />
              </AvGroup>
              <AvGroup>
                <Label id="pizzaBranchesLabel" for="pizzaria-pizzaBranches">
                  Pizza Branches
                </Label>
                <AvField id="pizzaria-pizzaBranches" type="string" className="form-control" name="pizzaBranches" />
              </AvGroup>
              <AvGroup>
                <Label for="pizzaria-manager">Manager</Label>
                <AvInput id="pizzaria-manager" type="select" className="form-control" name="managerId">
                  <option value="" key="0" />
                  {managers
                    ? managers.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="pizzaria-address">Address</Label>
                <AvInput id="pizzaria-address" type="select" className="form-control" name="addressId">
                  <option value="" key="0" />
                  {addresses
                    ? addresses.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/pizzaria" replace color="info">
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
  managers: storeState.manager.entities,
  addresses: storeState.address.entities,
  pizzariaEntity: storeState.pizzaria.entity,
  loading: storeState.pizzaria.loading,
  updating: storeState.pizzaria.updating,
  updateSuccess: storeState.pizzaria.updateSuccess,
});

const mapDispatchToProps = {
  getManagers,
  getAddresses,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PizzariaUpdate);
