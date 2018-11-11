import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IItemAvaliadoPerdaQuebraAcumulados, defaultValue } from 'app/shared/model/item-avaliado-perda-quebra-acumulados.model';

export const ACTION_TYPES = {
  FETCH_ITEMAVALIADOPERDAQUEBRAACUMULADOS_LIST: 'itemAvaliadoPerdaQuebraAcumulados/FETCH_ITEMAVALIADOPERDAQUEBRAACUMULADOS_LIST',
  FETCH_ITEMAVALIADOPERDAQUEBRAACUMULADOS: 'itemAvaliadoPerdaQuebraAcumulados/FETCH_ITEMAVALIADOPERDAQUEBRAACUMULADOS',
  CREATE_ITEMAVALIADOPERDAQUEBRAACUMULADOS: 'itemAvaliadoPerdaQuebraAcumulados/CREATE_ITEMAVALIADOPERDAQUEBRAACUMULADOS',
  UPDATE_ITEMAVALIADOPERDAQUEBRAACUMULADOS: 'itemAvaliadoPerdaQuebraAcumulados/UPDATE_ITEMAVALIADOPERDAQUEBRAACUMULADOS',
  DELETE_ITEMAVALIADOPERDAQUEBRAACUMULADOS: 'itemAvaliadoPerdaQuebraAcumulados/DELETE_ITEMAVALIADOPERDAQUEBRAACUMULADOS',
  RESET: 'itemAvaliadoPerdaQuebraAcumulados/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IItemAvaliadoPerdaQuebraAcumulados>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ItemAvaliadoPerdaQuebraAcumuladosState = Readonly<typeof initialState>;

// Reducer

export default (state: ItemAvaliadoPerdaQuebraAcumuladosState = initialState, action): ItemAvaliadoPerdaQuebraAcumuladosState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ITEMAVALIADOPERDAQUEBRAACUMULADOS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ITEMAVALIADOPERDAQUEBRAACUMULADOS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ITEMAVALIADOPERDAQUEBRAACUMULADOS):
    case REQUEST(ACTION_TYPES.UPDATE_ITEMAVALIADOPERDAQUEBRAACUMULADOS):
    case REQUEST(ACTION_TYPES.DELETE_ITEMAVALIADOPERDAQUEBRAACUMULADOS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ITEMAVALIADOPERDAQUEBRAACUMULADOS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ITEMAVALIADOPERDAQUEBRAACUMULADOS):
    case FAILURE(ACTION_TYPES.CREATE_ITEMAVALIADOPERDAQUEBRAACUMULADOS):
    case FAILURE(ACTION_TYPES.UPDATE_ITEMAVALIADOPERDAQUEBRAACUMULADOS):
    case FAILURE(ACTION_TYPES.DELETE_ITEMAVALIADOPERDAQUEBRAACUMULADOS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ITEMAVALIADOPERDAQUEBRAACUMULADOS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ITEMAVALIADOPERDAQUEBRAACUMULADOS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ITEMAVALIADOPERDAQUEBRAACUMULADOS):
    case SUCCESS(ACTION_TYPES.UPDATE_ITEMAVALIADOPERDAQUEBRAACUMULADOS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ITEMAVALIADOPERDAQUEBRAACUMULADOS):
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

const apiUrl = 'api/item-avaliado-perda-quebra-acumulados';

// Actions

export const getEntities: ICrudGetAllAction<IItemAvaliadoPerdaQuebraAcumulados> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ITEMAVALIADOPERDAQUEBRAACUMULADOS_LIST,
  payload: axios.get<IItemAvaliadoPerdaQuebraAcumulados>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IItemAvaliadoPerdaQuebraAcumulados> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ITEMAVALIADOPERDAQUEBRAACUMULADOS,
    payload: axios.get<IItemAvaliadoPerdaQuebraAcumulados>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IItemAvaliadoPerdaQuebraAcumulados> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ITEMAVALIADOPERDAQUEBRAACUMULADOS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IItemAvaliadoPerdaQuebraAcumulados> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ITEMAVALIADOPERDAQUEBRAACUMULADOS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IItemAvaliadoPerdaQuebraAcumulados> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ITEMAVALIADOPERDAQUEBRAACUMULADOS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
