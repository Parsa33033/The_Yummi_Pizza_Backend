import { IManager } from 'app/shared/model/manager.model';
import { IAddress } from 'app/shared/model/address.model';
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
  manager?: IManager;
  address?: IAddress;
  orders?: IOrder[];
  items?: IMenuItem[];
}

export const defaultValue: Readonly<IPizzaria> = {};
