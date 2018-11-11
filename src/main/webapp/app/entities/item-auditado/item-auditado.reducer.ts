import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IItemAuditado, defaultValue } from 'app/shared/model/item-auditado.model';

export const ACTION_TYPES = {
  FETCH_ITEMAUDITADO_LIST: 'itemAuditado/FETCH_ITEMAUDITADO_LIST',
  FETCH_ITEMAUDITADO: 'itemAuditado/FETCH_ITEMAUDITADO',
  CREATE_ITEMAUDITADO: 'itemAuditado/CREATE_ITEMAUDITADO',
  UPDATE_ITEMAUDITADO: 'itemAuditado/UPDATE_ITEMAUDITADO',
  DELETE_ITEMAUDITADO: 'itemAuditado/DELETE_ITEMAUDITADO',
  RESET: 'itemAuditado/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IItemAuditado>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ItemAuditadoState = Readonly<typeof initialState>;

// Reducer

export default (state: ItemAuditadoState = initialState, action): ItemAuditadoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ITEMAUDITADO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ITEMAUDITADO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ITEMAUDITADO):
    case REQUEST(ACTION_TYPES.UPDATE_ITEMAUDITADO):
    case REQUEST(ACTION_TYPES.DELETE_ITEMAUDITADO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ITEMAUDITADO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ITEMAUDITADO):
    case FAILURE(ACTION_TYPES.CREATE_ITEMAUDITADO):
    case FAILURE(ACTION_TYPES.UPDATE_ITEMAUDITADO):
    case FAILURE(ACTION_TYPES.DELETE_ITEMAUDITADO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ITEMAUDITADO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ITEMAUDITADO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ITEMAUDITADO):
    case SUCCESS(ACTION_TYPES.UPDATE_ITEMAUDITADO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ITEMAUDITADO):
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

const apiUrl = 'api/item-auditados';

// Actions

export const getEntities: ICrudGetAllAction<IItemAuditado> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ITEMAUDITADO_LIST,
  payload: axios.get<IItemAuditado>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IItemAuditado> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ITEMAUDITADO,
    payload: axios.get<IItemAuditado>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IItemAuditado> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ITEMAUDITADO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IItemAuditado> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ITEMAUDITADO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IItemAuditado> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ITEMAUDITADO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
