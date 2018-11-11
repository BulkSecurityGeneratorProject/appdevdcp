import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAnexoItem, defaultValue } from 'app/shared/model/anexo-item.model';

export const ACTION_TYPES = {
  FETCH_ANEXOITEM_LIST: 'anexoItem/FETCH_ANEXOITEM_LIST',
  FETCH_ANEXOITEM: 'anexoItem/FETCH_ANEXOITEM',
  CREATE_ANEXOITEM: 'anexoItem/CREATE_ANEXOITEM',
  UPDATE_ANEXOITEM: 'anexoItem/UPDATE_ANEXOITEM',
  DELETE_ANEXOITEM: 'anexoItem/DELETE_ANEXOITEM',
  RESET: 'anexoItem/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAnexoItem>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AnexoItemState = Readonly<typeof initialState>;

// Reducer

export default (state: AnexoItemState = initialState, action): AnexoItemState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ANEXOITEM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ANEXOITEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ANEXOITEM):
    case REQUEST(ACTION_TYPES.UPDATE_ANEXOITEM):
    case REQUEST(ACTION_TYPES.DELETE_ANEXOITEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ANEXOITEM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ANEXOITEM):
    case FAILURE(ACTION_TYPES.CREATE_ANEXOITEM):
    case FAILURE(ACTION_TYPES.UPDATE_ANEXOITEM):
    case FAILURE(ACTION_TYPES.DELETE_ANEXOITEM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ANEXOITEM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ANEXOITEM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ANEXOITEM):
    case SUCCESS(ACTION_TYPES.UPDATE_ANEXOITEM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ANEXOITEM):
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

const apiUrl = 'api/anexo-items';

// Actions

export const getEntities: ICrudGetAllAction<IAnexoItem> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ANEXOITEM_LIST,
  payload: axios.get<IAnexoItem>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAnexoItem> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ANEXOITEM,
    payload: axios.get<IAnexoItem>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAnexoItem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ANEXOITEM,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAnexoItem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ANEXOITEM,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAnexoItem> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ANEXOITEM,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
