import { ICustomer } from 'app/shared/model/customer.model';
import { IOrder } from 'app/shared/model/order.model';
import { IPizzaria } from 'app/shared/model/pizzaria.model';

export interface IAddress {
  id?: number;
  phoneNumber?: string;
  country?: string;
  state?: string;
  city?: string;
  address1?: string;
  address2?: string;
  customer?: ICustomer;
  order?: IOrder;
  pizzaria?: IPizzaria;
}

export const defaultValue: Readonly<IAddress> = {};
