import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './rating.reducer';
import { IRating } from 'app/shared/model/rating.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRatingDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RatingDetail = (props: IRatingDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { ratingEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Rating [<b>{ratingEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="customerId">Customer Id</span>
          </dt>
          <dd>{ratingEntity.customerId}</dd>
          <dt>
            <span id="menuItemId">Menu Item Id</span>
          </dt>
          <dd>{ratingEntity.menuItemId}</dd>
          <dt>
            <span id="rating">Rating</span>
          </dt>
          <dd>{ratingEntity.rating}</dd>
        </dl>
        <Button tag={Link} to="/rating" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/rating/${ratingEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ rating }: IRootState) => ({
  ratingEntity: rating.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RatingDetail);
