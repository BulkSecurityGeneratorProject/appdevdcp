import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IItemAvaliacao, defaultValue } from 'app/shared/model/item-avaliacao.model';

export const ACTION_TYPES = {
  FETCH_ITEMAVALIACAO_LIST: 'itemAvaliacao/FETCH_ITEMAVALIACAO_LIST',
  FETCH_ITEMAVALIACAO: 'itemAvaliacao/FETCH_ITEMAVALIACAO',
  CREATE_ITEMAVALIACAO: 'itemAvaliacao/CREATE_ITEMAVALIACAO',
  UPDATE_ITEMAVALIACAO: 'itemAvaliacao/UPDATE_ITEMAVALIACAO',
  DELETE_ITEMAVALIACAO: 'itemAvaliacao/DELETE_ITEMAVALIACAO',
  RESET: 'itemAvaliacao/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IItemAvaliacao>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ItemAvaliacaoState = Readonly<typeof initialState>;

// Reducer

export default (state: ItemAvaliacaoState = initialState, action): ItemAvaliacaoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ITEMAVALIACAO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ITEMAVALIACAO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ITEMAVALIACAO):
    case REQUEST(ACTION_TYPES.UPDATE_ITEMAVALIACAO):
    case REQUEST(ACTION_TYPES.DELETE_ITEMAVALIACAO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ITEMAVALIACAO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ITEMAVALIACAO):
    case FAILURE(ACTION_TYPES.CREATE_ITEMAVALIACAO):
    case FAILURE(ACTION_TYPES.UPDATE_ITEMAVALIACAO):
    case FAILURE(ACTION_TYPES.DELETE_ITEMAVALIACAO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ITEMAVALIACAO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ITEMAVALIACAO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ITEMAVALIACAO):
    case SUCCESS(ACTION_TYPES.UPDATE_ITEMAVALIACAO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ITEMAVALIACAO):
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

const apiUrl = 'api/item-avaliacaos';

// Actions

export const getEntities: ICrudGetAllAction<IItemAvaliacao> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ITEMAVALIACAO_LIST,
  payload: axios.get<IItemAvaliacao>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IItemAvaliacao> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ITEMAVALIACAO,
    payload: axios.get<IItemAvaliacao>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IItemAvaliacao> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ITEMAVALIACAO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IItemAvaliacao> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ITEMAVALIACAO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IItemAvaliacao> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ITEMAVALIACAO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
