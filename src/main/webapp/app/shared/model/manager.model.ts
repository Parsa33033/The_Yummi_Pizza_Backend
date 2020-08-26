import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface IManager {
  id?: number;
  username?: string;
  email?: string;
  firstName?: string;
  lastName?: string;
  mobileNumber?: string;
  gender?: Gender;
  imageContentType?: string;
  image?: any;
  pizzariaId?: number;
}

export const defaultValue: Readonly<IManager> = {};
