package br.com.lasa.dcpdesconformidades.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import br.com.lasa.dcpdesconformidades.domain.enumeration.TipoItemAuditado;

/**
 * A ItemAuditado.
 */
@Entity
@Table(name = "item_auditado")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemAuditado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "respondido_em", nullable = false)
    private Instant respondidoEm;

    @Column(name = "ultima_atualizacao_em")
    private Instant ultimaAtualizacaoEm;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoItemAuditado tipo;

    @NotNull
    @Column(name = "codigo_departamento", nullable = false)
    private Integer codigoDepartamento;

    @NotNull
    @Column(name = "codigo_sap", nullable = false)
    private Integer codigoSap;

    @NotNull
    @Column(name = "descricao_item", nullable = false)
    private String descricaoItem;

    @NotNull
    @Column(name = "saldo_sap", nullable = false)
    private Integer saldoSap;

    @NotNull
    @Column(name = "saldo_fisico", nullable = false)
    private Integer saldoFisico;

    @Column(name = "motivo_divergencia")
    private String motivoDivergencia;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("itensAuditados")
    private Avaliacao avaliacao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getRespondidoEm() {
        return respondidoEm;
    }

    public ItemAuditado respondidoEm(Instant respondidoEm) {
        this.respondidoEm = respondidoEm;
        return this;
    }

    public void setRespondidoEm(Instant respondidoEm) {
        this.respondidoEm = respondidoEm;
    }

    public Instant getUltimaAtualizacaoEm() {
        return ultimaAtualizacaoEm;
    }

    public ItemAuditado ultimaAtualizacaoEm(Instant ultimaAtualizacaoEm) {
        this.ultimaAtualizacaoEm = ultimaAtualizacaoEm;
        return this;
    }

    public void setUltimaAtualizacaoEm(Instant ultimaAtualizacaoEm) {
        this.ultimaAtualizacaoEm = ultimaAtualizacaoEm;
    }

    public TipoItemAuditado getTipo() {
        return tipo;
    }

    public ItemAuditado tipo(TipoItemAuditado tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(TipoItemAuditado tipo) {
        this.tipo = tipo;
    }

    public Integer getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public ItemAuditado codigoDepartamento(Integer codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
        return this;
    }

    public void setCodigoDepartamento(Integer codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public Integer getCodigoSap() {
        return codigoSap;
    }

    public ItemAuditado codigoSap(Integer codigoSap) {
        this.codigoSap = codigoSap;
        return this;
    }

    public void setCodigoSap(Integer codigoSap) {
        this.codigoSap = codigoSap;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public ItemAuditado descricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
        return this;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public Integer getSaldoSap() {
        return saldoSap;
    }

    public ItemAuditado saldoSap(Integer saldoSap) {
        this.saldoSap = saldoSap;
        return this;
    }

    public void setSaldoSap(Integer saldoSap) {
        this.saldoSap = saldoSap;
    }

    public Integer getSaldoFisico() {
        return saldoFisico;
    }

    public ItemAuditado saldoFisico(Integer saldoFisico) {
        this.saldoFisico = saldoFisico;
        return this;
    }

    public void setSaldoFisico(Integer saldoFisico) {
        this.saldoFisico = saldoFisico;
    }

    public String getMotivoDivergencia() {
        return motivoDivergencia;
    }

    public ItemAuditado motivoDivergencia(String motivoDivergencia) {
        this.motivoDivergencia = motivoDivergencia;
        return this;
    }

    public void setMotivoDivergencia(String motivoDivergencia) {
        this.motivoDivergencia = motivoDivergencia;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public ItemAuditado avaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
        return this;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
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
        ItemAuditado itemAuditado = (ItemAuditado) o;
        if (itemAuditado.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemAuditado.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemAuditado{" +
            "id=" + getId() +
            ", respondidoEm='" + getRespondidoEm() + "'" +
            ", ultimaAtualizacaoEm='" + getUltimaAtualizacaoEm() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", codigoDepartamento=" + getCodigoDepartamento() +
            ", codigoSap=" + getCodigoSap() +
            ", descricaoItem='" + getDescricaoItem() + "'" +
            ", saldoSap=" + getSaldoSap() +
            ", saldoFisico=" + getSaldoFisico() +
            ", motivoDivergencia='" + getMotivoDivergencia() + "'" +
            "}";
    }
}
