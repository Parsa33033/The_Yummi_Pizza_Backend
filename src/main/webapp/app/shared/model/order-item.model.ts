import { IMenuItem } from 'app/shared/model/menu-item.model';
import { IOrder } from 'app/shared/model/order.model';

export interface IOrderItem {
  id?: number;
  number?: number;
  menuItem?: IMenuItem;
  order?: IOrder;
}

export const defaultValue: Readonly<IOrderItem> = {};
