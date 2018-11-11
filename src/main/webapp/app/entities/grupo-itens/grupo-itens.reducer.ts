import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IGrupoItens, defaultValue } from 'app/shared/model/grupo-itens.model';

export const ACTION_TYPES = {
  FETCH_GRUPOITENS_LIST: 'grupoItens/FETCH_GRUPOITENS_LIST',
  FETCH_GRUPOITENS: 'grupoItens/FETCH_GRUPOITENS',
  CREATE_GRUPOITENS: 'grupoItens/CREATE_GRUPOITENS',
  UPDATE_GRUPOITENS: 'grupoItens/UPDATE_GRUPOITENS',
  DELETE_GRUPOITENS: 'grupoItens/DELETE_GRUPOITENS',
  RESET: 'grupoItens/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IGrupoItens>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type GrupoItensState = Readonly<typeof initialState>;

// Reducer

export default (state: GrupoItensState = initialState, action): GrupoItensState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_GRUPOITENS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_GRUPOITENS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_GRUPOITENS):
    case REQUEST(ACTION_TYPES.UPDATE_GRUPOITENS):
    case REQUEST(ACTION_TYPES.DELETE_GRUPOITENS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_GRUPOITENS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_GRUPOITENS):
    case FAILURE(ACTION_TYPES.CREATE_GRUPOITENS):
    case FAILURE(ACTION_TYPES.UPDATE_GRUPOITENS):
    case FAILURE(ACTION_TYPES.DELETE_GRUPOITENS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_GRUPOITENS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_GRUPOITENS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_GRUPOITENS):
    case SUCCESS(ACTION_TYPES.UPDATE_GRUPOITENS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_GRUPOITENS):
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

const apiUrl = 'api/grupo-itens';

// Actions

export const getEntities: ICrudGetAllAction<IGrupoItens> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_GRUPOITENS_LIST,
  payload: axios.get<IGrupoItens>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IGrupoItens> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_GRUPOITENS,
    payload: axios.get<IGrupoItens>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IGrupoItens> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_GRUPOITENS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IGrupoItens> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_GRUPOITENS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IGrupoItens> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_GRUPOITENS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
