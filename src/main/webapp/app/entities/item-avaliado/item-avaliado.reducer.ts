import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IItemAvaliado, defaultValue } from 'app/shared/model/item-avaliado.model';

export const ACTION_TYPES = {
  FETCH_ITEMAVALIADO_LIST: 'itemAvaliado/FETCH_ITEMAVALIADO_LIST',
  FETCH_ITEMAVALIADO: 'itemAvaliado/FETCH_ITEMAVALIADO',
  CREATE_ITEMAVALIADO: 'itemAvaliado/CREATE_ITEMAVALIADO',
  UPDATE_ITEMAVALIADO: 'itemAvaliado/UPDATE_ITEMAVALIADO',
  DELETE_ITEMAVALIADO: 'itemAvaliado/DELETE_ITEMAVALIADO',
  RESET: 'itemAvaliado/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IItemAvaliado>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ItemAvaliadoState = Readonly<typeof initialState>;

// Reducer

export default (state: ItemAvaliadoState = initialState, action): ItemAvaliadoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ITEMAVALIADO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ITEMAVALIADO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ITEMAVALIADO):
    case REQUEST(ACTION_TYPES.UPDATE_ITEMAVALIADO):
    case REQUEST(ACTION_TYPES.DELETE_ITEMAVALIADO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ITEMAVALIADO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ITEMAVALIADO):
    case FAILURE(ACTION_TYPES.CREATE_ITEMAVALIADO):
    case FAILURE(ACTION_TYPES.UPDATE_ITEMAVALIADO):
    case FAILURE(ACTION_TYPES.DELETE_ITEMAVALIADO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ITEMAVALIADO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ITEMAVALIADO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ITEMAVALIADO):
    case SUCCESS(ACTION_TYPES.UPDATE_ITEMAVALIADO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ITEMAVALIADO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/item-avaliados';

// Actions

export const getEntities: ICrudGetAllAction<IItemAvaliado> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ITEMAVALIADO_LIST,
  payload: axios.get<IItemAvaliado>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IItemAvaliado> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ITEMAVALIADO,
    payload: axios.get<IItemAvaliado>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IItemAvaliado> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ITEMAVALIADO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IItemAvaliado> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ITEMAVALIADO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IItemAvaliado> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ITEMAVALIADO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
