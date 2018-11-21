package br.com.lasa.dcpdesconformidades.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusAvaliacao;

import br.com.lasa.dcpdesconformidades.domain.enumeration.CriticidadePainel;

import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;

/**
 * A Avaliacao.
 */
@Entity
@Table(name = "avaliacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data_inicio", nullable = false)
    private Instant dataInicio;

    @NotNull
    @Column(name = "latitude_inicio_avaliacao", nullable = false)
    private Double latitudeInicioAvaliacao;

    @NotNull
    @Column(name = "longitude_inicio_avaliacao", nullable = false)
    private Double longitudeInicioAvaliacao;

    @Column(name = "nome_responsavel_loja")
    private String nomeResponsavelLoja;

    @Column(name = "prontuario_responsavel_loja")
    private Integer prontuarioResponsavelLoja;

    @Column(name = "submetido_em")
    private Instant submetidoEm;

    @Column(name = "latitude_submissao_avaliacao")
    private Double latitudeSubmissaoAvaliacao;

    @Column(name = "longitude_submissao_avaliacao")
    private Double longitudeSubmissaoAvaliacao;

    @Column(name = "observacao_submissao_enviada_fora_da_loja")
    private String observacaoSubmissaoEnviadaForaDaLoja;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusAvaliacao status;

    @Enumerated(EnumType.STRING)
    @Column(name = "criticidade_painel")
    private CriticidadePainel criticidadePainel;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_eficiencia_geral")
    private NivelEficiencia nivelEficienciaGeral;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_eficiencia_procedimento")
    private NivelEficiencia nivelEficienciaProcedimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_eficiencia_pessoa")
    private NivelEficiencia nivelEficienciaPessoa;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_eficiencia_processo")
    private NivelEficiencia nivelEficienciaProcesso;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_eficiencia_produto")
    private NivelEficiencia nivelEficienciaProduto;

    @Column(name = "cancelado_em")
    private Instant canceladoEm;

    @Column(name = "motivo_cancelamento")
    private String motivoCancelamento;

    @OneToMany(mappedBy = "avaliacao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemAvaliado> itensAvaliados = new HashSet<>();
    @OneToMany(mappedBy = "avaliacao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemAvaliadoPerdaQuebraAcumulados> itensPerdaEQuebraAcumulados = new HashSet<>();
    @OneToMany(mappedBy = "avaliacao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemAuditado> itensAuditados = new HashSet<>();
    @OneToMany(mappedBy = "avaliacao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemSolicitadoAjuste> itensComAjusteSolicitados = new HashSet<>();
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("avaliacoesRealizadas")
    private Questionario questionario;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("avaliacoes")
    private Avaliador avaliador;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDataInicio() {
        return dataInicio;
    }

    public Avaliacao dataInicio(Instant dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public void setDataInicio(Instant dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Double getLatitudeInicioAvaliacao() {
        return latitudeInicioAvaliacao;
    }

    public Avaliacao latitudeInicioAvaliacao(Double latitudeInicioAvaliacao) {
        this.latitudeInicioAvaliacao = latitudeInicioAvaliacao;
        return this;
    }

    public void setLatitudeInicioAvaliacao(Double latitudeInicioAvaliacao) {
        this.latitudeInicioAvaliacao = latitudeInicioAvaliacao;
    }

    public Double getLongitudeInicioAvaliacao() {
        return longitudeInicioAvaliacao;
    }

    public Avaliacao longitudeInicioAvaliacao(Double longitudeInicioAvaliacao) {
        this.longitudeInicioAvaliacao = longitudeInicioAvaliacao;
        return this;
    }

    public void setLongitudeInicioAvaliacao(Double longitudeInicioAvaliacao) {
        this.longitudeInicioAvaliacao = longitudeInicioAvaliacao;
    }

    public String getNomeResponsavelLoja() {
        return nomeResponsavelLoja;
    }

    public Avaliacao nomeResponsavelLoja(String nomeResponsavelLoja) {
        this.nomeResponsavelLoja = nomeResponsavelLoja;
        return this;
    }

    public void setNomeResponsavelLoja(String nomeResponsavelLoja) {
        this.nomeResponsavelLoja = nomeResponsavelLoja;
    }

    public Integer getProntuarioResponsavelLoja() {
        return prontuarioResponsavelLoja;
    }

    public Avaliacao prontuarioResponsavelLoja(Integer prontuarioResponsavelLoja) {
        this.prontuarioResponsavelLoja = prontuarioResponsavelLoja;
        return this;
    }

    public void setProntuarioResponsavelLoja(Integer prontuarioResponsavelLoja) {
        this.prontuarioResponsavelLoja = prontuarioResponsavelLoja;
    }

    public Instant getSubmetidoEm() {
        return submetidoEm;
    }

    public Avaliacao submetidoEm(Instant submetidoEm) {
        this.submetidoEm = submetidoEm;
        return this;
    }

    public void setSubmetidoEm(Instant submetidoEm) {
        this.submetidoEm = submetidoEm;
    }

    public Double getLatitudeSubmissaoAvaliacao() {
        return latitudeSubmissaoAvaliacao;
    }

    public Avaliacao latitudeSubmissaoAvaliacao(Double latitudeSubmissaoAvaliacao) {
        this.latitudeSubmissaoAvaliacao = latitudeSubmissaoAvaliacao;
        return this;
    }

    public void setLatitudeSubmissaoAvaliacao(Double latitudeSubmissaoAvaliacao) {
        this.latitudeSubmissaoAvaliacao = latitudeSubmissaoAvaliacao;
    }

    public Double getLongitudeSubmissaoAvaliacao() {
        return longitudeSubmissaoAvaliacao;
    }

    public Avaliacao longitudeSubmissaoAvaliacao(Double longitudeSubmissaoAvaliacao) {
        this.longitudeSubmissaoAvaliacao = longitudeSubmissaoAvaliacao;
        return this;
    }

    public void setLongitudeSubmissaoAvaliacao(Double longitudeSubmissaoAvaliacao) {
        this.longitudeSubmissaoAvaliacao = longitudeSubmissaoAvaliacao;
    }

    public String getObservacaoSubmissaoEnviadaForaDaLoja() {
        return observacaoSubmissaoEnviadaForaDaLoja;
    }

    public Avaliacao observacaoSubmissaoEnviadaForaDaLoja(String observacaoSubmissaoEnviadaForaDaLoja) {
        this.observacaoSubmissaoEnviadaForaDaLoja = observacaoSubmissaoEnviadaForaDaLoja;
        return this;
    }

    public void setObservacaoSubmissaoEnviadaForaDaLoja(String observacaoSubmissaoEnviadaForaDaLoja) {
        this.observacaoSubmissaoEnviadaForaDaLoja = observacaoSubmissaoEnviadaForaDaLoja;
    }

    public StatusAvaliacao getStatus() {
        return status;
    }

    public Avaliacao status(StatusAvaliacao status) {
        this.status = status;
        return this;
    }

    public void setStatus(StatusAvaliacao status) {
        this.status = status;
    }

    public CriticidadePainel getCriticidadePainel() {
        return criticidadePainel;
    }

    public Avaliacao criticidadePainel(CriticidadePainel criticidadePainel) {
        this.criticidadePainel = criticidadePainel;
        return this;
    }

    public void setCriticidadePainel(CriticidadePainel criticidadePainel) {
        this.criticidadePainel = criticidadePainel;
    }

    public NivelEficiencia getNivelEficienciaGeral() {
        return nivelEficienciaGeral;
    }

    public Avaliacao nivelEficienciaGeral(NivelEficiencia nivelEficienciaGeral) {
        this.nivelEficienciaGeral = nivelEficienciaGeral;
        return this;
    }

    public void setNivelEficienciaGeral(NivelEficiencia nivelEficienciaGeral) {
        this.nivelEficienciaGeral = nivelEficienciaGeral;
    }

    public NivelEficiencia getNivelEficienciaProcedimento() {
        return nivelEficienciaProcedimento;
    }

    public Avaliacao nivelEficienciaProcedimento(NivelEficiencia nivelEficienciaProcedimento) {
        this.nivelEficienciaProcedimento = nivelEficienciaProcedimento;
        return this;
    }

    public void setNivelEficienciaProcedimento(NivelEficiencia nivelEficienciaProcedimento) {
        this.nivelEficienciaProcedimento = nivelEficienciaProcedimento;
    }

    public NivelEficiencia getNivelEficienciaPessoa() {
        return nivelEficienciaPessoa;
    }

    public Avaliacao nivelEficienciaPessoa(NivelEficiencia nivelEficienciaPessoa) {
        this.nivelEficienciaPessoa = nivelEficienciaPessoa;
        return this;
    }

    public void setNivelEficienciaPessoa(NivelEficiencia nivelEficienciaPessoa) {
        this.nivelEficienciaPessoa = nivelEficienciaPessoa;
    }

    public NivelEficiencia getNivelEficienciaProcesso() {
        return nivelEficienciaProcesso;
    }

    public Avaliacao nivelEficienciaProcesso(NivelEficiencia nivelEficienciaProcesso) {
        this.nivelEficienciaProcesso = nivelEficienciaProcesso;
        return this;
    }

    public void setNivelEficienciaProcesso(NivelEficiencia nivelEficienciaProcesso) {
        this.nivelEficienciaProcesso = nivelEficienciaProcesso;
    }

    public NivelEficiencia getNivelEficienciaProduto() {
        return nivelEficienciaProduto;
    }

    public Avaliacao nivelEficienciaProduto(NivelEficiencia nivelEficienciaProduto) {
        this.nivelEficienciaProduto = nivelEficienciaProduto;
        return this;
    }

    public void setNivelEficienciaProduto(NivelEficiencia nivelEficienciaProduto) {
        this.nivelEficienciaProduto = nivelEficienciaProduto;
    }

    public Instant getCanceladoEm() {
        return canceladoEm;
    }

    public Avaliacao canceladoEm(Instant canceladoEm) {
        this.canceladoEm = canceladoEm;
        return this;
    }

    public void setCanceladoEm(Instant canceladoEm) {
        this.canceladoEm = canceladoEm;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public Avaliacao motivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
        return this;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public Set<ItemAvaliado> getItensAvaliados() {
        return itensAvaliados;
    }

    public Avaliacao itensAvaliados(Set<ItemAvaliado> itemAvaliados) {
        this.itensAvaliados = itemAvaliados;
        return this;
    }

    public Avaliacao addItensAvaliados(ItemAvaliado itemAvaliado) {
        this.itensAvaliados.add(itemAvaliado);
        itemAvaliado.setAvaliacao(this);
        return this;
    }

    public Avaliacao removeItensAvaliados(ItemAvaliado itemAvaliado) {
        this.itensAvaliados.remove(itemAvaliado);
        itemAvaliado.setAvaliacao(null);
        return this;
    }

    public void setItensAvaliados(Set<ItemAvaliado> itemAvaliados) {
        this.itensAvaliados = itemAvaliados;
    }

    public Set<ItemAvaliadoPerdaQuebraAcumulados> getItensPerdaEQuebraAcumulados() {
        return itensPerdaEQuebraAcumulados;
    }

    public Avaliacao itensPerdaEQuebraAcumulados(Set<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumulados) {
        this.itensPerdaEQuebraAcumulados = itemAvaliadoPerdaQuebraAcumulados;
        return this;
    }

    public Avaliacao addItensPerdaEQuebraAcumulados(ItemAvaliadoPerdaQuebraAcumulados itemAvaliadoPerdaQuebraAcumulados) {
        this.itensPerdaEQuebraAcumulados.add(itemAvaliadoPerdaQuebraAcumulados);
        itemAvaliadoPerdaQuebraAcumulados.setAvaliacao(this);
        return this;
    }

    public Avaliacao removeItensPerdaEQuebraAcumulados(ItemAvaliadoPerdaQuebraAcumulados itemAvaliadoPerdaQuebraAcumulados) {
        this.itensPerdaEQuebraAcumulados.remove(itemAvaliadoPerdaQuebraAcumulados);
        itemAvaliadoPerdaQuebraAcumulados.setAvaliacao(null);
        return this;
    }

    public void setItensPerdaEQuebraAcumulados(Set<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumulados) {
        this.itensPerdaEQuebraAcumulados = itemAvaliadoPerdaQuebraAcumulados;
    }

    public Set<ItemAuditado> getItensAuditados() {
        return itensAuditados;
    }

    public Avaliacao itensAuditados(Set<ItemAuditado> itemAuditados) {
        this.itensAuditados = itemAuditados;
        return this;
    }

    public Avaliacao addItensAuditados(ItemAuditado itemAuditado) {
        this.itensAuditados.add(itemAuditado);
        itemAuditado.setAvaliacao(this);
        return this;
    }

    public Avaliacao removeItensAuditados(ItemAuditado itemAuditado) {
        this.itensAuditados.remove(itemAuditado);
        itemAuditado.setAvaliacao(null);
        return this;
    }

    public void setItensAuditados(Set<ItemAuditado> itemAuditados) {
        this.itensAuditados = itemAuditados;
    }

    public Set<ItemSolicitadoAjuste> getItensComAjusteSolicitados() {
        return itensComAjusteSolicitados;
    }

    public Avaliacao itensComAjusteSolicitados(Set<ItemSolicitadoAjuste> itemSolicitadoAjustes) {
        this.itensComAjusteSolicitados = itemSolicitadoAjustes;
        return this;
    }

    public Avaliacao addItensComAjusteSolicitado(ItemSolicitadoAjuste itemSolicitadoAjuste) {
        this.itensComAjusteSolicitados.add(itemSolicitadoAjuste);
        itemSolicitadoAjuste.setAvaliacao(this);
        return this;
    }

    public Avaliacao removeItensComAjusteSolicitado(ItemSolicitadoAjuste itemSolicitadoAjuste) {
        this.itensComAjusteSolicitados.remove(itemSolicitadoAjuste);
        itemSolicitadoAjuste.setAvaliacao(null);
        return this;
    }

    public void setItensComAjusteSolicitados(Set<ItemSolicitadoAjuste> itemSolicitadoAjustes) {
        this.itensComAjusteSolicitados = itemSolicitadoAjustes;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public Avaliacao questionario(Questionario questionario) {
        this.questionario = questionario;
        return this;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public Avaliador getAvaliador() {
        return avaliador;
    }

    public Avaliacao avaliador(Avaliador avaliador) {
        this.avaliador = avaliador;
        return this;
    }

    public void setAvaliador(Avaliador avaliador) {
        this.avaliador = avaliador;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Avaliacao avaliacao = (Avaliacao) o;
        if (avaliacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), avaliacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
            "id=" + getId() +
            ", dataInicio='" + getDataInicio() + "'" +
            ", latitudeInicioAvaliacao=" + getLatitudeInicioAvaliacao() +
            ", longitudeInicioAvaliacao=" + getLongitudeInicioAvaliacao() +
            ", nomeResponsavelLoja='" + getNomeResponsavelLoja() + "'" +
            ", prontuarioResponsavelLoja=" + getProntuarioResponsavelLoja() +
            ", submetidoEm='" + getSubmetidoEm() + "'" +
            ", latitudeSubmissaoAvaliacao=" + getLatitudeSubmissaoAvaliacao() +
            ", longitudeSubmissaoAvaliacao=" + getLongitudeSubmissaoAvaliacao() +
            ", observacaoSubmissaoEnviadaForaDaLoja='" + getObservacaoSubmissaoEnviadaForaDaLoja() + "'" +
            ", status='" + getStatus() + "'" +
            ", criticidadePainel='" + getCriticidadePainel() + "'" +
            ", nivelEficienciaGeral='" + getNivelEficienciaGeral() + "'" +
            ", nivelEficienciaProcedimento='" + getNivelEficienciaProcedimento() + "'" +
            ", nivelEficienciaPessoa='" + getNivelEficienciaPessoa() + "'" +
            ", nivelEficienciaProcesso='" + getNivelEficienciaProcesso() + "'" +
            ", nivelEficienciaProduto='" + getNivelEficienciaProduto() + "'" +
            ", canceladoEm='" + getCanceladoEm() + "'" +
            ", motivoCancelamento='" + getMotivoCancelamento() + "'" +
            "}";
    }
}
