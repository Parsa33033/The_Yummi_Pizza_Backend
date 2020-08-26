import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './customer-message.reducer';
import { ICustomerMessage } from 'app/shared/model/customer-message.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICustomerMessageUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CustomerMessageUpdate = (props: ICustomerMessageUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { customerMessageEntity, loading, updating } = props;

  const { message } = customerMessageEntity;

  const handleClose = () => {
    props.history.push('/customer-message');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
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
        ...customerMessageEntity,
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
          <h2 id="theYummiPizzaBackendApp.customerMessage.home.createOrEditLabel">Create or edit a CustomerMessage</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : customerMessageEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="customer-message-id">ID</Label>
                  <AvInput id="customer-message-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="customer-message-name">
                  Name
                </Label>
                <AvField id="customer-message-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="customer-message-email">
                  Email
                </Label>
                <AvField id="customer-message-email" type="text" name="email" />
              </AvGroup>
              <AvGroup>
                <Label id="subjectLabel" for="customer-message-subject">
                  Subject
                </Label>
                <AvField id="customer-message-subject" type="text" name="subject" />
              </AvGroup>
              <AvGroup>
                <Label id="messageLabel" for="customer-message-message">
                  Message
                </Label>
                <AvInput id="customer-message-message" type="textarea" name="message" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/customer-message" replace color="info">
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
  customerMessageEntity: storeState.customerMessage.entity,
  loading: storeState.customerMessage.loading,
  updating: storeState.customerMessage.updating,
  updateSuccess: storeState.customerMessage.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CustomerMessageUpdate);
