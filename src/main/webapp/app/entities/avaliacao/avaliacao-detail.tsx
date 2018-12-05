import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './avaliacao.reducer';
import { IAvaliacao } from 'app/shared/model/avaliacao.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAvaliacaoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AvaliacaoDetail extends React.Component<IAvaliacaoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { avaliacaoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dcpdesconformidadesApp.avaliacao.detail.title">Avaliacao</Translate> [<b>{avaliacaoEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="iniciadaEm">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.iniciadaEm">Iniciada Em</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={avaliacaoEntity.iniciadaEm} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="latitudeInicioAvaliacao">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.latitudeInicioAvaliacao">Latitude Inicio Avaliacao</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.latitudeInicioAvaliacao}</dd>
            <dt>
              <span id="longitudeInicioAvaliacao">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.longitudeInicioAvaliacao">Longitude Inicio Avaliacao</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.longitudeInicioAvaliacao}</dd>
            <dt>
              <span id="nomeResponsavelLoja">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.nomeResponsavelLoja">Nome Responsavel Loja</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.nomeResponsavelLoja}</dd>
            <dt>
              <span id="prontuarioResponsavelLoja">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.prontuarioResponsavelLoja">Prontuario Responsavel Loja</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.prontuarioResponsavelLoja}</dd>
            <dt>
              <span id="submetidoEm">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.submetidoEm">Submetido Em</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={avaliacaoEntity.submetidoEm} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="latitudeSubmissaoAvaliacao">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.latitudeSubmissaoAvaliacao">Latitude Submissao Avaliacao</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.latitudeSubmissaoAvaliacao}</dd>
            <dt>
              <span id="longitudeSubmissaoAvaliacao">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.longitudeSubmissaoAvaliacao">
                  Longitude Submissao Avaliacao
                </Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.longitudeSubmissaoAvaliacao}</dd>
            <dt>
              <span id="observacaoSubmissaoEnviadaForaDaLoja">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.observacaoSubmissaoEnviadaForaDaLoja">
                  Observacao Submissao Enviada Fora Da Loja
                </Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.observacaoSubmissaoEnviadaForaDaLoja}</dd>
            <dt>
              <span id="status">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.status">Status</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.status}</dd>
            <dt>
              <span id="criticidadePainel">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.criticidadePainel">Criticidade Painel</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.criticidadePainel}</dd>
            <dt>
              <span id="nivelEficienciaGeral">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.nivelEficienciaGeral">Nivel Eficiencia Geral</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.nivelEficienciaGeral}</dd>
            <dt>
              <span id="nivelEficienciaProcedimento">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.nivelEficienciaProcedimento">
                  Nivel Eficiencia Procedimento
                </Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.nivelEficienciaProcedimento}</dd>
            <dt>
              <span id="nivelEficienciaPessoa">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.nivelEficienciaPessoa">Nivel Eficiencia Pessoa</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.nivelEficienciaPessoa}</dd>
            <dt>
              <span id="nivelEficienciaProcesso">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.nivelEficienciaProcesso">Nivel Eficiencia Processo</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.nivelEficienciaProcesso}</dd>
            <dt>
              <span id="nivelEficienciaProduto">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.nivelEficienciaProduto">Nivel Eficiencia Produto</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.nivelEficienciaProduto}</dd>
            <dt>
              <span id="canceladoEm">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.canceladoEm">Cancelado Em</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={avaliacaoEntity.canceladoEm} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="motivoCancelamento">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.motivoCancelamento">Motivo Cancelamento</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.motivoCancelamento}</dd>
            <dt>
              <span id="percentualPerda">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.percentualPerda">Percentual Perda</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.percentualPerda}</dd>
            <dt>
              <span id="financeiroPerda">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.financeiroPerda">Financeiro Perda</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.financeiroPerda}</dd>
            <dt>
              <span id="pontuacaoPerda">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.pontuacaoPerda">Pontuacao Perda</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.pontuacaoPerda}</dd>
            <dt>
              <span id="statusPerda">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.statusPerda">Status Perda</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.statusPerda}</dd>
            <dt>
              <span id="categorizacaoPerda">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.categorizacaoPerda">Categorizacao Perda</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.categorizacaoPerda}</dd>
            <dt>
              <span id="percentualQuebra">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.percentualQuebra">Percentual Quebra</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.percentualQuebra}</dd>
            <dt>
              <span id="financeiroQuebra">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.financeiroQuebra">Financeiro Quebra</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.financeiroQuebra}</dd>
            <dt>
              <span id="pontuacaoQuebra">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.pontuacaoQuebra">Pontuacao Quebra</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.pontuacaoQuebra}</dd>
            <dt>
              <span id="statusQuebra">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.statusQuebra">Status Quebra</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.statusQuebra}</dd>
            <dt>
              <span id="categorizacaoQuebra">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.categorizacaoQuebra">Categorizacao Quebra</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.categorizacaoQuebra}</dd>
            <dt>
              <span id="importadoViaPlanilha">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.importadoViaPlanilha">Importado Via Planilha</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.importadoViaPlanilha ? 'true' : 'false'}</dd>
            <dt>
              <span id="caminhoArquivoPlanilha">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.caminhoArquivoPlanilha">Caminho Arquivo Planilha</Translate>
              </span>
            </dt>
            <dd>{avaliacaoEntity.caminhoArquivoPlanilha}</dd>
            <dt>
              <Translate contentKey="dcpdesconformidadesApp.avaliacao.avaliador">Avaliador</Translate>
            </dt>
            <dd>{avaliacaoEntity.avaliadorName ? avaliacaoEntity.avaliadorName : ''}</dd>
            <dt>
              <Translate contentKey="dcpdesconformidadesApp.avaliacao.questionario">Questionario</Translate>
            </dt>
            <dd>{avaliacaoEntity.questionarioNome ? avaliacaoEntity.questionarioNome : ''}</dd>
            <dt>
              <Translate contentKey="dcpdesconformidadesApp.avaliacao.loja">Loja</Translate>
            </dt>
            <dd>{avaliacaoEntity.lojaNome ? avaliacaoEntity.lojaNome : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/avaliacao" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/avaliacao/${avaliacaoEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ avaliacao }: IRootState) => ({
  avaliacaoEntity: avaliacao.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AvaliacaoDetail);
