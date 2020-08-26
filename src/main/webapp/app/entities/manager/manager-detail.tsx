import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './manager.reducer';
import { IManager } from 'app/shared/model/manager.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IManagerDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ManagerDetail = (props: IManagerDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { managerEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Manager [<b>{managerEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="username">Username</span>
          </dt>
          <dd>{managerEntity.username}</dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{managerEntity.email}</dd>
          <dt>
            <span id="firstName">First Name</span>
          </dt>
          <dd>{managerEntity.firstName}</dd>
          <dt>
            <span id="lastName">Last Name</span>
          </dt>
          <dd>{managerEntity.lastName}</dd>
          <dt>
            <span id="mobileNumber">Mobile Number</span>
          </dt>
          <dd>{managerEntity.mobileNumber}</dd>
          <dt>
            <span id="gender">Gender</span>
          </dt>
          <dd>{managerEntity.gender}</dd>
          <dt>
            <span id="image">Image</span>
          </dt>
          <dd>
            {managerEntity.image ? (
              <div>
                {managerEntity.imageContentType ? (
                  <a onClick={openFile(managerEntity.imageContentType, managerEntity.image)}>
                    <img src={`data:${managerEntity.imageContentType};base64,${managerEntity.image}`} style={{ maxHeight: '30px' }} />
                  </a>
                ) : null}
                <span>
                  {managerEntity.imageContentType}, {byteSize(managerEntity.image)}
                </span>
              </div>
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/manager" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/manager/${managerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ manager }: IRootState) => ({
  managerEntity: manager.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ManagerDetail);
