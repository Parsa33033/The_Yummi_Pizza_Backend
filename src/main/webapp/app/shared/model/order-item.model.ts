export interface IOrderItem {
  id?: number;
  number?: number;
  menuItemId?: number;
  orderId?: number;
}

export const defaultValue: Readonly<IOrderItem> = {};
