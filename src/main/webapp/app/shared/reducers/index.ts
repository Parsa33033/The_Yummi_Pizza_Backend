import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import customer, {
  CustomerState
} from 'app/entities/customer/customer.reducer';
// prettier-ignore
import manager, {
  ManagerState
} from 'app/entities/manager/manager.reducer';
// prettier-ignore
import pizzaria, {
  PizzariaState
} from 'app/entities/pizzaria/pizzaria.reducer';
// prettier-ignore
import order, {
  OrderState
} from 'app/entities/order/order.reducer';
// prettier-ignore
import orderItem, {
  OrderItemState
} from 'app/entities/order-item/order-item.reducer';
// prettier-ignore
import address, {
  AddressState
} from 'app/entities/address/address.reducer';
// prettier-ignore
import menuItem, {
  MenuItemState
} from 'app/entities/menu-item/menu-item.reducer';
// prettier-ignore
import rating, {
  RatingState
} from 'app/entities/rating/rating.reducer';
// prettier-ignore
import customerMessage, {
  CustomerMessageState
} from 'app/entities/customer-message/customer-message.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly customer: CustomerState;
  readonly manager: ManagerState;
  readonly pizzaria: PizzariaState;
  readonly order: OrderState;
  readonly orderItem: OrderItemState;
  readonly address: AddressState;
  readonly menuItem: MenuItemState;
  readonly rating: RatingState;
  readonly customerMessage: CustomerMessageState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  customer,
  manager,
  pizzaria,
  order,
  orderItem,
  address,
  menuItem,
  rating,
  customerMessage,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
});

export default rootReducer;
