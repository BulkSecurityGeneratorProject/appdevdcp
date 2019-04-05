import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, IPayload, IPayloadResult } from 'react-jhipster';

import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAvaliacao, defaultValue, IPontosPorGrupo } from 'app/shared/model/avaliacao.model';

export const ACTION_TYPES = {
  FETCH_AVALIACAO_LIST: 'avaliacao/FETCH_AVALIACAO_LIST',
  FETCH_AVALIACAO: 'avaliacao/FETCH_AVALIACAO',
  CANCEL_AVALIACAO: 'avaliacao/CANCEL_AVALIACAO',
  RESET: 'avaliacao/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAvaliacao>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type AvaliacaoState = Readonly<typeof initialState>;

declare type ICrudDeleteAction<T> = (id?: string | number, motivo?: string) => IPayload<T> | IPayloadResult<T>;

// Reducer

export default (state: AvaliacaoState = initialState, action): AvaliacaoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_AVALIACAO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_AVALIACAO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CANCEL_AVALIACAO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_AVALIACAO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_AVALIACAO):
    case FAILURE(ACTION_TYPES.CANCEL_AVALIACAO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_AVALIACAO_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_AVALIACAO):
      const data = action.payload.data;
      includePontosTotaisParaGrupos(data);
      return {
        ...state,
        loading: false,
        entity: data
      };
    case SUCCESS(ACTION_TYPES.CANCEL_AVALIACAO):
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

const apiUrl = 'api/avaliacoes';

// Actions

const includePontosTotaisParaGrupos: ((avaliacao?: IAvaliacao) => void) = avaliacao => {
  avaliacao.pontosPorGrupos = {};

  avaliacao.questionario.grupos.forEach(grupo => {
    const itensAvaliacaoIds = grupo.itens.map(item => item.id);

    const pontos = {
      pontosProcedimento: 0,
      pontosPessoa: 0,
      pontosProcesso: 0,
      pontosProduto: 0,
      pontosObtidosProcedimento: 0,
      pontosObtidosPessoa: 0,
      pontosObtidosProcesso: 0,
      pontosObtidosProduto: 0,
      totalPontos: 0,
      totalPontosObtidos: 0,
      obtencao: 0
    };

    avaliacao.itensAvaliados.forEach(item => {
      if (itensAvaliacaoIds.includes(item.itemAvaliacaoId)) {
        pontos.pontosProcedimento += item.pontosProcedimento;
        pontos.pontosPessoa += item.pontosPessoa;
        pontos.pontosProcesso += item.pontosProcesso;
        pontos.pontosProduto += item.pontosProduto;
        pontos.pontosObtidosProcedimento += item.pontosObtidosProcedimento;
        pontos.pontosObtidosPessoa += item.pontosObtidosPessoa;
        pontos.pontosObtidosProcesso += item.pontosObtidosProcesso;
        pontos.pontosObtidosProduto += item.pontosObtidosProduto;
      }
    });

    pontos.totalPontos = pontos.pontosProcedimento + pontos.pontosPessoa + pontos.pontosProcesso + pontos.pontosProduto;
    pontos.totalPontosObtidos =
      pontos.pontosObtidosProcedimento + pontos.pontosObtidosPessoa + pontos.pontosObtidosProcesso + pontos.pontosObtidosProduto;
    pontos.obtencao = pontos.totalPontosObtidos / pontos.totalPontos;

    avaliacao.pontosPorGrupos[grupo.id] = pontos;
  });
};

export const getEntities: ICrudGetAllAction<IAvaliacao> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_AVALIACAO_LIST,
    payload: axios.get<IAvaliacao>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IAvaliacao> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_AVALIACAO,
    payload: axios.get<IAvaliacao>(requestUrl)
  };
};

export const deleteEntity: ICrudDeleteAction<IAvaliacao> = (id, motivo) => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.CANCEL_AVALIACAO,
    payload: axios.post(requestUrl, { motivoCancelamento: motivo })
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
