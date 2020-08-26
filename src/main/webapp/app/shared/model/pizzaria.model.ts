import { IOrder } from 'app/shared/model/order.model';
import { IMenuItem } from 'app/shared/model/menu-item.model';

export interface IPizzaria {
  id?: number;
  name?: string;
  description?: any;
  openHours?: string;
  openDays?: string;
  deliveryPrice?: number;
  staff?: number;
  customers?: number;
  numberOfAwards?: number;
  pizzaBranches?: number;
  managerId?: number;
  addressId?: number;
  orders?: IOrder[];
  items?: IMenuItem[];
}

export const defaultValue: Readonly<IPizzaria> = {};
