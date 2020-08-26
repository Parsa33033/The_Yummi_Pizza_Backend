import { IOrder } from 'app/shared/model/order.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface ICustomer {
  id?: number;
  username?: string;
  email?: string;
  firstName?: string;
  lastName?: string;
  mobileNumber?: string;
  gender?: Gender;
  imageContentType?: string;
  image?: any;
  addressId?: number;
  orders?: IOrder[];
}

export const defaultValue: Readonly<ICustomer> = {};
