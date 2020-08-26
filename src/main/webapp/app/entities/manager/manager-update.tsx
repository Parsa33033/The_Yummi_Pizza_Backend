import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPizzaria } from 'app/shared/model/pizzaria.model';
import { getEntities as getPizzarias } from 'app/entities/pizzaria/pizzaria.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './manager.reducer';
import { IManager } from 'app/shared/model/manager.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IManagerUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ManagerUpdate = (props: IManagerUpdateProps) => {
  const [pizzariaId, setPizzariaId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { managerEntity, pizzarias, loading, updating } = props;

  const { image, imageContentType } = managerEntity;

  const handleClose = () => {
    props.history.push('/manager');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getPizzarias();
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
        ...managerEntity,
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
          <h2 id="theYummiPizzaBackendApp.manager.home.createOrEditLabel">Create or edit a Manager</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : managerEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="manager-id">ID</Label>
                  <AvInput id="manager-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="usernameLabel" for="manager-username">
                  Username
                </Label>
                <AvField id="manager-username" type="text" name="username" />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="manager-email">
                  Email
                </Label>
                <AvField id="manager-email" type="text" name="email" />
              </AvGroup>
              <AvGroup>
                <Label id="firstNameLabel" for="manager-firstName">
                  First Name
                </Label>
                <AvField id="manager-firstName" type="text" name="firstName" />
              </AvGroup>
              <AvGroup>
                <Label id="lastNameLabel" for="manager-lastName">
                  Last Name
                </Label>
                <AvField id="manager-lastName" type="text" name="lastName" />
              </AvGroup>
              <AvGroup>
                <Label id="mobileNumberLabel" for="manager-mobileNumber">
                  Mobile Number
                </Label>
                <AvField id="manager-mobileNumber" type="text" name="mobileNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="genderLabel" for="manager-gender">
                  Gender
                </Label>
                <AvInput
                  id="manager-gender"
                  type="select"
                  className="form-control"
                  name="gender"
                  value={(!isNew && managerEntity.gender) || 'MALE'}
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
              <Button tag={Link} id="cancel-save" to="/manager" replace color="info">
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
  pizzarias: storeState.pizzaria.entities,
  managerEntity: storeState.manager.entity,
  loading: storeState.manager.loading,
  updating: storeState.manager.updating,
  updateSuccess: storeState.manager.updateSuccess,
});

const mapDispatchToProps = {
  getPizzarias,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ManagerUpdate);
