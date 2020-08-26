export interface IRating {
  id?: number;
  customerId?: number;
  menuItemId?: number;
  rating?: number;
}

export const defaultValue: Readonly<IRating> = {};
