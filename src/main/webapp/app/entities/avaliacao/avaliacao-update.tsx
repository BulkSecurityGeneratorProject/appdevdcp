import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IQuestionario } from 'app/shared/model/questionario.model';
import { getEntities as getQuestionarios } from 'app/entities/questionario/questionario.reducer';
import { ILoja } from 'app/shared/model/loja.model';
import { getEntities as getLojas } from 'app/entities/loja/loja.reducer';
import { getEntity, updateEntity, createEntity, reset } from './avaliacao.reducer';
import { IAvaliacao } from 'app/shared/model/avaliacao.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAvaliacaoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAvaliacaoUpdateState {
  isNew: boolean;
  avaliadorId: string;
  questionarioId: string;
  lojaId: string;
}

export class AvaliacaoUpdate extends React.Component<IAvaliacaoUpdateProps, IAvaliacaoUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      avaliadorId: '0',
      questionarioId: '0',
      lojaId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getUsers();
    this.props.getQuestionarios();
    this.props.getLojas();
  }

  saveEntity = (event, errors, values) => {
    values.iniciadaEm = new Date(values.iniciadaEm);
    values.submetidoEm = new Date(values.submetidoEm);
    values.canceladoEm = new Date(values.canceladoEm);

    if (errors.length === 0) {
      const { avaliacaoEntity } = this.props;
      const entity = {
        ...avaliacaoEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/avaliacao');
  };

  render() {
    const { avaliacaoEntity, users, questionarios, lojas, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dcpdesconformidadesApp.avaliacao.home.createOrEditLabel">
              <Translate contentKey="dcpdesconformidadesApp.avaliacao.home.createOrEditLabel">Create or edit a Avaliacao</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p className="loading-message" />
            ) : (
              <AvForm model={isNew ? {} : avaliacaoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="avaliacao-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="iniciadaEmLabel" for="iniciadaEm">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.iniciadaEm">Iniciada Em</Translate>
                  </Label>
                  <AvInput
                    id="avaliacao-iniciadaEm"
                    type="datetime-local"
                    className="form-control"
                    name="iniciadaEm"
                    value={isNew ? null : convertDateTimeFromServer(this.props.avaliacaoEntity.iniciadaEm)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="latitudeInicioAvaliacaoLabel" for="latitudeInicioAvaliacao">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.latitudeInicioAvaliacao">Latitude Inicio Avaliacao</Translate>
                  </Label>
                  <AvField
                    id="avaliacao-latitudeInicioAvaliacao"
                    type="string"
                    className="form-control"
                    name="latitudeInicioAvaliacao"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="longitudeInicioAvaliacaoLabel" for="longitudeInicioAvaliacao">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.longitudeInicioAvaliacao">Longitude Inicio Avaliacao</Translate>
                  </Label>
                  <AvField
                    id="avaliacao-longitudeInicioAvaliacao"
                    type="string"
                    className="form-control"
                    name="longitudeInicioAvaliacao"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nomeResponsavelLojaLabel" for="nomeResponsavelLoja">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.nomeResponsavelLoja">Nome Responsavel Loja</Translate>
                  </Label>
                  <AvField id="avaliacao-nomeResponsavelLoja" type="text" name="nomeResponsavelLoja" />
                </AvGroup>
                <AvGroup>
                  <Label id="prontuarioResponsavelLojaLabel" for="prontuarioResponsavelLoja">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.prontuarioResponsavelLoja">
                      Prontuario Responsavel Loja
                    </Translate>
                  </Label>
                  <AvField
                    id="avaliacao-prontuarioResponsavelLoja"
                    type="string"
                    className="form-control"
                    name="prontuarioResponsavelLoja"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="submetidoEmLabel" for="submetidoEm">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.submetidoEm">Submetido Em</Translate>
                  </Label>
                  <AvInput
                    id="avaliacao-submetidoEm"
                    type="datetime-local"
                    className="form-control"
                    name="submetidoEm"
                    value={isNew ? null : convertDateTimeFromServer(this.props.avaliacaoEntity.submetidoEm)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="latitudeSubmissaoAvaliacaoLabel" for="latitudeSubmissaoAvaliacao">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.latitudeSubmissaoAvaliacao">
                      Latitude Submissao Avaliacao
                    </Translate>
                  </Label>
                  <AvField
                    id="avaliacao-latitudeSubmissaoAvaliacao"
                    type="string"
                    className="form-control"
                    name="latitudeSubmissaoAvaliacao"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="longitudeSubmissaoAvaliacaoLabel" for="longitudeSubmissaoAvaliacao">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.longitudeSubmissaoAvaliacao">
                      Longitude Submissao Avaliacao
                    </Translate>
                  </Label>
                  <AvField
                    id="avaliacao-longitudeSubmissaoAvaliacao"
                    type="string"
                    className="form-control"
                    name="longitudeSubmissaoAvaliacao"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="observacaoSubmissaoEnviadaForaDaLojaLabel" for="observacaoSubmissaoEnviadaForaDaLoja">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.observacaoSubmissaoEnviadaForaDaLoja">
                      Observacao Submissao Enviada Fora Da Loja
                    </Translate>
                  </Label>
                  <AvField id="avaliacao-observacaoSubmissaoEnviadaForaDaLoja" type="text" name="observacaoSubmissaoEnviadaForaDaLoja" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.status">Status</Translate>
                  </Label>
                  <AvInput
                    id="avaliacao-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && avaliacaoEntity.status) || 'INICIADA'}
                  >
                    <option value="INICIADA">
                      <Translate contentKey="dcpdesconformidadesApp.StatusAvaliacao.INICIADA" />
                    </option>
                    <option value="CHECKLIST_FINALIZADO">
                      <Translate contentKey="dcpdesconformidadesApp.StatusAvaliacao.CHECKLIST_FINALIZADO" />
                    </option>
                    <option value="ANEXO_AUDITORIA_FINALIZADO">
                      <Translate contentKey="dcpdesconformidadesApp.StatusAvaliacao.ANEXO_AUDITORIA_FINALIZADO" />
                    </option>
                    <option value="ANEXO_SOLICITACAO_AJUSTE_FINALIZADO">
                      <Translate contentKey="dcpdesconformidadesApp.StatusAvaliacao.ANEXO_SOLICITACAO_AJUSTE_FINALIZADO" />
                    </option>
                    <option value="SUBMETIDO">
                      <Translate contentKey="dcpdesconformidadesApp.StatusAvaliacao.SUBMETIDO" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="criticidadePainelLabel">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.criticidadePainel">Criticidade Painel</Translate>
                  </Label>
                  <AvInput
                    id="avaliacao-criticidadePainel"
                    type="select"
                    className="form-control"
                    name="criticidadePainel"
                    value={(!isNew && avaliacaoEntity.criticidadePainel) || 'INADMISSIVEL'}
                  >
                    <option value="INADMISSIVEL">
                      <Translate contentKey="dcpdesconformidadesApp.CriticidadePainel.INADMISSIVEL" />
                    </option>
                    <option value="CONTROLE">
                      <Translate contentKey="dcpdesconformidadesApp.CriticidadePainel.CONTROLE" />
                    </option>
                    <option value="VALOR_ELEVADO">
                      <Translate contentKey="dcpdesconformidadesApp.CriticidadePainel.VALOR_ELEVADO" />
                    </option>
                    <option value="CRITICO">
                      <Translate contentKey="dcpdesconformidadesApp.CriticidadePainel.CRITICO" />
                    </option>
                    <option value="ATENCAO">
                      <Translate contentKey="dcpdesconformidadesApp.CriticidadePainel.ATENCAO" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="nivelEficienciaGeralLabel">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.nivelEficienciaGeral">Nivel Eficiencia Geral</Translate>
                  </Label>
                  <AvInput
                    id="avaliacao-nivelEficienciaGeral"
                    type="select"
                    className="form-control"
                    name="nivelEficienciaGeral"
                    value={(!isNew && avaliacaoEntity.nivelEficienciaGeral) || 'A'}
                  >
                    <option value="A">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.A" />
                    </option>
                    <option value="B">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.B" />
                    </option>
                    <option value="C">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.C" />
                    </option>
                    <option value="D">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.D" />
                    </option>
                    <option value="E">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.E" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="nivelEficienciaProcedimentoLabel">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.nivelEficienciaProcedimento">
                      Nivel Eficiencia Procedimento
                    </Translate>
                  </Label>
                  <AvInput
                    id="avaliacao-nivelEficienciaProcedimento"
                    type="select"
                    className="form-control"
                    name="nivelEficienciaProcedimento"
                    value={(!isNew && avaliacaoEntity.nivelEficienciaProcedimento) || 'A'}
                  >
                    <option value="A">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.A" />
                    </option>
                    <option value="B">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.B" />
                    </option>
                    <option value="C">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.C" />
                    </option>
                    <option value="D">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.D" />
                    </option>
                    <option value="E">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.E" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="nivelEficienciaPessoaLabel">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.nivelEficienciaPessoa">Nivel Eficiencia Pessoa</Translate>
                  </Label>
                  <AvInput
                    id="avaliacao-nivelEficienciaPessoa"
                    type="select"
                    className="form-control"
                    name="nivelEficienciaPessoa"
                    value={(!isNew && avaliacaoEntity.nivelEficienciaPessoa) || 'A'}
                  >
                    <option value="A">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.A" />
                    </option>
                    <option value="B">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.B" />
                    </option>
                    <option value="C">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.C" />
                    </option>
                    <option value="D">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.D" />
                    </option>
                    <option value="E">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.E" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="nivelEficienciaProcessoLabel">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.nivelEficienciaProcesso">Nivel Eficiencia Processo</Translate>
                  </Label>
                  <AvInput
                    id="avaliacao-nivelEficienciaProcesso"
                    type="select"
                    className="form-control"
                    name="nivelEficienciaProcesso"
                    value={(!isNew && avaliacaoEntity.nivelEficienciaProcesso) || 'A'}
                  >
                    <option value="A">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.A" />
                    </option>
                    <option value="B">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.B" />
                    </option>
                    <option value="C">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.C" />
                    </option>
                    <option value="D">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.D" />
                    </option>
                    <option value="E">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.E" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="nivelEficienciaProdutoLabel">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.nivelEficienciaProduto">Nivel Eficiencia Produto</Translate>
                  </Label>
                  <AvInput
                    id="avaliacao-nivelEficienciaProduto"
                    type="select"
                    className="form-control"
                    name="nivelEficienciaProduto"
                    value={(!isNew && avaliacaoEntity.nivelEficienciaProduto) || 'A'}
                  >
                    <option value="A">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.A" />
                    </option>
                    <option value="B">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.B" />
                    </option>
                    <option value="C">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.C" />
                    </option>
                    <option value="D">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.D" />
                    </option>
                    <option value="E">
                      <Translate contentKey="dcpdesconformidadesApp.NivelEficiencia.E" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="canceladoEmLabel" for="canceladoEm">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.canceladoEm">Cancelado Em</Translate>
                  </Label>
                  <AvInput
                    id="avaliacao-canceladoEm"
                    type="datetime-local"
                    className="form-control"
                    name="canceladoEm"
                    value={isNew ? null : convertDateTimeFromServer(this.props.avaliacaoEntity.canceladoEm)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="motivoCancelamentoLabel" for="motivoCancelamento">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.motivoCancelamento">Motivo Cancelamento</Translate>
                  </Label>
                  <AvField id="avaliacao-motivoCancelamento" type="text" name="motivoCancelamento" />
                </AvGroup>
                <AvGroup>
                  <Label id="percentualPerdaLabel" for="percentualPerda">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.percentualPerda">Percentual Perda</Translate>
                  </Label>
                  <AvField id="avaliacao-percentualPerda" type="string" className="form-control" name="percentualPerda" />
                </AvGroup>
                <AvGroup>
                  <Label id="financeiroPerdaLabel" for="financeiroPerda">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.financeiroPerda">Financeiro Perda</Translate>
                  </Label>
                  <AvField id="avaliacao-financeiroPerda" type="text" name="financeiroPerda" />
                </AvGroup>
                <AvGroup>
                  <Label id="pontuacaoPerdaLabel" for="pontuacaoPerda">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.pontuacaoPerda">Pontuacao Perda</Translate>
                  </Label>
                  <AvField id="avaliacao-pontuacaoPerda" type="string" className="form-control" name="pontuacaoPerda" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusPerdaLabel">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.statusPerda">Status Perda</Translate>
                  </Label>
                  <AvInput
                    id="avaliacao-statusPerda"
                    type="select"
                    className="form-control"
                    name="statusPerda"
                    value={(!isNew && avaliacaoEntity.statusPerda) || 'OK'}
                  >
                    <option value="OK">
                      <Translate contentKey="dcpdesconformidadesApp.StatusItemAvaliado.OK" />
                    </option>
                    <option value="NAO_OK">
                      <Translate contentKey="dcpdesconformidadesApp.StatusItemAvaliado.NAO_OK" />
                    </option>
                    <option value="N_A">
                      <Translate contentKey="dcpdesconformidadesApp.StatusItemAvaliado.N_A" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="categorizacaoPerdaLabel">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.categorizacaoPerda">Categorizacao Perda</Translate>
                  </Label>
                  <AvInput
                    id="avaliacao-categorizacaoPerda"
                    type="select"
                    className="form-control"
                    name="categorizacaoPerda"
                    value={(!isNew && avaliacaoEntity.categorizacaoPerda) || 'INADMISSIVEL'}
                  >
                    <option value="INADMISSIVEL">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.INADMISSIVEL" />
                    </option>
                    <option value="CRITICO">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.CRITICO" />
                    </option>
                    <option value="VALOR_ELEVADO">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.VALOR_ELEVADO" />
                    </option>
                    <option value="ATENCAO">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.ATENCAO" />
                    </option>
                    <option value="CONTROLE">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.CONTROLE" />
                    </option>
                    <option value="SOBRA_DESCONTROLE">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.SOBRA_DESCONTROLE" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="percentualQuebraLabel" for="percentualQuebra">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.percentualQuebra">Percentual Quebra</Translate>
                  </Label>
                  <AvField id="avaliacao-percentualQuebra" type="string" className="form-control" name="percentualQuebra" />
                </AvGroup>
                <AvGroup>
                  <Label id="financeiroQuebraLabel" for="financeiroQuebra">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.financeiroQuebra">Financeiro Quebra</Translate>
                  </Label>
                  <AvField id="avaliacao-financeiroQuebra" type="text" name="financeiroQuebra" />
                </AvGroup>
                <AvGroup>
                  <Label id="pontuacaoQuebraLabel" for="pontuacaoQuebra">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.pontuacaoQuebra">Pontuacao Quebra</Translate>
                  </Label>
                  <AvField id="avaliacao-pontuacaoQuebra" type="string" className="form-control" name="pontuacaoQuebra" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusQuebraLabel">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.statusQuebra">Status Quebra</Translate>
                  </Label>
                  <AvInput
                    id="avaliacao-statusQuebra"
                    type="select"
                    className="form-control"
                    name="statusQuebra"
                    value={(!isNew && avaliacaoEntity.statusQuebra) || 'OK'}
                  >
                    <option value="OK">
                      <Translate contentKey="dcpdesconformidadesApp.StatusItemAvaliado.OK" />
                    </option>
                    <option value="NAO_OK">
                      <Translate contentKey="dcpdesconformidadesApp.StatusItemAvaliado.NAO_OK" />
                    </option>
                    <option value="N_A">
                      <Translate contentKey="dcpdesconformidadesApp.StatusItemAvaliado.N_A" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="categorizacaoQuebraLabel">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.categorizacaoQuebra">Categorizacao Quebra</Translate>
                  </Label>
                  <AvInput
                    id="avaliacao-categorizacaoQuebra"
                    type="select"
                    className="form-control"
                    name="categorizacaoQuebra"
                    value={(!isNew && avaliacaoEntity.categorizacaoQuebra) || 'INADMISSIVEL'}
                  >
                    <option value="INADMISSIVEL">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.INADMISSIVEL" />
                    </option>
                    <option value="CRITICO">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.CRITICO" />
                    </option>
                    <option value="VALOR_ELEVADO">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.VALOR_ELEVADO" />
                    </option>
                    <option value="ATENCAO">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.ATENCAO" />
                    </option>
                    <option value="CONTROLE">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.CONTROLE" />
                    </option>
                    <option value="SOBRA_DESCONTROLE">
                      <Translate contentKey="dcpdesconformidadesApp.CategorizacaoPerdaQuebra.SOBRA_DESCONTROLE" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup check inline>
                  <Label>
                    <AvInput id="avaliacao-importadoViaPlanilha" type="checkbox" name="importadoViaPlanilha" />
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.importadoViaPlanilha">Importado Via Planilha</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="caminhoArquivoPlanilhaLabel" for="caminhoArquivoPlanilha">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.caminhoArquivoPlanilha">Caminho Arquivo Planilha</Translate>
                  </Label>
                  <AvField id="avaliacao-caminhoArquivoPlanilha" type="text" name="caminhoArquivoPlanilha" />
                </AvGroup>
                <AvGroup>
                  <Label for="avaliador.name">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.avaliador">Avaliador</Translate>
                  </Label>
                  <AvInput id="avaliacao-avaliador" type="select" className="form-control" name="avaliadorId">
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.name}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="questionario.nome">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.questionario">Questionario</Translate>
                  </Label>
                  <AvInput id="avaliacao-questionario" type="select" className="form-control" name="questionarioId">
                    {questionarios
                      ? questionarios.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.nome}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="loja.nome">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.loja">Loja</Translate>
                  </Label>
                  <AvInput id="avaliacao-loja" type="select" className="form-control" name="lojaId">
                    {lojas
                      ? lojas.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.nome}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/avaliacao" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  users: storeState.userManagement.users,
  questionarios: storeState.questionario.entities,
  lojas: storeState.loja.entities,
  avaliacaoEntity: storeState.avaliacao.entity,
  loading: storeState.avaliacao.loading,
  updating: storeState.avaliacao.updating,
  updateSuccess: storeState.avaliacao.updateSuccess
});

const mapDispatchToProps = {
  getUsers,
  getQuestionarios,
  getLojas,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AvaliacaoUpdate);
