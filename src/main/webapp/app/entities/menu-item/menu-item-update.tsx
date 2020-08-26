import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IOrderItem } from 'app/shared/model/order-item.model';
import { getEntities as getOrderItems } from 'app/entities/order-item/order-item.reducer';
import { IPizzaria } from 'app/shared/model/pizzaria.model';
import { getEntities as getPizzarias } from 'app/entities/pizzaria/pizzaria.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './menu-item.reducer';
import { IMenuItem } from 'app/shared/model/menu-item.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMenuItemUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MenuItemUpdate = (props: IMenuItemUpdateProps) => {
  const [orderItemId, setOrderItemId] = useState('0');
  const [pizzariaId, setPizzariaId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { menuItemEntity, orderItems, pizzarias, loading, updating } = props;

  const { description, ingredient, picJpg, picJpgContentType, picPng, picPngContentType } = menuItemEntity;

  const handleClose = () => {
    props.history.push('/menu-item');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getOrderItems();
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
        ...menuItemEntity,
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
          <h2 id="theYummiPizzaBackendApp.menuItem.home.createOrEditLabel">Create or edit a MenuItem</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : menuItemEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="menu-item-id">ID</Label>
                  <AvInput id="menu-item-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="menu-item-name">
                  Name
                </Label>
                <AvField id="menu-item-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="menu-item-description">
                  Description
                </Label>
                <AvInput id="menu-item-description" type="textarea" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="ingredientLabel" for="menu-item-ingredient">
                  Ingredient
                </Label>
                <AvInput id="menu-item-ingredient" type="textarea" name="ingredient" />
              </AvGroup>
              <AvGroup>
                <Label id="priceDollorLabel" for="menu-item-priceDollor">
                  Price Dollor
                </Label>
                <AvField id="menu-item-priceDollor" type="string" className="form-control" name="priceDollor" />
              </AvGroup>
              <AvGroup>
                <Label id="priceEuroLabel" for="menu-item-priceEuro">
                  Price Euro
                </Label>
                <AvField id="menu-item-priceEuro" type="string" className="form-control" name="priceEuro" />
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="menu-item-type">
                  Type
                </Label>
                <AvInput
                  id="menu-item-type"
                  type="select"
                  className="form-control"
                  name="type"
                  value={(!isNew && menuItemEntity.type) || 'PIZZA'}
                >
                  <option value="PIZZA">PIZZA</option>
                  <option value="BURGER">BURGER</option>
                  <option value="PASTA">PASTA</option>
                  <option value="DRINK">DRINK</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="picJpgLabel" for="picJpg">
                    Pic Jpg
                  </Label>
                  <br />
                  {picJpg ? (
                    <div>
                      {picJpgContentType ? (
                        <a onClick={openFile(picJpgContentType, picJpg)}>
                          <img src={`data:${picJpgContentType};base64,${picJpg}`} style={{ maxHeight: '100px' }} />
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {picJpgContentType}, {byteSize(picJpg)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('picJpg')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_picJpg" type="file" onChange={onBlobChange(true, 'picJpg')} accept="image/*" />
                  <AvInput type="hidden" name="picJpg" value={picJpg} />
                </AvGroup>
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="picPngLabel" for="picPng">
                    Pic Png
                  </Label>
                  <br />
                  {picPng ? (
                    <div>
                      {picPngContentType ? (
                        <a onClick={openFile(picPngContentType, picPng)}>
                          <img src={`data:${picPngContentType};base64,${picPng}`} style={{ maxHeight: '100px' }} />
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {picPngContentType}, {byteSize(picPng)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('picPng')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_picPng" type="file" onChange={onBlobChange(true, 'picPng')} accept="image/*" />
                  <AvInput type="hidden" name="picPng" value={picPng} />
                </AvGroup>
              </AvGroup>
              <AvGroup>
                <Label for="menu-item-pizzaria">Pizzaria</Label>
                <AvInput id="menu-item-pizzaria" type="select" className="form-control" name="pizzariaId">
                  <option value="" key="0" />
                  {pizzarias
                    ? pizzarias.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/menu-item" replace color="info">
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
  orderItems: storeState.orderItem.entities,
  pizzarias: storeState.pizzaria.entities,
  menuItemEntity: storeState.menuItem.entity,
  loading: storeState.menuItem.loading,
  updating: storeState.menuItem.updating,
  updateSuccess: storeState.menuItem.updateSuccess,
});

const mapDispatchToProps = {
  getOrderItems,
  getPizzarias,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MenuItemUpdate);
