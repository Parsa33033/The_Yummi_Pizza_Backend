import { Moment } from 'moment';
import { IAddress } from 'app/shared/model/address.model';
import { IOrderItem } from 'app/shared/model/order-item.model';
import { ICustomer } from 'app/shared/model/customer.model';
import { IPizzaria } from 'app/shared/model/pizzaria.model';

export interface IOrder {
  id?: number;
  date?: string;
  totalPrice?: number;
  delivered?: boolean;
  address?: IAddress;
  items?: IOrderItem[];
  customer?: ICustomer;
  pizzaria?: IPizzaria;
}

export const defaultValue: Readonly<IOrder> = {
  delivered: false,
};
