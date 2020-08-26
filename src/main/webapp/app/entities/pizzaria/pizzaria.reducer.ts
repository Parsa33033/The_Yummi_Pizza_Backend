import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPizzaria, defaultValue } from 'app/shared/model/pizzaria.model';

export const ACTION_TYPES = {
  FETCH_PIZZARIA_LIST: 'pizzaria/FETCH_PIZZARIA_LIST',
  FETCH_PIZZARIA: 'pizzaria/FETCH_PIZZARIA',
  CREATE_PIZZARIA: 'pizzaria/CREATE_PIZZARIA',
  UPDATE_PIZZARIA: 'pizzaria/UPDATE_PIZZARIA',
  DELETE_PIZZARIA: 'pizzaria/DELETE_PIZZARIA',
  SET_BLOB: 'pizzaria/SET_BLOB',
  RESET: 'pizzaria/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPizzaria>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type PizzariaState = Readonly<typeof initialState>;

// Reducer

export default (state: PizzariaState = initialState, action): PizzariaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PIZZARIA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PIZZARIA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PIZZARIA):
    case REQUEST(ACTION_TYPES.UPDATE_PIZZARIA):
    case REQUEST(ACTION_TYPES.DELETE_PIZZARIA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PIZZARIA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PIZZARIA):
    case FAILURE(ACTION_TYPES.CREATE_PIZZARIA):
    case FAILURE(ACTION_TYPES.UPDATE_PIZZARIA):
    case FAILURE(ACTION_TYPES.DELETE_PIZZARIA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIZZARIA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PIZZARIA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PIZZARIA):
    case SUCCESS(ACTION_TYPES.UPDATE_PIZZARIA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PIZZARIA):
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

const apiUrl = 'api/pizzarias';

// Actions

export const getEntities: ICrudGetAllAction<IPizzaria> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PIZZARIA_LIST,
  payload: axios.get<IPizzaria>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IPizzaria> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PIZZARIA,
    payload: axios.get<IPizzaria>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IPizzaria> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PIZZARIA,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPizzaria> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PIZZARIA,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPizzaria> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PIZZARIA,
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
