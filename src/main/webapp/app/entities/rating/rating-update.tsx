import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './rating.reducer';
import { IRating } from 'app/shared/model/rating.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IRatingUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RatingUpdate = (props: IRatingUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { ratingEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/rating');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...ratingEntity,
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
          <h2 id="theYummiPizzaBackendApp.rating.home.createOrEditLabel">Create or edit a Rating</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : ratingEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="rating-id">ID</Label>
                  <AvInput id="rating-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="customerIdLabel" for="rating-customerId">
                  Customer Id
                </Label>
                <AvField id="rating-customerId" type="string" className="form-control" name="customerId" />
              </AvGroup>
              <AvGroup>
                <Label id="menuItemIdLabel" for="rating-menuItemId">
                  Menu Item Id
                </Label>
                <AvField id="rating-menuItemId" type="string" className="form-control" name="menuItemId" />
              </AvGroup>
              <AvGroup>
                <Label id="ratingLabel" for="rating-rating">
                  Rating
                </Label>
                <AvField id="rating-rating" type="string" className="form-control" name="rating" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/rating" replace color="info">
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
  ratingEntity: storeState.rating.entity,
  loading: storeState.rating.loading,
  updating: storeState.rating.updating,
  updateSuccess: storeState.rating.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RatingUpdate);
