export interface IAddress {
  id?: number;
  phoneNumber?: string;
  country?: string;
  state?: string;
  city?: string;
  address1?: string;
  address2?: string;
  customerId?: number;
  orderId?: number;
  pizzariaId?: number;
}

export const defaultValue: Readonly<IAddress> = {};
