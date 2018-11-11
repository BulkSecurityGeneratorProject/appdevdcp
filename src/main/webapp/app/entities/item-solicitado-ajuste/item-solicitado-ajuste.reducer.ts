import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IItemSolicitadoAjuste, defaultValue } from 'app/shared/model/item-solicitado-ajuste.model';

export const ACTION_TYPES = {
  FETCH_ITEMSOLICITADOAJUSTE_LIST: 'itemSolicitadoAjuste/FETCH_ITEMSOLICITADOAJUSTE_LIST',
  FETCH_ITEMSOLICITADOAJUSTE: 'itemSolicitadoAjuste/FETCH_ITEMSOLICITADOAJUSTE',
  CREATE_ITEMSOLICITADOAJUSTE: 'itemSolicitadoAjuste/CREATE_ITEMSOLICITADOAJUSTE',
  UPDATE_ITEMSOLICITADOAJUSTE: 'itemSolicitadoAjuste/UPDATE_ITEMSOLICITADOAJUSTE',
  DELETE_ITEMSOLICITADOAJUSTE: 'itemSolicitadoAjuste/DELETE_ITEMSOLICITADOAJUSTE',
  RESET: 'itemSolicitadoAjuste/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IItemSolicitadoAjuste>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ItemSolicitadoAjusteState = Readonly<typeof initialState>;

// Reducer

export default (state: ItemSolicitadoAjusteState = initialState, action): ItemSolicitadoAjusteState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ITEMSOLICITADOAJUSTE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ITEMSOLICITADOAJUSTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ITEMSOLICITADOAJUSTE):
    case REQUEST(ACTION_TYPES.UPDATE_ITEMSOLICITADOAJUSTE):
    case REQUEST(ACTION_TYPES.DELETE_ITEMSOLICITADOAJUSTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ITEMSOLICITADOAJUSTE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ITEMSOLICITADOAJUSTE):
    case FAILURE(ACTION_TYPES.CREATE_ITEMSOLICITADOAJUSTE):
    case FAILURE(ACTION_TYPES.UPDATE_ITEMSOLICITADOAJUSTE):
    case FAILURE(ACTION_TYPES.DELETE_ITEMSOLICITADOAJUSTE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ITEMSOLICITADOAJUSTE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ITEMSOLICITADOAJUSTE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ITEMSOLICITADOAJUSTE):
    case SUCCESS(ACTION_TYPES.UPDATE_ITEMSOLICITADOAJUSTE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ITEMSOLICITADOAJUSTE):
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

const apiUrl = 'api/item-solicitado-ajustes';

// Actions

export const getEntities: ICrudGetAllAction<IItemSolicitadoAjuste> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ITEMSOLICITADOAJUSTE_LIST,
  payload: axios.get<IItemSolicitadoAjuste>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IItemSolicitadoAjuste> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ITEMSOLICITADOAJUSTE,
    payload: axios.get<IItemSolicitadoAjuste>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IItemSolicitadoAjuste> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ITEMSOLICITADOAJUSTE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IItemSolicitadoAjuste> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ITEMSOLICITADOAJUSTE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IItemSolicitadoAjuste> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ITEMSOLICITADOAJUSTE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
