import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown icon="th-list" name="Entities" id="entity-menu" style={{ maxHeight: '80vh', overflow: 'auto' }}>
    <MenuItem icon="asterisk" to="/customer">
      Customer
    </MenuItem>
    <MenuItem icon="asterisk" to="/manager">
      Manager
    </MenuItem>
    <MenuItem icon="asterisk" to="/pizzaria">
      Pizzaria
    </MenuItem>
    <MenuItem icon="asterisk" to="/order">
      Order
    </MenuItem>
    <MenuItem icon="asterisk" to="/order-item">
      Order Item
    </MenuItem>
    <MenuItem icon="asterisk" to="/address">
      Address
    </MenuItem>
    <MenuItem icon="asterisk" to="/menu-item">
      Menu Item
    </MenuItem>
    <MenuItem icon="asterisk" to="/rating">
      Rating
    </MenuItem>
    <MenuItem icon="asterisk" to="/customer-message">
      Customer Message
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
