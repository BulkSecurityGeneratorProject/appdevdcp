import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import {
  Button,
  Row,
  Col,
  TabContent,
  TabPane,
  Nav,
  NavItem,
  NavLink,
  Card,
  CardText,
  Table,
  CardTitle,
  CardDeck,
  CardGroup,
  Progress,
  Alert
} from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import classnames from 'classnames';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './avaliacao.reducer';
import { IAvaliacao, IPontosPorGrupo } from 'app/shared/model/avaliacao.model';
import { TipoItemAuditado } from 'app/shared/model/item-auditado.model';
// tslint:disable-next-line:no-unused-variable
import {
  APP_DATE_FORMAT,
  APP_LOCAL_DATE_FORMAT,
  APP_DATE_EXTENSO_FORMAT,
  APP_PERCENTAGE_FORMAT,
  APP_CURRENCY_FORMAT
} from 'app/config/constants';
import { IItemAvaliado } from 'app/shared/model/item-avaliado.model';
import { GoogleMapsLink } from 'app/shared/util/google-maps-link';
import { IGrupoItens } from 'app/shared/model/grupo-itens.model';
import { IItemAvaliacao } from 'app/shared/model/item-avaliacao.model';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';

export interface IAvaliacaoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAvaliacaoDetailState {
  activeTab: string;
  rowSpanForItensAuditados: {};
  pontosValidosEObtidosPorTipo: any[];
}
export class AvaliacaoDetail extends React.Component<IAvaliacaoDetailProps, IAvaliacaoDetailState> {
  state: IAvaliacaoDetailState = {
    activeTab: 'CHECKLIST',
    rowSpanForItensAuditados: {},
    pontosValidosEObtidosPorTipo: [] as any[]
  };

  // Obtém os primeiros itens de cada tipo e define o rowspan deles de acordo com a quantidade de itens para o tipo
  // Pressupõe que a lista já está ordenada por tipo
  includeRowspanForItensAuditados(itensAuditados) {
    const rowSpansForTipos: { [key in TipoItemAuditado]?: number } = {
      [TipoItemAuditado.TOP_5_PERDAS]: itensAuditados.filter(i => i.tipo === TipoItemAuditado.TOP_5_PERDAS).length,
      [TipoItemAuditado.ALTO_RISCO]: itensAuditados.filter(i => i.tipo === TipoItemAuditado.ALTO_RISCO).length,
      [TipoItemAuditado.TROCA_CANCELAMENTO_DVC]: itensAuditados.filter(i => i.tipo === TipoItemAuditado.TROCA_CANCELAMENTO_DVC).length
    };

    itensAuditados
      .filter((item, index, array) => index === 0 || array[index - 1].tipo !== item.tipo)
      .forEach((item, index) => (this.state.rowSpanForItensAuditados[item.id] = rowSpansForTipos[item.tipo]));
  }

  getPontosValidosEObtidosPorTipo(pontosPorGrupos: IPontosPorGrupo[], pontuacaoPerdaEQuebra: number) {
    const data = [];

    const pontos = {
      pontosProcedimento: 0,
      pontosPessoa: 0,
      pontosProcesso: 0,
      pontosProduto: 0,
      pontosObtidosProcedimento: 0,
      pontosObtidosPessoa: 0,
      pontosObtidosProcesso: 0,
      pontosObtidosProduto: 0
    };

    Object.keys(pontosPorGrupos).forEach(grupoId => {
      const pontosPorGrupo = pontosPorGrupos[grupoId];
      pontos.pontosProcedimento += pontosPorGrupo.pontosProcedimento;
      pontos.pontosPessoa += pontosPorGrupo.pontosPessoa;
      pontos.pontosProcesso += pontosPorGrupo.pontosProcesso;
      pontos.pontosProduto += pontosPorGrupo.pontosProduto;
      pontos.pontosObtidosProcedimento += pontosPorGrupo.pontosObtidosProcedimento;
      pontos.pontosObtidosPessoa += pontosPorGrupo.pontosObtidosPessoa;
      pontos.pontosObtidosProcesso += pontosPorGrupo.pontosObtidosProcesso;
      pontos.pontosObtidosProduto += pontosPorGrupo.pontosObtidosProduto;
    });

    data.push(
      {
        tipo: translate('dcpdesconformidadesApp.avaliacao.procedimentos'),
        validos: pontos.pontosProcedimento,
        obtidos: pontos.pontosObtidosProcedimento + pontuacaoPerdaEQuebra
      },
      {
        tipo: translate('dcpdesconformidadesApp.avaliacao.pessoas'),
        validos: pontos.pontosPessoa,
        obtidos: pontos.pontosObtidosPessoa + pontuacaoPerdaEQuebra
      },
      {
        tipo: translate('dcpdesconformidadesApp.avaliacao.processos'),
        validos: pontos.pontosProcesso,
        obtidos: pontos.pontosObtidosProcesso + pontuacaoPerdaEQuebra
      },
      {
        tipo: translate('dcpdesconformidadesApp.avaliacao.produtos'),
        validos: pontos.pontosProduto,
        obtidos: pontos.pontosObtidosProduto + pontuacaoPerdaEQuebra
      }
    );

    return data;
  }

  getItemAvaliadoParaItemAvaliacaoId(itemAvaliacaoId: number): IItemAvaliado {
    return this.props.avaliacaoEntity.itensAvaliados.find(item => item.itemAvaliacaoId === itemAvaliacaoId);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.avaliacaoEntity.itensAuditados && nextProps.avaliacaoEntity.itensAuditados.length) {
      this.includeRowspanForItensAuditados(nextProps.avaliacaoEntity.itensAuditados);
    }
    if (nextProps.avaliacaoEntity.pontosPorGrupos) {
      this.state.pontosValidosEObtidosPorTipo = this.getPontosValidosEObtidosPorTipo(
        nextProps.avaliacaoEntity.pontosPorGrupos,
        nextProps.avaliacaoEntity.pontuacaoPerda + nextProps.avaliacaoEntity.pontuacaoQuebra
      );
    }
  }

  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  toggle(tab) {
    if (this.state.activeTab !== tab) {
      this.setState({
        activeTab: tab
      });
    }
  }

  PerdaQuebraRows = ({ avaliacaoEntity }) => (
    <>
      <tr>
        <td className="text-center">
          <b>{avaliacaoEntity.questionario.grupos.length + 1}</b>
        </td>
        <td colSpan={2} className="text-center table-header">
          <b>
            <Translate contentKey="dcpdesconformidadesApp.avaliacao.ultimoAcumuladoPerdaEQuebra">
              Perda e Quebra (último acumulado)
            </Translate>
          </b>
        </td>
        <td className="text-center table-header">
          <b>
            <Translate contentKey="dcpdesconformidadesApp.avaliacao.percentualPerdaEQuebra">%</Translate>
          </b>
        </td>
        <td className="text-center table-header">
          <b>
            <Translate contentKey="dcpdesconformidadesApp.avaliacao.financeiroPerdaEQuebra">Financeiro R$</Translate>
          </b>
        </td>
        <td className="text-center table-header">
          <b>
            <Translate contentKey="dcpdesconformidadesApp.avaliacao.categorizacaoPerdaEQuebra">Categorização</Translate>
          </b>
        </td>
        <td className="text-center table-header">
          <b>
            <Translate contentKey="dcpdesconformidadesApp.avaliacao.pontuacaoPerdaEQuebra">Pontuação</Translate>
          </b>
        </td>
        <th className="table-header" />
        <th className="table-header" />
        <th className="table-header" />
      </tr>
      <tr>
        <td className="text-center">1</td>
        <td className={`text-center status-item-avaliado-${avaliacaoEntity.statusPerda}`}>
          <Translate contentKey={`dcpdesconformidadesApp.StatusItemAvaliado.${avaliacaoEntity.statusPerda}`} />
        </td>
        <td>
          <Translate contentKey="dcpdesconformidadesApp.avaliacao.descricaoPerda">Valores de perda do último acumulado do ano</Translate>
        </td>
        <td className="text-center">
          <TextFormat value={avaliacaoEntity.percentualPerda} type="number" format={APP_PERCENTAGE_FORMAT} />
        </td>
        <td className="text-center">
          <TextFormat value={avaliacaoEntity.financeiroPerda} type="number" format={APP_CURRENCY_FORMAT} />
        </td>
        <td className={`text-center categorizacao-perda-quebra-${avaliacaoEntity.categorizacaoPerda}`}>
          <Translate contentKey={`dcpdesconformidadesApp.CategorizacaoPerdaQuebra.${avaliacaoEntity.categorizacaoPerda}`} />
        </td>
        <td className="text-center">{avaliacaoEntity.pontuacaoPerda}</td>
        <th />
        <th />
        <th />
      </tr>
      <tr>
        <td className="text-center">2</td>
        <td className={`text-center status-item-avaliado-${avaliacaoEntity.statusQuebra}`}>
          <Translate contentKey={`dcpdesconformidadesApp.StatusItemAvaliado.${avaliacaoEntity.statusQuebra}`} />
        </td>
        <td>
          <Translate contentKey="dcpdesconformidadesApp.avaliacao.descricaoQuebra">Valores de quebra do último acumulado do ano</Translate>
        </td>
        <td className="text-center">
          <TextFormat value={avaliacaoEntity.percentualQuebra} type="number" format={APP_PERCENTAGE_FORMAT} />
        </td>
        <td className="text-center">
          <TextFormat value={avaliacaoEntity.financeiroQuebra} type="number" format={APP_CURRENCY_FORMAT} />
        </td>
        <td className={`text-center categorizacao-perda-quebra-${avaliacaoEntity.categorizacaoQuebra}`}>
          <Translate contentKey={`dcpdesconformidadesApp.CategorizacaoPerdaQuebra.${avaliacaoEntity.categorizacaoQuebra}`} />
        </td>
        <td className="text-center">{avaliacaoEntity.pontuacaoQuebra}</td>
        <th />
        <th />
        <th />
      </tr>
    </>
  );

  GrupoPontosSum = ({ grupo }) => {
    const pontos = this.props.avaliacaoEntity.pontosPorGrupos[grupo.id];
    return (
      <>
        <tr className="grupo-pontos-sum-table-row">
          <td colSpan={3}>
            {' '}
            <b>
              <Translate contentKey="dcpdesconformidadesApp.avaliacao.pontosValidos">Pontos válidos</Translate> ({pontos.totalPontos})
            </b>{' '}
          </td>
          <td className="text-center">{pontos.pontosProcedimento}</td>
          <td className="text-center">{pontos.pontosPessoa}</td>
          <td className="text-center">{pontos.pontosProcesso}</td>
          <td className="text-center">{pontos.pontosProduto}</td>
          <th />
          <th />
          <th />
        </tr>
        <tr className="grupo-pontos-sum-table-row">
          <td colSpan={3}>
            {' '}
            <b>
              <Translate contentKey="dcpdesconformidadesApp.avaliacao.pontosObtidos">Pontos obtidos</Translate> ({pontos.totalPontosObtidos}
              )
            </b>{' '}
          </td>
          <td className="text-center">{pontos.pontosObtidosProcedimento}</td>
          <td className="text-center">{pontos.pontosObtidosPessoa}</td>
          <td className="text-center">{pontos.pontosObtidosProcesso}</td>
          <td className="text-center">{pontos.pontosObtidosProduto}</td>
          <th />
          <th />
          <th />
        </tr>
      </>
    );
  };

  GrupoItensRow = ({ grupo }) =>
    grupo.itens.map((itemAvaliacao, j) => {
      const itemAvaliado = this.getItemAvaliadoParaItemAvaliacaoId(itemAvaliacao.id);
      return (
        <tr key={`item-avaliado-${j}`}>
          <td className="text-center">{j + 1}</td>
          <td className={`text-center status-item-avaliado-${itemAvaliado.status}`}>
            <Translate contentKey={`dcpdesconformidadesApp.StatusItemAvaliado.${itemAvaliado.status}`} />
          </td>
          <td>{itemAvaliacao.descricao}</td>
          <td className="text-center">{itemAvaliado.pontosObtidosProcedimento}</td>
          <td className="text-center">{itemAvaliado.pontosObtidosPessoa}</td>
          <td className="text-center">{itemAvaliado.pontosObtidosProcesso}</td>
          <td className="text-center">{itemAvaliado.pontosObtidosProduto}</td>
          <td className="text-center">
            <GoogleMapsLink
              lat1={itemAvaliado.latitudeLocalResposta}
              long1={itemAvaliado.longitudeLocalResposta}
              lat2={this.props.avaliacaoEntity.lojaLatitude}
              long2={this.props.avaliacaoEntity.lojaLongitude}
            />
          </td>
          <td className="text-center">{itemAvaliado.observacoes}</td>
          <td className="text-center">
            {itemAvaliado.anexos && itemAvaliado.anexos.length ? `Sim (${itemAvaliado.anexos.length})` : 'Não'}
          </td>
        </tr>
      );
    });

  CheckpointGrupoRow = ({ avaliacaoEntity }) =>
    avaliacaoEntity.questionario.grupos.map((grupo, i) => {
      const pontos = avaliacaoEntity.pontosPorGrupos[grupo.id];
      return (
        <tr key={`checkpoint-grupo-${i}`}>
          <td>
            <b>{grupo.nome}</b>
          </td>
          <td className="text-right">
            <TextFormat value={pontos.pontosObtidosProcedimento / pontos.pontosProcedimento} type="number" format={APP_PERCENTAGE_FORMAT} />
          </td>
          <td className="text-right">
            <TextFormat value={pontos.pontosObtidosPessoa / pontos.pontosPessoa} type="number" format={APP_PERCENTAGE_FORMAT} />
          </td>
          <td className="text-right">
            <TextFormat value={pontos.pontosObtidosProcesso / pontos.pontosProcesso} type="number" format={APP_PERCENTAGE_FORMAT} />
          </td>
          <td className="text-right">
            <TextFormat value={pontos.pontosObtidosProduto / pontos.pontosProduto} type="number" format={APP_PERCENTAGE_FORMAT} />
          </td>
        </tr>
      );
    });

  getPerformanceGruposBarChartData() {
    const barChartData = [];

    this.props.avaliacaoEntity.questionario.grupos.forEach(grupo => {
      const pontos = this.props.avaliacaoEntity.pontosPorGrupos[grupo.id];

      barChartData.push({
        nomeGrupo: grupo.nome,
        procedimento: (pontos.pontosObtidosProcedimento * 100) / pontos.pontosProcedimento,
        pessoa: (pontos.pontosObtidosPessoa * 100) / pontos.pontosPessoa,
        processo: (pontos.pontosObtidosProcesso * 100) / pontos.pontosProcesso,
        produto: (pontos.pontosObtidosProduto * 100) / pontos.pontosProduto
      });
    });

    return barChartData;
  }

  render() {
    const { avaliacaoEntity } = this.props;
    const { activeTab, rowSpanForItensAuditados, pontosValidosEObtidosPorTipo } = this.state;
    return (
      <Row>
        <Col>
          <h2>
            <Translate contentKey="dcpdesconformidadesApp.avaliacao.detail.title">Avaliação</Translate> [<b>{avaliacaoEntity.id}</b>]
          </h2>
          <div>
            <Nav tabs>
              <NavItem>
                <NavLink
                  className={classnames({ active: activeTab === 'CHECKLIST' })}
                  onClick={() => this.toggle('CHECKLIST')} // tslint:disable-line jsx-no-lambda
                >
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.tabs.checklist">Checklist</Translate>
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink
                  className={classnames({ active: activeTab === 'AUDITORIA' })}
                  onClick={() => this.toggle('AUDITORIA')} // tslint:disable-line jsx-no-lambda
                >
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.tabs.auditoria">Auditoria</Translate>
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink
                  className={classnames({ active: activeTab === 'SOLICITACAO_AJUSTE' })}
                  onClick={() => this.toggle('SOLICITACAO_AJUSTE')} // tslint:disable-line jsx-no-lambda
                >
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.tabs.solicitacaoAjuste">Solicitação de Ajuste</Translate>
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink
                  className={classnames({ active: activeTab === 'ETIQUETA' })}
                  onClick={() => this.toggle('ETIQUETA')} // tslint:disable-line jsx-no-lambda
                >
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.tabs.etiqueta">Etiqueta</Translate>
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink
                  className={classnames({ active: activeTab === 'APOIO_SPP' })}
                  onClick={() => this.toggle('APOIO_SPP')} // tslint:disable-line jsx-no-lambda
                >
                  <Translate contentKey="dcpdesconformidadesApp.avaliacao.tabs.apoioSPP">Apoio SPP</Translate>
                </NavLink>
              </NavItem>
            </Nav>
            <TabContent activeTab={activeTab}>
              <TabPane tabId="CHECKLIST" className="tab-checklist">
                <Card body outline>
                  <Row>
                    <Col xs="2">
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.loja">Loja</Translate>
                      </b>
                    </Col>
                    <Col xs="2">
                      {`L${avaliacaoEntity.lojaId} - ${avaliacaoEntity.lojaNome} `}(
                      <GoogleMapsLink
                        lat1={avaliacaoEntity.lojaLatitude}
                        long1={avaliacaoEntity.lojaLongitude}
                        label={translate('dcpdesconformidadesApp.avaliacao.verLocalNoMapa')}
                      />
                      )
                    </Col>
                    <Col xs="2">
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.criticidadePainel">Criticidade Painel</Translate>
                      </b>
                    </Col>
                    <Col xs="2">
                      <Translate contentKey={`dcpdesconformidadesApp.CriticidadePainel.${avaliacaoEntity.criticidadePainel}`} />
                    </Col>
                    <Col xs="2">
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.iniciadaEm">Iniciada Em</Translate>
                      </b>
                    </Col>
                    <Col xs="2">
                      <TextFormat value={avaliacaoEntity.iniciadaEm} type="date" format={APP_DATE_FORMAT} />(
                      <GoogleMapsLink
                        lat1={avaliacaoEntity.latitudeInicioAvaliacao}
                        long1={avaliacaoEntity.longitudeInicioAvaliacao}
                        lat2={avaliacaoEntity.lojaLatitude}
                        long2={avaliacaoEntity.lojaLongitude}
                        label={translate('dcpdesconformidadesApp.avaliacao.verLocalNoMapa')}
                      />
                      )
                    </Col>
                  </Row>

                  <Row>
                    <Col xs="2">
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.nomeAvaliador">Avaliador</Translate>
                      </b>
                    </Col>
                    <Col xs="2">{avaliacaoEntity.avaliadorName ? avaliacaoEntity.avaliadorName : ''}</Col>
                    <Col xs="2">
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.prontuarioAvaliador">Prontuário</Translate>
                      </b>
                    </Col>
                    <Col xs="2">{avaliacaoEntity.avaliadorProntuario ? avaliacaoEntity.avaliadorProntuario : ''}</Col>
                    <Col xs="2">
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.submetidoEm">Submetido Em</Translate>
                      </b>
                    </Col>
                    <Col xs="2">
                      <TextFormat value={avaliacaoEntity.submetidoEm} type="date" format={APP_DATE_FORMAT} />(
                      <GoogleMapsLink
                        lat1={avaliacaoEntity.latitudeSubmissaoAvaliacao}
                        long1={avaliacaoEntity.longitudeSubmissaoAvaliacao}
                        lat2={avaliacaoEntity.lojaLatitude}
                        long2={avaliacaoEntity.lojaLongitude}
                        label={translate('dcpdesconformidadesApp.avaliacao.verLocalNoMapa')}
                      />
                      )
                    </Col>
                  </Row>

                  <Row>
                    <Col xs="2">
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.nomeResponsavelLoja">Nome Responsavel Loja</Translate>
                      </b>
                    </Col>
                    <Col xs="2">{avaliacaoEntity.nomeResponsavelLoja}</Col>
                    <Col xs="2">
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.prontuarioResponsavelLoja">Prontuário</Translate>
                      </b>
                    </Col>
                    <Col xs="2">{avaliacaoEntity.prontuarioResponsavelLoja}</Col>
                    <Col xs="2">
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.observacaoSubmissaoEnviadaForaDaLoja">
                          Observacao Submissao Enviada Fora Da Loja
                        </Translate>
                      </b>
                    </Col>
                    <Col xs="2">{avaliacaoEntity.observacaoSubmissaoEnviadaForaDaLoja}</Col>
                  </Row>

                  <Row>
                    <Col xs="2">
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.status">Status</Translate>
                      </b>
                    </Col>
                    <Col xs="2">
                      <Translate contentKey={`dcpdesconformidadesApp.StatusAvaliacao.${avaliacaoEntity.status}`} />
                    </Col>
                    <Col xs="2">
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.canceladoEm">Cancelado Em</Translate>
                      </b>
                    </Col>
                    <Col xs="2">
                      {avaliacaoEntity.canceladoEm && (
                        <TextFormat value={avaliacaoEntity.canceladoEm} type="date" format={APP_DATE_FORMAT} />
                      )}
                    </Col>
                    <Col xs="2">
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.motivoCancelamento">Motivo Cancelamento</Translate>
                      </b>
                    </Col>
                    <Col xs="2">{avaliacaoEntity.motivoCancelamento}</Col>
                  </Row>

                  <Row>
                    <Col xs="2">
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.questionario">Questionário</Translate>
                      </b>
                    </Col>
                    <Col xs="2">
                      {avaliacaoEntity.questionario && avaliacaoEntity.questionario.nome ? (
                        <Link to={`/entity/questionario/${avaliacaoEntity.questionario.id}`}>{avaliacaoEntity.questionario.nome}</Link>
                      ) : (
                        ''
                      )}
                    </Col>
                    <Col xs="2">
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.importadoViaPlanilha">Importado Via Planilha</Translate>
                      </b>
                    </Col>
                    <Col xs="2">{avaliacaoEntity.importadoViaPlanilha ? 'Sim' : 'Não'}</Col>
                    <Col xs="2">
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.caminhoArquivoPlanilha">Caminho Arquivo Planilha</Translate>
                      </b>
                    </Col>
                    <Col xs="2">{avaliacaoEntity.caminhoArquivoPlanilha}</Col>
                  </Row>
                </Card>

                <Card body outline>
                  <div className="table-responsive">
                    <Table responsive bordered hover size="sm">
                      <thead>
                        <tr className="table-header">
                          <th className="text-center">
                            <b>
                              <Translate contentKey="dcpdesconformidadesApp.avaliacao.item">#</Translate>
                            </b>
                          </th>
                          <th className="text-center">
                            <b>
                              <Translate contentKey="dcpdesconformidadesApp.avaliacao.classificacaoItem">Classificação</Translate>
                            </b>
                          </th>
                          <th className="text-center" width="42%">
                            <b>
                              <Translate contentKey="dcpdesconformidadesApp.avaliacao.descricaoItem">Descrição do item</Translate>
                            </b>
                          </th>
                          <th />
                          <th />
                          <th />
                          <th />
                          <th />
                          <th />
                          <th />
                        </tr>
                      </thead>
                      <tbody>
                        {avaliacaoEntity.questionario &&
                          avaliacaoEntity.questionario.grupos.map((grupo, i) => (
                            <>
                              <tr key={`grupo-itens-${i}`}>
                                <td className="text-center">
                                  {' '}
                                  <b>{i + 1}</b>{' '}
                                </td>
                                <td colSpan={2} className="text-center table-header">
                                  <b>{grupo.nome}</b>
                                </td>
                                <td className="text-center table-header">
                                  <b>
                                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.procedimentos">Procedimentos</Translate>
                                  </b>
                                </td>
                                <td className="text-center table-header">
                                  <b>
                                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.pessoas">Pessoa</Translate>
                                  </b>
                                </td>
                                <td className="text-center table-header">
                                  <b>
                                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.processos">Processos</Translate>
                                  </b>
                                </td>
                                <td className="text-center table-header">
                                  <b>
                                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.produtos">Produtos</Translate>
                                  </b>
                                </td>
                                <td className="text-center table-header">
                                  <b>
                                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.distanciaLojaResposta">Dist. Loja</Translate>
                                  </b>
                                </td>
                                <th className="text-center table-header">
                                  <b>
                                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.observacoesItem">Observações DCP</Translate>
                                  </b>
                                </th>
                                <th className="text-center table-header">
                                  <b>Anexos</b>
                                </th>
                              </tr>

                              <this.GrupoItensRow grupo={grupo} />

                              <this.GrupoPontosSum grupo={grupo} />
                            </>
                          ))}

                        {avaliacaoEntity.questionario && <this.PerdaQuebraRows avaliacaoEntity={avaliacaoEntity} />}
                      </tbody>
                    </Table>
                  </div>
                </Card>

                <CardGroup>
                  <Card body outline>
                    <CardTitle className="text-center">
                      <Translate contentKey="dcpdesconformidadesApp.avaliacao.pontosValidosEObtidos">Pontos válidos / obtidos</Translate>
                    </CardTitle>
                    <ResponsiveContainer height={300}>
                      <BarChart data={pontosValidosEObtidosPorTipo}>
                        <CartesianGrid strokeDasharray="3 3" />
                        <XAxis dataKey="tipo" />
                        <YAxis />
                        <Tooltip />
                        <Legend />
                        <Bar dataKey="validos" name="Válidos" fill="#3F6CB0" />
                        <Bar dataKey="obtidos" name="Obtidos" fill="#8AB048" />
                      </BarChart>
                    </ResponsiveContainer>
                  </Card>
                  <Card body outline>
                    <CardTitle className="text-center">
                      <Translate contentKey="dcpdesconformidadesApp.avaliacao.pontosValidosEObtidos">Pontos válidos / obtidos</Translate>
                    </CardTitle>
                    <div className="table-responsive">
                      <Table responsive bordered hover size="sm">
                        <thead>
                          <tr>
                            <th />
                            <th className="text-center">
                              <Translate contentKey="dcpdesconformidadesApp.avaliacao.pontosValidos">Pontos válidos</Translate>
                            </th>
                            <th className="text-center">
                              <Translate contentKey="dcpdesconformidadesApp.avaliacao.pontosObtidos">Pontos obtidos</Translate>
                            </th>
                            <th className="text-center">
                              <Translate contentKey="dcpdesconformidadesApp.avaliacao.percentualObtencaoPontos">% Obtenção</Translate>
                            </th>
                          </tr>
                        </thead>
                        <tbody>
                          {pontosValidosEObtidosPorTipo.map((pontos, i) => (
                            <tr key={`pontos-validos-e-obtidos-${i}`}>
                              <td className="text-center">
                                <b>{pontos.tipo}</b>
                              </td>
                              <td className="text-center">{pontos.validos}</td>
                              <td className="text-center">{pontos.obtidos}</td>
                              <td className="text-center">
                                <TextFormat value={pontos.obtidos / pontos.validos} type="number" format={APP_PERCENTAGE_FORMAT} />
                              </td>
                            </tr>
                          ))}
                        </tbody>
                      </Table>
                    </div>
                  </Card>
                </CardGroup>

                <Card body outline>
                  <Row>
                    <Col>
                      <p>
                        Eu, {avaliacaoEntity.nomeResponsavelLoja}, prontuário {avaliacaoEntity.prontuarioResponsavelLoja}, responsável pela
                        loja {`L${avaliacaoEntity.lojaId} - ${avaliacaoEntity.lojaNome}`}, declaro que em{' '}
                        <TextFormat value={avaliacaoEntity.submetidoEm} type="date" format={APP_DATE_FORMAT} /> recebi o "Relatório de
                        Desconformidade e a etiqueta com o nível de eficiência da loja em relação a Política de Prevenção da Cia" e seus
                        respectivos anexos, emitido em visita realizada nesta data, pelo SPP {avaliacaoEntity.avaliadorName}, prontuário{' '}
                        {avaliacaoEntity.avaliadorProntuario}. Estou ciente do prazo de 15 dias, contados desta data, para regularizar as
                        ações que corrijam as desconformidades verificadas.
                      </p>
                      <p>
                        Estou ciente ainda que o DCP encaminhará por e-mail e/ou disponibilizará no portal Elasa o "Relatório de
                        Desconformidade", consignando as desconformidades verificadas. Após o prazo estipulado, será realizada uma nova
                        vistoria, para verificar as ações corretivas adotadas. Na hipótese de não terem sido sanadas as irregularidades,
                        total ou parcialmente, tenho ciência de que estarei sujeito à aplicação de penalidade disciplinar.
                      </p>
                      <p>
                        Este Relatório de Desconformidade e a Etiqueta de Eficiência do DCP têm como objetivo evidenciar aspectos da
                        Política de Prevenção de Perdas da Companhia que devem ser rigorosamente atendidos, garantindo um clima de segurança
                        e controle em loja. Todas as desconformidades relacionadas foram apresentadas na oportunidade da atual visita ao
                        responsável pela loja, sendo os prazos concedidos em decorrência da complexidade de cada medida. É considerado
                        reincidente o responsável que não tiver cumprido as medidas corretivas determinadas na última visita realizada ou
                        que gerar novas desconformidades idênticas às levantadas em visitas anteriores.
                      </p>
                    </Col>
                  </Row>

                  <Row className="align-self-center">
                    <Col>
                      <span className="text-signed">
                        {`L${avaliacaoEntity.lojaId} - ${avaliacaoEntity.lojaNome}`},{' '}
                        <TextFormat value={avaliacaoEntity.submetidoEm} type="date" format={APP_DATE_EXTENSO_FORMAT} />
                      </span>
                    </Col>
                  </Row>
                  <Row className="align-self-center" style={{ marginBottom: '1rem' }}>
                    <Col>
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.contrato.localEData">Local e Data</Translate>
                      </b>
                    </Col>
                  </Row>

                  <Row className="align-self-center">
                    <Col>
                      <span className="text-signed">{avaliacaoEntity.nomeResponsavelLoja}</span>
                    </Col>
                  </Row>
                  <Row className="align-self-center" style={{ marginBottom: '1rem' }}>
                    <Col>
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.contrato.responsavelLoja">Responsável pela loja</Translate>
                      </b>
                    </Col>
                  </Row>

                  <Row className="align-self-center">
                    <Col>
                      <span className="text-signed">{avaliacaoEntity.avaliadorName}</span>
                    </Col>
                  </Row>
                  <Row className="align-self-center">
                    <Col>
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.contrato.responsavelVistoria">
                          Responsável pela vistoria
                        </Translate>
                      </b>
                    </Col>
                  </Row>
                </Card>
              </TabPane>
              <TabPane tabId="AUDITORIA" className="tab-auditoria">
                <Card body outline>
                  {avaliacaoEntity.itensAuditados && avaliacaoEntity.itensAuditados.length ? (
                    <div className="table-responsive">
                      <Table responsive bordered hover size="sm" style={{ margin: 0 }}>
                        <thead>
                          <tr>
                            <th className="text-center">Tipo</th>
                            <th className="text-center">Dpto.</th>
                            <th className="text-center">Cód. SAP</th>
                            <th className="text-center">Item</th>
                            <th className="text-center">Saldo SAP 0001</th>
                            <th className="text-center">Saldo Físico 0001</th>
                            <th className="text-center">Diferença 0001</th>
                            <th className="text-center">Saldo SAP 9000</th>
                            <th className="text-center">Saldo Físico 9000</th>
                            <th className="text-center">Diferença 9000</th>
                            <th className="text-center">Motivo da divergência - Análise na loja</th>
                          </tr>
                        </thead>
                        <tbody>
                          {avaliacaoEntity.itensAuditados.map((itemAuditado, i) => (
                            <tr key={`item-auditado-${i}`} className={`tipo-item-auditado-${itemAuditado.tipo}`}>
                              {rowSpanForItensAuditados[itemAuditado.id] && (
                                <td rowSpan={rowSpanForItensAuditados[itemAuditado.id]} className="text-center">
                                  <b>
                                    <Translate contentKey={`dcpdesconformidadesApp.TipoItemAuditado.${itemAuditado.tipo}`} />
                                  </b>
                                </td>
                              )}
                              <td>{itemAuditado.codigoDepartamento}</td>
                              <td>{itemAuditado.codigoSap}</td>
                              <td>{itemAuditado.descricaoItem}</td>
                              <td>{itemAuditado.saldoSap0001}</td>
                              <td>{itemAuditado.saldoFisico0001}</td>
                              <td>{itemAuditado.saldoFisico0001 - itemAuditado.saldoSap0001}</td>
                              <td>{itemAuditado.saldoSap9000}</td>
                              <td>{itemAuditado.saldoFisico9000}</td>
                              <td>{itemAuditado.saldoFisico9000 - itemAuditado.saldoSap9000}</td>
                              <td>{itemAuditado.motivoDivergencia}</td>
                            </tr>
                          ))}
                        </tbody>
                      </Table>
                    </div>
                  ) : (
                    <Alert color="warning" className="text-center" style={{ margin: 0 }}>
                      Nenhum item auditado para esta avaliação.
                    </Alert>
                  )}
                </Card>
              </TabPane>
              <TabPane tabId="SOLICITACAO_AJUSTE" className="tab-solicitacao-ajuste">
                <Card body outline>
                  {avaliacaoEntity.itensComAjusteSolicitados && avaliacaoEntity.itensComAjusteSolicitados.length ? (
                    <div className="table-responsive">
                      <Table responsive bordered hover size="sm" style={{ margin: 0 }}>
                        <thead>
                          <tr>
                            <th className="text-center">Cód. SAP</th>
                            <th className="text-center">Item</th>
                            <th className="text-center">Saldo SAP 0001</th>
                            <th className="text-center">Saldo Físico 0001</th>
                            <th className="text-center">Diferença 0001</th>
                            <th className="text-center">Saldo SAP 9000</th>
                            <th className="text-center">Saldo Físico 9000</th>
                            <th className="text-center">Diferença 9000</th>
                            <th className="text-center">Motivo da diferença</th>
                            <th className="text-center">Ação Corretiva / Preventiva</th>
                            <th className="text-center">Responsável</th>
                          </tr>
                        </thead>
                        <tbody>
                          {avaliacaoEntity.itensComAjusteSolicitados.map((itemComAjusteSolicitado, i) => (
                            <tr key={`item-solicitado-ajuste-${i}`}>
                              <td>{itemComAjusteSolicitado.codigoSap}</td>
                              <td>{itemComAjusteSolicitado.descricaoItem}</td>
                              <td>{itemComAjusteSolicitado.saldoSap0001}</td>
                              <td>{itemComAjusteSolicitado.saldoFisico0001}</td>
                              <td>{itemComAjusteSolicitado.saldoFisico0001 - itemComAjusteSolicitado.saldoSap0001}</td>
                              <td>{itemComAjusteSolicitado.saldoSap9000}</td>
                              <td>{itemComAjusteSolicitado.saldoFisico9000}</td>
                              <td>{itemComAjusteSolicitado.saldoFisico9000 - itemComAjusteSolicitado.saldoSap9000}</td>
                              <td>{itemComAjusteSolicitado.motivoDivergencia}</td>
                              <td>{itemComAjusteSolicitado.acaoCorretivaOuPreventiva}</td>
                              <td>{itemComAjusteSolicitado.responsavel}</td>
                            </tr>
                          ))}
                        </tbody>
                      </Table>
                    </div>
                  ) : (
                    <Alert color="warning" className="text-center" style={{ margin: 0 }}>
                      Nenhum item com ajuste solicitado para esta avaliação.
                    </Alert>
                  )}
                </Card>
              </TabPane>
              <TabPane tabId="ETIQUETA" className="tab-etiqueta">
                <Row>
                  <Col>
                    <NivelEficienciaCard
                      etiqueta={avaliacaoEntity.nivelEficienciaGeral}
                      title={translate('dcpdesconformidadesApp.avaliacao.nivelEficienciaGeral')}
                      text={translate('dcpdesconformidadesApp.avaliacao.nivelEficienciaGeralObservacao')}
                    />
                  </Col>
                </Row>
                <Row>
                  <Col>
                    <CardDeck>
                      <NivelEficienciaCard
                        etiqueta={avaliacaoEntity.nivelEficienciaProcedimento}
                        title={translate('dcpdesconformidadesApp.avaliacao.nivelEficienciaProcedimento')}
                        text={translate('dcpdesconformidadesApp.avaliacao.nivelEficienciaProcedimentoObservacao')}
                      />
                      <NivelEficienciaCard
                        etiqueta={avaliacaoEntity.nivelEficienciaProcesso}
                        title={translate('dcpdesconformidadesApp.avaliacao.nivelEficienciaProcesso')}
                        text={translate('dcpdesconformidadesApp.avaliacao.nivelEficienciaProcessoObservacao')}
                      />
                      <NivelEficienciaCard
                        etiqueta={avaliacaoEntity.nivelEficienciaProduto}
                        title={translate('dcpdesconformidadesApp.avaliacao.nivelEficienciaProduto')}
                        text={translate('dcpdesconformidadesApp.avaliacao.nivelEficienciaProdutoObservacao')}
                      />
                      <NivelEficienciaCard
                        etiqueta={avaliacaoEntity.nivelEficienciaPessoa}
                        title={translate('dcpdesconformidadesApp.avaliacao.nivelEficienciaPessoa')}
                        text={translate('dcpdesconformidadesApp.avaliacao.nivelEficienciaPessoaObservacao')}
                      />
                    </CardDeck>
                  </Col>
                </Row>
              </TabPane>
              <TabPane tabId="APOIO_SPP" className="tab-apoio-spp">
                {avaliacaoEntity.questionario &&
                  avaliacaoEntity.questionario.grupos && (
                    <>
                      <Card body outline>
                        <CardTitle className="text-center">Nível de Eficiência - Consolidado</CardTitle>
                        <div className="table-responsive">
                          <Table responsive bordered hover size="sm">
                            <thead>
                              <tr>
                                <th className="text-center">Check points</th>
                                <th className="text-center">Procedimento</th>
                                <th className="text-center">Pessoa</th>
                                <th className="text-center">Processo</th>
                                <th className="text-center">Produto</th>
                              </tr>
                            </thead>
                            <tbody>
                              <this.CheckpointGrupoRow avaliacaoEntity={avaliacaoEntity} />
                            </tbody>
                          </Table>
                        </div>
                      </Card>
                      <Card body outline>
                        <CardTitle className="text-center">Performance</CardTitle>
                        <ResponsiveContainer width="100%" height={400}>
                          <BarChart data={this.getPerformanceGruposBarChartData()}>
                            <CartesianGrid strokeDasharray="3 3" />
                            <XAxis dataKey="nomeGrupo" />
                            <YAxis />
                            <Tooltip />
                            <Legend />
                            <Bar dataKey="procedimento" name="Procedimento" fill="#3F6CB0" />
                            <Bar dataKey="pessoa" name="Pessoa" fill="#B03B3C" />
                            <Bar dataKey="processo" name="Processo" fill="#8AB048" />
                            <Bar dataKey="produto" name="Produto" fill="#6C4D91" />
                          </BarChart>
                        </ResponsiveContainer>
                      </Card>
                    </>
                  )}
              </TabPane>
            </TabContent>
          </div>
          <Button tag={Link} to="/entity/avaliacao" replace color="info" style={{ marginTop: '15px' }}>
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const NivelEficienciaCard = props => (
  <Card body className="nivel-eficiencia-card">
    <CardTitle className="text-center">{props.title}</CardTitle>
    <Row>
      <Col xs="8">
        <div className="help-text">Mais eficiente</div>
        <Progress className="progress-etiqueta-A" value="100">
          <span>A</span>
        </Progress>
        <Progress className="progress-etiqueta-B" value="80">
          <span>B</span>
        </Progress>
        <Progress className="progress-etiqueta-C" value="60">
          <span>C</span>
        </Progress>
        <Progress className="progress-etiqueta-D" value="40">
          <span>D</span>
        </Progress>
        <Progress className="progress-etiqueta-E" value="20">
          <span>E</span>
        </Progress>
        <div className="help-text">Menos eficiente</div>
      </Col>
      <Col xs="4" className={`etiqueta-text etiqueta-text-${props.etiqueta}`}>
        {props.etiqueta}
      </Col>
    </Row>
    <CardText>{props.text}</CardText>
  </Card>
);

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
