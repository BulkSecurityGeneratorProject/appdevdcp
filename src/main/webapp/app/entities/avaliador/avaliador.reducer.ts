import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAvaliador, defaultValue } from 'app/shared/model/avaliador.model';

export const ACTION_TYPES = {
  FETCH_AVALIADOR_LIST: 'avaliador/FETCH_AVALIADOR_LIST',
  FETCH_AVALIADOR: 'avaliador/FETCH_AVALIADOR',
  CREATE_AVALIADOR: 'avaliador/CREATE_AVALIADOR',
  UPDATE_AVALIADOR: 'avaliador/UPDATE_AVALIADOR',
  DELETE_AVALIADOR: 'avaliador/DELETE_AVALIADOR',
  RESET: 'avaliador/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAvaliador>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type AvaliadorState = Readonly<typeof initialState>;

// Reducer

export default (state: AvaliadorState = initialState, action): AvaliadorState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_AVALIADOR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_AVALIADOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_AVALIADOR):
    case REQUEST(ACTION_TYPES.UPDATE_AVALIADOR):
    case REQUEST(ACTION_TYPES.DELETE_AVALIADOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_AVALIADOR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_AVALIADOR):
    case FAILURE(ACTION_TYPES.CREATE_AVALIADOR):
    case FAILURE(ACTION_TYPES.UPDATE_AVALIADOR):
    case FAILURE(ACTION_TYPES.DELETE_AVALIADOR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_AVALIADOR_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_AVALIADOR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_AVALIADOR):
    case SUCCESS(ACTION_TYPES.UPDATE_AVALIADOR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_AVALIADOR):
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

const apiUrl = 'api/avaliadors';

// Actions

export const getEntities: ICrudGetAllAction<IAvaliador> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_AVALIADOR_LIST,
    payload: axios.get<IAvaliador>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IAvaliador> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_AVALIADOR,
    payload: axios.get<IAvaliador>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAvaliador> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_AVALIADOR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAvaliador> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_AVALIADOR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAvaliador> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_AVALIADOR,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
