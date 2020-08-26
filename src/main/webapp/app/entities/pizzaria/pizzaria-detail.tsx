import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pizzaria.reducer';
import { IPizzaria } from 'app/shared/model/pizzaria.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPizzariaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PizzariaDetail = (props: IPizzariaDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { pizzariaEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Pizzaria [<b>{pizzariaEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{pizzariaEntity.name}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{pizzariaEntity.description}</dd>
          <dt>
            <span id="aboutus">Aboutus</span>
          </dt>
          <dd>{pizzariaEntity.aboutus}</dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{pizzariaEntity.email}</dd>
          <dt>
            <span id="openHours">Open Hours</span>
          </dt>
          <dd>{pizzariaEntity.openHours}</dd>
          <dt>
            <span id="openDays">Open Days</span>
          </dt>
          <dd>{pizzariaEntity.openDays}</dd>
          <dt>
            <span id="deliveryPriceInDollor">Delivery Price In Dollor</span>
          </dt>
          <dd>{pizzariaEntity.deliveryPriceInDollor}</dd>
          <dt>
            <span id="deliveryPriceInEuro">Delivery Price In Euro</span>
          </dt>
          <dd>{pizzariaEntity.deliveryPriceInEuro}</dd>
          <dt>
            <span id="staff">Staff</span>
          </dt>
          <dd>{pizzariaEntity.staff}</dd>
          <dt>
            <span id="customers">Customers</span>
          </dt>
          <dd>{pizzariaEntity.customers}</dd>
          <dt>
            <span id="numberOfAwards">Number Of Awards</span>
          </dt>
          <dd>{pizzariaEntity.numberOfAwards}</dd>
          <dt>
            <span id="pizzaBranches">Pizza Branches</span>
          </dt>
          <dd>{pizzariaEntity.pizzaBranches}</dd>
          <dt>Manager</dt>
          <dd>{pizzariaEntity.managerId ? pizzariaEntity.managerId : ''}</dd>
          <dt>Address</dt>
          <dd>{pizzariaEntity.addressId ? pizzariaEntity.addressId : ''}</dd>
        </dl>
        <Button tag={Link} to="/pizzaria" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/pizzaria/${pizzariaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ pizzaria }: IRootState) => ({
  pizzariaEntity: pizzaria.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PizzariaDetail);
