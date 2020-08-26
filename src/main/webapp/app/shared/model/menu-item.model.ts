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
  orderItemId?: number;
  pizzariaId?: number;
}

export const defaultValue: Readonly<IMenuItem> = {};
