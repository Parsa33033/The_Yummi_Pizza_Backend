import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICustomerMessage, defaultValue } from 'app/shared/model/customer-message.model';

export const ACTION_TYPES = {
  FETCH_CUSTOMERMESSAGE_LIST: 'customerMessage/FETCH_CUSTOMERMESSAGE_LIST',
  FETCH_CUSTOMERMESSAGE: 'customerMessage/FETCH_CUSTOMERMESSAGE',
  CREATE_CUSTOMERMESSAGE: 'customerMessage/CREATE_CUSTOMERMESSAGE',
  UPDATE_CUSTOMERMESSAGE: 'customerMessage/UPDATE_CUSTOMERMESSAGE',
  DELETE_CUSTOMERMESSAGE: 'customerMessage/DELETE_CUSTOMERMESSAGE',
  SET_BLOB: 'customerMessage/SET_BLOB',
  RESET: 'customerMessage/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICustomerMessage>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type CustomerMessageState = Readonly<typeof initialState>;

// Reducer

export default (state: CustomerMessageState = initialState, action): CustomerMessageState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CUSTOMERMESSAGE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CUSTOMERMESSAGE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CUSTOMERMESSAGE):
    case REQUEST(ACTION_TYPES.UPDATE_CUSTOMERMESSAGE):
    case REQUEST(ACTION_TYPES.DELETE_CUSTOMERMESSAGE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CUSTOMERMESSAGE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CUSTOMERMESSAGE):
    case FAILURE(ACTION_TYPES.CREATE_CUSTOMERMESSAGE):
    case FAILURE(ACTION_TYPES.UPDATE_CUSTOMERMESSAGE):
    case FAILURE(ACTION_TYPES.DELETE_CUSTOMERMESSAGE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CUSTOMERMESSAGE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CUSTOMERMESSAGE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CUSTOMERMESSAGE):
    case SUCCESS(ACTION_TYPES.UPDATE_CUSTOMERMESSAGE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CUSTOMERMESSAGE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.SET_BLOB: {
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType,
        },
      };
    }
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/customer-messages';

// Actions

export const getEntities: ICrudGetAllAction<ICustomerMessage> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CUSTOMERMESSAGE_LIST,
  payload: axios.get<ICustomerMessage>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ICustomerMessage> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CUSTOMERMESSAGE,
    payload: axios.get<ICustomerMessage>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ICustomerMessage> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CUSTOMERMESSAGE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICustomerMessage> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CUSTOMERMESSAGE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICustomerMessage> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CUSTOMERMESSAGE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType,
  },
});

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
