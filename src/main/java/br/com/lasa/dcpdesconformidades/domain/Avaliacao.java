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

    @OneToMany(mappedBy = "avaliacao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemAvaliado> itemAvaliados = new HashSet<>();
    @OneToMany(mappedBy = "avaliacao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemAvaliadoPerdaQuebraAcumulados> itensPerdaEQuebraAcumulados = new HashSet<>();
    @OneToMany(mappedBy = "avaliacao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemAuditado> itensAuditados = new HashSet<>();
    @OneToMany(mappedBy = "avaliacao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemSolicitadoAjuste> itensComAjusteSolicitados = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("avaliacoesRealizadas")
    private Questionario questionario;

    @ManyToOne
    @JsonIgnoreProperties("avaliacaos")
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

    public Set<ItemAvaliado> getItemAvaliados() {
        return itemAvaliados;
    }

    public Avaliacao itemAvaliados(Set<ItemAvaliado> itemAvaliados) {
        this.itemAvaliados = itemAvaliados;
        return this;
    }

    public Avaliacao addItemAvaliado(ItemAvaliado itemAvaliado) {
        this.itemAvaliados.add(itemAvaliado);
        itemAvaliado.setAvaliacao(this);
        return this;
    }

    public Avaliacao removeItemAvaliado(ItemAvaliado itemAvaliado) {
        this.itemAvaliados.remove(itemAvaliado);
        itemAvaliado.setAvaliacao(null);
        return this;
    }

    public void setItemAvaliados(Set<ItemAvaliado> itemAvaliados) {
        this.itemAvaliados = itemAvaliados;
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
            "}";
    }
}
