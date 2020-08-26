export interface ICustomerMessage {
  id?: number;
  name?: string;
  email?: string;
  subject?: string;
  message?: any;
}

export const defaultValue: Readonly<ICustomerMessage> = {};
