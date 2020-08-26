import { IOrderItem } from 'app/shared/model/order-item.model';
import { IPizzaria } from 'app/shared/model/pizzaria.model';
import { FoodType } from 'app/shared/model/enumerations/food-type.model';

export interface IMenuItem {
  id?: number;
  name?: string;
  description?: any;
  ingredient?: any;
  priceDollor?: number;
  priceEuro?: number;
  type?: FoodType;
  picJpgContentType?: string;
  picJpg?: any;
  picPngContentType?: string;
  picPng?: any;
  orderItem?: IOrderItem;
  pizzaria?: IPizzaria;
}

export const defaultValue: Readonly<IMenuItem> = {};
