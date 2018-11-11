import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILoja, defaultValue } from 'app/shared/model/loja.model';

export const ACTION_TYPES = {
  FETCH_LOJA_LIST: 'loja/FETCH_LOJA_LIST',
  FETCH_LOJA: 'loja/FETCH_LOJA',
  CREATE_LOJA: 'loja/CREATE_LOJA',
  UPDATE_LOJA: 'loja/UPDATE_LOJA',
  DELETE_LOJA: 'loja/DELETE_LOJA',
  RESET: 'loja/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILoja>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type LojaState = Readonly<typeof initialState>;

// Reducer

export default (state: LojaState = initialState, action): LojaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LOJA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LOJA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LOJA):
    case REQUEST(ACTION_TYPES.UPDATE_LOJA):
    case REQUEST(ACTION_TYPES.DELETE_LOJA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LOJA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LOJA):
    case FAILURE(ACTION_TYPES.CREATE_LOJA):
    case FAILURE(ACTION_TYPES.UPDATE_LOJA):
    case FAILURE(ACTION_TYPES.DELETE_LOJA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOJA_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOJA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LOJA):
    case SUCCESS(ACTION_TYPES.UPDATE_LOJA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LOJA):
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

const apiUrl = 'api/lojas';

// Actions

export const getEntities: ICrudGetAllAction<ILoja> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LOJA_LIST,
    payload: axios.get<ILoja>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ILoja> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LOJA,
    payload: axios.get<ILoja>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILoja> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LOJA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILoja> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LOJA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILoja> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LOJA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
