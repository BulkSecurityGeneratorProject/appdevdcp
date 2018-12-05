import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPerdaQuebraAcumuladosAnoLoja, defaultValue } from 'app/shared/model/perda-quebra-acumulados-ano-loja.model';

export const ACTION_TYPES = {
  FETCH_PERDAQUEBRAACUMULADOSANOLOJA_LIST: 'perdaQuebraAcumuladosAnoLoja/FETCH_PERDAQUEBRAACUMULADOSANOLOJA_LIST',
  FETCH_PERDAQUEBRAACUMULADOSANOLOJA: 'perdaQuebraAcumuladosAnoLoja/FETCH_PERDAQUEBRAACUMULADOSANOLOJA',
  CREATE_PERDAQUEBRAACUMULADOSANOLOJA: 'perdaQuebraAcumuladosAnoLoja/CREATE_PERDAQUEBRAACUMULADOSANOLOJA',
  UPDATE_PERDAQUEBRAACUMULADOSANOLOJA: 'perdaQuebraAcumuladosAnoLoja/UPDATE_PERDAQUEBRAACUMULADOSANOLOJA',
  DELETE_PERDAQUEBRAACUMULADOSANOLOJA: 'perdaQuebraAcumuladosAnoLoja/DELETE_PERDAQUEBRAACUMULADOSANOLOJA',
  RESET: 'perdaQuebraAcumuladosAnoLoja/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPerdaQuebraAcumuladosAnoLoja>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PerdaQuebraAcumuladosAnoLojaState = Readonly<typeof initialState>;

// Reducer

export default (state: PerdaQuebraAcumuladosAnoLojaState = initialState, action): PerdaQuebraAcumuladosAnoLojaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PERDAQUEBRAACUMULADOSANOLOJA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PERDAQUEBRAACUMULADOSANOLOJA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PERDAQUEBRAACUMULADOSANOLOJA):
    case REQUEST(ACTION_TYPES.UPDATE_PERDAQUEBRAACUMULADOSANOLOJA):
    case REQUEST(ACTION_TYPES.DELETE_PERDAQUEBRAACUMULADOSANOLOJA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PERDAQUEBRAACUMULADOSANOLOJA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PERDAQUEBRAACUMULADOSANOLOJA):
    case FAILURE(ACTION_TYPES.CREATE_PERDAQUEBRAACUMULADOSANOLOJA):
    case FAILURE(ACTION_TYPES.UPDATE_PERDAQUEBRAACUMULADOSANOLOJA):
    case FAILURE(ACTION_TYPES.DELETE_PERDAQUEBRAACUMULADOSANOLOJA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PERDAQUEBRAACUMULADOSANOLOJA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PERDAQUEBRAACUMULADOSANOLOJA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PERDAQUEBRAACUMULADOSANOLOJA):
    case SUCCESS(ACTION_TYPES.UPDATE_PERDAQUEBRAACUMULADOSANOLOJA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PERDAQUEBRAACUMULADOSANOLOJA):
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

const apiUrl = 'api/perda-quebra-acumulados-ano-lojas';

// Actions

export const getEntities: ICrudGetAllAction<IPerdaQuebraAcumuladosAnoLoja> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PERDAQUEBRAACUMULADOSANOLOJA_LIST,
  payload: axios.get<IPerdaQuebraAcumuladosAnoLoja>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPerdaQuebraAcumuladosAnoLoja> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PERDAQUEBRAACUMULADOSANOLOJA,
    payload: axios.get<IPerdaQuebraAcumuladosAnoLoja>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPerdaQuebraAcumuladosAnoLoja> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PERDAQUEBRAACUMULADOSANOLOJA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPerdaQuebraAcumuladosAnoLoja> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PERDAQUEBRAACUMULADOSANOLOJA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPerdaQuebraAcumuladosAnoLoja> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PERDAQUEBRAACUMULADOSANOLOJA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
