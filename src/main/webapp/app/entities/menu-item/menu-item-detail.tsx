import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './menu-item.reducer';
import { IMenuItem } from 'app/shared/model/menu-item.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMenuItemDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MenuItemDetail = (props: IMenuItemDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { menuItemEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          MenuItem [<b>{menuItemEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{menuItemEntity.name}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{menuItemEntity.description}</dd>
          <dt>
            <span id="ingredient">Ingredient</span>
          </dt>
          <dd>{menuItemEntity.ingredient}</dd>
          <dt>
            <span id="priceDollor">Price Dollor</span>
          </dt>
          <dd>{menuItemEntity.priceDollor}</dd>
          <dt>
            <span id="priceEuro">Price Euro</span>
          </dt>
          <dd>{menuItemEntity.priceEuro}</dd>
          <dt>
            <span id="type">Type</span>
          </dt>
          <dd>{menuItemEntity.type}</dd>
          <dt>
            <span id="picJpg">Pic Jpg</span>
          </dt>
          <dd>
            {menuItemEntity.picJpg ? (
              <div>
                {menuItemEntity.picJpgContentType ? (
                  <a onClick={openFile(menuItemEntity.picJpgContentType, menuItemEntity.picJpg)}>
                    <img src={`data:${menuItemEntity.picJpgContentType};base64,${menuItemEntity.picJpg}`} style={{ maxHeight: '30px' }} />
                  </a>
                ) : null}
                <span>
                  {menuItemEntity.picJpgContentType}, {byteSize(menuItemEntity.picJpg)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="picPng">Pic Png</span>
          </dt>
          <dd>
            {menuItemEntity.picPng ? (
              <div>
                {menuItemEntity.picPngContentType ? (
                  <a onClick={openFile(menuItemEntity.picPngContentType, menuItemEntity.picPng)}>
                    <img src={`data:${menuItemEntity.picPngContentType};base64,${menuItemEntity.picPng}`} style={{ maxHeight: '30px' }} />
                  </a>
                ) : null}
                <span>
                  {menuItemEntity.picPngContentType}, {byteSize(menuItemEntity.picPng)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>Pizzaria</dt>
          <dd>{menuItemEntity.pizzariaId ? menuItemEntity.pizzariaId : ''}</dd>
        </dl>
        <Button tag={Link} to="/menu-item" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/menu-item/${menuItemEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ menuItem }: IRootState) => ({
  menuItemEntity: menuItem.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MenuItemDetail);
