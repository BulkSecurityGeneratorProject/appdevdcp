import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IQuestionario, defaultValue } from 'app/shared/model/questionario.model';

export const ACTION_TYPES = {
  FETCH_QUESTIONARIO_LIST: 'questionario/FETCH_QUESTIONARIO_LIST',
  FETCH_QUESTIONARIO: 'questionario/FETCH_QUESTIONARIO',
  CREATE_QUESTIONARIO: 'questionario/CREATE_QUESTIONARIO',
  UPDATE_QUESTIONARIO: 'questionario/UPDATE_QUESTIONARIO',
  DELETE_QUESTIONARIO: 'questionario/DELETE_QUESTIONARIO',
  RESET: 'questionario/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IQuestionario>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type QuestionarioState = Readonly<typeof initialState>;

// Reducer

export default (state: QuestionarioState = initialState, action): QuestionarioState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_QUESTIONARIO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_QUESTIONARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_QUESTIONARIO):
    case REQUEST(ACTION_TYPES.UPDATE_QUESTIONARIO):
    case REQUEST(ACTION_TYPES.DELETE_QUESTIONARIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_QUESTIONARIO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_QUESTIONARIO):
    case FAILURE(ACTION_TYPES.CREATE_QUESTIONARIO):
    case FAILURE(ACTION_TYPES.UPDATE_QUESTIONARIO):
    case FAILURE(ACTION_TYPES.DELETE_QUESTIONARIO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_QUESTIONARIO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_QUESTIONARIO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_QUESTIONARIO):
    case SUCCESS(ACTION_TYPES.UPDATE_QUESTIONARIO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_QUESTIONARIO):
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

const apiUrl = 'api/questionarios';

// Actions

export const getEntities: ICrudGetAllAction<IQuestionario> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_QUESTIONARIO_LIST,
  payload: axios.get<IQuestionario>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IQuestionario> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_QUESTIONARIO,
    payload: axios.get<IQuestionario>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IQuestionario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_QUESTIONARIO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IQuestionario> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_QUESTIONARIO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IQuestionario> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_QUESTIONARIO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
