import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './customer-message.reducer';
import { ICustomerMessage } from 'app/shared/model/customer-message.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICustomerMessageDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CustomerMessageDetail = (props: ICustomerMessageDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { customerMessageEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          CustomerMessage [<b>{customerMessageEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{customerMessageEntity.name}</dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{customerMessageEntity.email}</dd>
          <dt>
            <span id="subject">Subject</span>
          </dt>
          <dd>{customerMessageEntity.subject}</dd>
          <dt>
            <span id="message">Message</span>
          </dt>
          <dd>{customerMessageEntity.message}</dd>
        </dl>
        <Button tag={Link} to="/customer-message" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/customer-message/${customerMessageEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ customerMessage }: IRootState) => ({
  customerMessageEntity: customerMessage.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CustomerMessageDetail);
