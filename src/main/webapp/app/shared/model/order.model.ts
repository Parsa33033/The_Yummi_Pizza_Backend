import { Moment } from 'moment';
import { IOrderItem } from 'app/shared/model/order-item.model';
import { Currency } from 'app/shared/model/enumerations/currency.model';

export interface IOrder {
  id?: number;
  date?: string;
  totalPrice?: number;
  paidIn?: Currency;
  delivered?: boolean;
  addressId?: number;
  items?: IOrderItem[];
  customerId?: number;
  pizzariaId?: number;
}

export const defaultValue: Readonly<IOrder> = {
  delivered: false,
};
