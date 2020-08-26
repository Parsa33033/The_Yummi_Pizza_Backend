import { IPizzaria } from 'app/shared/model/pizzaria.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface IManager {
  id?: number;
  username?: string;
  email?: string;
  firstName?: string;
  lastName?: string;
  mobileNumber?: string;
  gender?: Gender;
  pizzaria?: IPizzaria;
}

export const defaultValue: Readonly<IManager> = {};
