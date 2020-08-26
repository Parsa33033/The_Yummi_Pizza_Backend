import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAddress } from 'app/shared/model/address.model';
import { getEntities as getAddresses } from 'app/entities/address/address.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './customer.reducer';
import { ICustomer } from 'app/shared/model/customer.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICustomerUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CustomerUpdate = (props: ICustomerUpdateProps) => {
  const [addressId, setAddressId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { customerEntity, addresses, loading, updating } = props;

  const { image, imageContentType } = customerEntity;

  const handleClose = () => {
    props.history.push('/customer');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

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
        ...customerEntity,
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
          <h2 id="theYummiPizzaBackendApp.customer.home.createOrEditLabel">Create or edit a Customer</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : customerEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="customer-id">ID</Label>
                  <AvInput id="customer-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="usernameLabel" for="customer-username">
                  Username
                </Label>
                <AvField id="customer-username" type="text" name="username" />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="customer-email">
                  Email
                </Label>
                <AvField id="customer-email" type="text" name="email" />
              </AvGroup>
              <AvGroup>
                <Label id="firstNameLabel" for="customer-firstName">
                  First Name
                </Label>
                <AvField id="customer-firstName" type="text" name="firstName" />
              </AvGroup>
              <AvGroup>
                <Label id="lastNameLabel" for="customer-lastName">
                  Last Name
                </Label>
                <AvField id="customer-lastName" type="text" name="lastName" />
              </AvGroup>
              <AvGroup>
                <Label id="mobileNumberLabel" for="customer-mobileNumber">
                  Mobile Number
                </Label>
                <AvField id="customer-mobileNumber" type="text" name="mobileNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="genderLabel" for="customer-gender">
                  Gender
                </Label>
                <AvInput
                  id="customer-gender"
                  type="select"
                  className="form-control"
                  name="gender"
                  value={(!isNew && customerEntity.gender) || 'MALE'}
                >
                  <option value="MALE">MALE</option>
                  <option value="FEMALE">FEMALE</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="imageLabel" for="image">
                    Image
                  </Label>
                  <br />
                  {image ? (
                    <div>
                      {imageContentType ? (
                        <a onClick={openFile(imageContentType, image)}>
                          <img src={`data:${imageContentType};base64,${image}`} style={{ maxHeight: '100px' }} />
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {imageContentType}, {byteSize(image)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('image')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_image" type="file" onChange={onBlobChange(true, 'image')} accept="image/*" />
                  <AvInput type="hidden" name="image" value={image} />
                </AvGroup>
              </AvGroup>
              <AvGroup>
                <Label for="customer-address">Address</Label>
                <AvInput id="customer-address" type="select" className="form-control" name="addressId">
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
              <Button tag={Link} id="cancel-save" to="/customer" replace color="info">
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
  addresses: storeState.address.entities,
  customerEntity: storeState.customer.entity,
  loading: storeState.customer.loading,
  updating: storeState.customer.updating,
  updateSuccess: storeState.customer.updateSuccess,
});

const mapDispatchToProps = {
  getAddresses,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CustomerUpdate);
