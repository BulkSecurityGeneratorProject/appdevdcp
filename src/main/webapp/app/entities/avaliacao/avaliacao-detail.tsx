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
  Progress
} from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import classnames from 'classnames';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './avaliacao.reducer';
import { IAvaliacao } from 'app/shared/model/avaliacao.model';
import { TipoItemAuditado } from 'app/shared/model/item-auditado.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAvaliacaoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAvaliacaoDetailState {
  activeTab: string;
  rowSpanForItensAuditados: {};
}
export class AvaliacaoDetail extends React.Component<IAvaliacaoDetailProps, IAvaliacaoDetailState> {
  state: IAvaliacaoDetailState = {
    activeTab: 'CHECKLIST',
    rowSpanForItensAuditados: {}
  };

  includeRowspanForItensAuditados(itensAuditados) {
    const rowSpansForTipos: { [key in TipoItemAuditado]?: number } = {
      [TipoItemAuditado.TOP_5_PERDAS]: itensAuditados.filter(i => i.tipo === TipoItemAuditado.TOP_5_PERDAS).length,
      [TipoItemAuditado.ALTO_RISCO]: itensAuditados.filter(i => i.tipo === TipoItemAuditado.ALTO_RISCO).length,
      [TipoItemAuditado.TROCA_CANCELAMENTO_DVC]: itensAuditados.filter(i => i.tipo === TipoItemAuditado.TROCA_CANCELAMENTO_DVC).length
    };

    // Obtém os primeiros itens de cada tipo e seta os rowspans neles
    itensAuditados
      .filter((item, index, array) => index === 0 || array[index - 1].tipo !== item.tipo)
      .forEach((item, index) => (this.state.rowSpanForItensAuditados[index] = rowSpansForTipos[item.tipo]));
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.avaliacaoEntity.itensAuditados && nextProps.avaliacaoEntity.itensAuditados.length) {
      this.includeRowspanForItensAuditados(nextProps.avaliacaoEntity.itensAuditados);
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

  render() {
    const { avaliacaoEntity } = this.props;
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
                  className={classnames({ active: this.state.activeTab === 'CHECKLIST' })}
                  onClick={() => this.toggle('CHECKLIST')} // tslint:disable-line jsx-no-lambda
                >
                  Checklist
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink
                  className={classnames({ active: this.state.activeTab === 'AUDITORIA' })}
                  onClick={() => this.toggle('AUDITORIA')} // tslint:disable-line jsx-no-lambda
                >
                  Auditoria
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink
                  className={classnames({ active: this.state.activeTab === 'SOLICITACAO_AJUSTE' })}
                  onClick={() => this.toggle('SOLICITACAO_AJUSTE')} // tslint:disable-line jsx-no-lambda
                >
                  Solicitação de Ajuste
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink
                  className={classnames({ active: this.state.activeTab === 'ETIQUETA' })}
                  onClick={() => this.toggle('ETIQUETA')} // tslint:disable-line jsx-no-lambda
                >
                  Etiqueta
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink
                  className={classnames({ active: this.state.activeTab === 'APOIO_SPP' })}
                  onClick={() => this.toggle('APOIO_SPP')} // tslint:disable-line jsx-no-lambda
                >
                  Apoio SPP
                </NavLink>
              </NavItem>
            </Nav>
            <TabContent activeTab={this.state.activeTab}>
              <TabPane tabId="CHECKLIST" className="tab-checklist">
                <Card body outline color="secondary">
                  <Row>
                    <Col xs="2">
                      <b>
                        <Translate contentKey="dcpdesconformidadesApp.avaliacao.loja">Loja</Translate>
                      </b>
                    </Col>
                    <Col xs="2">{avaliacaoEntity.lojaNome ? avaliacaoEntity.lojaNome : ''}</Col>
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
                      <TextFormat value={avaliacaoEntity.iniciadaEm} type="date" format={APP_DATE_FORMAT} />
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
                      <TextFormat value={avaliacaoEntity.submetidoEm} type="date" format={APP_DATE_FORMAT} />
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
                    <Col xs="4" />
                  </Row>
                </Card>

                <Card body outline color="secondary">
                  <div className="table-responsive">
                    <Table responsive bordered hover size="sm">
                      <thead>
                        <tr>
                          <th className="text-center">Item</th>
                          <th className="text-center">Classificação</th>
                          <th className="text-center">Descrição do item</th>
                          <th />
                          <th />
                          <th />
                          <th />
                          <th className="text-center">
                            <Translate contentKey="dcpdesconformidadesApp.avaliacao.status">Status</Translate>
                          </th>
                          <th className="text-center">Observações DCP</th>
                        </tr>
                      </thead>
                      <tbody>
                        {/* {avaliacaoList.map((avaliacao, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${avaliacao.id}`} color="link" size="sm">
                        {avaliacao.id}
                      </Button>
                    </td>
                    <td>{avaliacao.lojaNome ? <Link to={`loja/${avaliacao.lojaId}`}>{avaliacao.lojaNome}</Link> : ''}</td>
                    <td>{avaliacao.nomeResponsavelLoja}</td>
                    <td>{avaliacao.prontuarioResponsavelLoja}</td>
                    <td>{avaliacao.avaliadorName ? avaliacao.avaliadorName : ''}</td>
                    <td>
                      <Translate contentKey={`dcpdesconformidadesApp.StatusAvaliacao.${avaliacao.status}`} />
                    </td>
                    <td>
                      <TextFormat type="date" value={avaliacao.iniciadaEm} format={APP_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={avaliacao.submetidoEm} format={APP_DATE_FORMAT} />
                    </td>
                    <td>
                      <Translate contentKey={`dcpdesconformidadesApp.CriticidadePainel.${avaliacao.criticidadePainel}`} />
                    </td>
                    <td>
                      <Translate contentKey={`dcpdesconformidadesApp.NivelEficiencia.${avaliacao.nivelEficienciaGeral}`} />
                    </td>
                    <td>{avaliacao.importadoViaPlanilha ? 'true' : 'false'}</td>
                    <td>{avaliacao.caminhoArquivoPlanilha}</td>
                    <td>
                      {avaliacao.questionarioNome ? (
                        <Link to={`questionario/${avaliacao.questionarioId}`}>{avaliacao.questionarioNome}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${avaliacao.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${avaliacao.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.cancel">Cancel</Translate>
                          </span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))} */}
                      </tbody>
                    </Table>
                  </div>
                </Card>
              </TabPane>
              <TabPane tabId="AUDITORIA" className="tab-auditoria">
                <Card body outline color="secondary">
                  <div className="table-responsive">
                    <Table responsive bordered hover size="sm">
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
                        {avaliacaoEntity.itensAuditados &&
                          avaliacaoEntity.itensAuditados.map((itemAuditado, i) => (
                            <tr key={`entity-${i}`}>
                              {this.state.rowSpanForItensAuditados[i] && (
                                <td rowSpan={this.state.rowSpanForItensAuditados[i]}>
                                  <Translate contentKey={`dcpdesconformidadesApp.TipoItemAuditado.${itemAuditado.tipo}`} />
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
                </Card>
              </TabPane>
              <TabPane tabId="SOLICITACAO_AJUSTE" className="tab-solicitacao-ajuste">
                <Card body outline color="secondary">
                  <div className="table-responsive">
                    <Table responsive bordered hover size="sm">
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
                        {avaliacaoEntity.itensComAjusteSolicitados &&
                          avaliacaoEntity.itensComAjusteSolicitados.map((itemComAjusteSolicitado, i) => (
                            <tr key={`entity-${i}`}>
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
                </Card>
              </TabPane>
              <TabPane tabId="ETIQUETA" className="tab-etiqueta">
                <Row>
                  {/* <Col>
                <Row>
                  <Col>
                    <Row>
                      <Col>
                        <b><Translate contentKey="dcpdesconformidadesApp.avaliacao.loja">Loja</Translate></b>
                      </Col>
                      <Col>
                        {avaliacaoEntity.lojaNome ? avaliacaoEntity.lojaNome : ''}
                      </Col>
                    </Row>
                    <Row>
                      <Col>
                        <b><Translate contentKey="dcpdesconformidadesApp.avaliacao.criticidadePainel">Criticidade Painel</Translate></b>
                      </Col>
                      <Col>
                        <Translate contentKey={`dcpdesconformidadesApp.CriticidadePainel.${avaliacaoEntity.criticidadePainel}`} />
                      </Col>
                    </Row>
                  </Col>

                  <Col>
                    <Row>
                      <Col>
                        <b><Translate contentKey="dcpdesconformidadesApp.avaliacao.loja">Loja</Translate></b>
                      </Col>
                      <Col>
                        {avaliacaoEntity.lojaNome ? avaliacaoEntity.lojaNome : ''}
                      </Col>
                    </Row>
                    <Row>
                      <Col>
                        <b><Translate contentKey="dcpdesconformidadesApp.avaliacao.criticidadePainel">Criticidade Painel</Translate></b>
                      </Col>
                      <Col>
                        <Translate contentKey={`dcpdesconformidadesApp.CriticidadePainel.${avaliacaoEntity.criticidadePainel}`} />
                      </Col>
                    </Row>
                  </Col>
                </Row>
              </Col> */}
                  <Col>
                    <NivelEficienciaCard
                      etiqueta={avaliacaoEntity.nivelEficienciaGeral}
                      title="Nível de Eficiência Loja"
                      text={translate('dcpdesconformidadesApp.avaliacao.nivelEficienciaGeralObservacao')}
                    />
                  </Col>
                </Row>
                <Row>
                  <Col>
                    <CardDeck>
                      <NivelEficienciaCard
                        etiqueta={avaliacaoEntity.nivelEficienciaProcedimento}
                        title="Procedimentos"
                        text={translate('dcpdesconformidadesApp.avaliacao.nivelEficienciaProcedimentoObservacao')}
                      />
                      <NivelEficienciaCard
                        etiqueta={avaliacaoEntity.nivelEficienciaProcesso}
                        title="Processos"
                        text={translate('dcpdesconformidadesApp.avaliacao.nivelEficienciaProcessoObservacao')}
                      />
                      <NivelEficienciaCard
                        etiqueta={avaliacaoEntity.nivelEficienciaProduto}
                        title="Produtos"
                        text={translate('dcpdesconformidadesApp.avaliacao.nivelEficienciaProdutoObservacao')}
                      />
                      <NivelEficienciaCard
                        etiqueta={avaliacaoEntity.nivelEficienciaPessoa}
                        title="Pessoas"
                        text={translate('dcpdesconformidadesApp.avaliacao.nivelEficienciaPessoaObservacao')}
                      />
                    </CardDeck>
                  </Col>
                </Row>
              </TabPane>
              <TabPane tabId="APOIO_SPP" className="tab-apoio-spp">
                <Row>
                  <Col sm="12">
                    <h4>APOIO_SPP</h4>
                  </Col>
                </Row>
              </TabPane>
            </TabContent>
          </div>
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
              <Translate contentKey="dcpdesconformidadesApp.avaliacao.nomeAvaliador">Avaliador</Translate>
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

const NivelEficienciaCard = props => (
  <Card body className="nivel-eficiencia-card">
    <CardTitle className="text-center">{props.title}</CardTitle>
    <Row>
      <Col xs="8">
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
