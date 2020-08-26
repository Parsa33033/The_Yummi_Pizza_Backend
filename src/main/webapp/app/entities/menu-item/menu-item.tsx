import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './menu-item.reducer';
import { IMenuItem } from 'app/shared/model/menu-item.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMenuItemProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const MenuItem = (props: IMenuItemProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { menuItemList, match, loading } = props;
  return (
    <div>
      <h2 id="menu-item-heading">
        Menu Items
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Menu Item
        </Link>
      </h2>
      <div className="table-responsive">
        {menuItemList && menuItemList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Ingredient</th>
                <th>Price Dollor</th>
                <th>Price Euro</th>
                <th>Type</th>
                <th>Pic Jpg</th>
                <th>Pic Png</th>
                <th>Pizzaria</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {menuItemList.map((menuItem, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${menuItem.id}`} color="link" size="sm">
                      {menuItem.id}
                    </Button>
                  </td>
                  <td>{menuItem.name}</td>
                  <td>{menuItem.description}</td>
                  <td>{menuItem.ingredient}</td>
                  <td>{menuItem.priceDollor}</td>
                  <td>{menuItem.priceEuro}</td>
                  <td>{menuItem.type}</td>
                  <td>
                    {menuItem.picJpg ? (
                      <div>
                        {menuItem.picJpgContentType ? (
                          <a onClick={openFile(menuItem.picJpgContentType, menuItem.picJpg)}>
                            <img src={`data:${menuItem.picJpgContentType};base64,${menuItem.picJpg}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {menuItem.picJpgContentType}, {byteSize(menuItem.picJpg)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {menuItem.picPng ? (
                      <div>
                        {menuItem.picPngContentType ? (
                          <a onClick={openFile(menuItem.picPngContentType, menuItem.picPng)}>
                            <img src={`data:${menuItem.picPngContentType};base64,${menuItem.picPng}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {menuItem.picPngContentType}, {byteSize(menuItem.picPng)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{menuItem.pizzaria ? <Link to={`pizzaria/${menuItem.pizzaria.id}`}>{menuItem.pizzaria.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${menuItem.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${menuItem.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${menuItem.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Menu Items found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ menuItem }: IRootState) => ({
  menuItemList: menuItem.entities,
  loading: menuItem.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MenuItem);
