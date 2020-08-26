import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMenuItem, defaultValue } from 'app/shared/model/menu-item.model';

export const ACTION_TYPES = {
  FETCH_MENUITEM_LIST: 'menuItem/FETCH_MENUITEM_LIST',
  FETCH_MENUITEM: 'menuItem/FETCH_MENUITEM',
  CREATE_MENUITEM: 'menuItem/CREATE_MENUITEM',
  UPDATE_MENUITEM: 'menuItem/UPDATE_MENUITEM',
  DELETE_MENUITEM: 'menuItem/DELETE_MENUITEM',
  SET_BLOB: 'menuItem/SET_BLOB',
  RESET: 'menuItem/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMenuItem>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type MenuItemState = Readonly<typeof initialState>;

// Reducer

export default (state: MenuItemState = initialState, action): MenuItemState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MENUITEM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MENUITEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_MENUITEM):
    case REQUEST(ACTION_TYPES.UPDATE_MENUITEM):
    case REQUEST(ACTION_TYPES.DELETE_MENUITEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_MENUITEM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MENUITEM):
    case FAILURE(ACTION_TYPES.CREATE_MENUITEM):
    case FAILURE(ACTION_TYPES.UPDATE_MENUITEM):
    case FAILURE(ACTION_TYPES.DELETE_MENUITEM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MENUITEM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MENUITEM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_MENUITEM):
    case SUCCESS(ACTION_TYPES.UPDATE_MENUITEM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_MENUITEM):
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

const apiUrl = 'api/menu-items';

// Actions

export const getEntities: ICrudGetAllAction<IMenuItem> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MENUITEM_LIST,
  payload: axios.get<IMenuItem>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IMenuItem> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MENUITEM,
    payload: axios.get<IMenuItem>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IMenuItem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MENUITEM,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMenuItem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MENUITEM,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMenuItem> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MENUITEM,
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
