import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IManager, defaultValue } from 'app/shared/model/manager.model';

export const ACTION_TYPES = {
  FETCH_MANAGER_LIST: 'manager/FETCH_MANAGER_LIST',
  FETCH_MANAGER: 'manager/FETCH_MANAGER',
  CREATE_MANAGER: 'manager/CREATE_MANAGER',
  UPDATE_MANAGER: 'manager/UPDATE_MANAGER',
  DELETE_MANAGER: 'manager/DELETE_MANAGER',
  RESET: 'manager/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IManager>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ManagerState = Readonly<typeof initialState>;

// Reducer

export default (state: ManagerState = initialState, action): ManagerState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MANAGER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MANAGER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_MANAGER):
    case REQUEST(ACTION_TYPES.UPDATE_MANAGER):
    case REQUEST(ACTION_TYPES.DELETE_MANAGER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_MANAGER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MANAGER):
    case FAILURE(ACTION_TYPES.CREATE_MANAGER):
    case FAILURE(ACTION_TYPES.UPDATE_MANAGER):
    case FAILURE(ACTION_TYPES.DELETE_MANAGER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MANAGER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MANAGER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_MANAGER):
    case SUCCESS(ACTION_TYPES.UPDATE_MANAGER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_MANAGER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/managers';

// Actions

export const getEntities: ICrudGetAllAction<IManager> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MANAGER_LIST,
  payload: axios.get<IManager>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IManager> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MANAGER,
    payload: axios.get<IManager>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IManager> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MANAGER,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IManager> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MANAGER,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IManager> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MANAGER,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
