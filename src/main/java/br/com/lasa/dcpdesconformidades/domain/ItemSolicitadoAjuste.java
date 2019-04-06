package br.com.lasa.dcpdesconformidades.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A ItemSolicitadoAjuste.
 */
@Entity
@Table(name = "item_solicitado_ajuste")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemSolicitadoAjuste implements Serializable {

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
    @Column(name = "codigo_departamento", nullable = false)
    private Integer codigoDepartamento;

    @NotNull
    @Column(name = "codigo_sap", nullable = false)
    private Integer codigoSap;

    @NotNull
    @Column(name = "descricao_item", nullable = false)
    private String descricaoItem;

    @NotNull
    @Column(name = "saldo_sap_0001", nullable = false)
    private Integer saldoSap0001;

    @NotNull
    @Column(name = "saldo_fisico_0001", nullable = false)
    private Integer saldoFisico0001;

    @Column(name = "saldo_sap_9000")
    private Integer saldoSap9000;

    @Column(name = "saldo_fisico_9000")
    private Integer saldoFisico9000;

    @Column(name = "motivo_divergencia")
    private String motivoDivergencia;

    @Column(name = "acao_corretiva_ou_preventiva")
    private String acaoCorretivaOuPreventiva;

    @Column(name = "responsavel")
    private String responsavel;

    //TODO Avaliar possibilidade de JsonIgnore a propriedade avaliacao com um todo
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties({"itensAvaliados", "itensAuditados", "itensComAjusteSolicitados"})
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

    public ItemSolicitadoAjuste respondidoEm(Instant respondidoEm) {
        this.respondidoEm = respondidoEm;
        return this;
    }

    public void setRespondidoEm(Instant respondidoEm) {
        this.respondidoEm = respondidoEm;
    }

    public Instant getUltimaAtualizacaoEm() {
        return ultimaAtualizacaoEm;
    }

    public ItemSolicitadoAjuste ultimaAtualizacaoEm(Instant ultimaAtualizacaoEm) {
        this.ultimaAtualizacaoEm = ultimaAtualizacaoEm;
        return this;
    }

    public void setUltimaAtualizacaoEm(Instant ultimaAtualizacaoEm) {
        this.ultimaAtualizacaoEm = ultimaAtualizacaoEm;
    }

    public Integer getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public ItemSolicitadoAjuste codigoDepartamento(Integer codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
        return this;
    }

    public void setCodigoDepartamento(Integer codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public Integer getCodigoSap() {
        return codigoSap;
    }

    public ItemSolicitadoAjuste codigoSap(Integer codigoSap) {
        this.codigoSap = codigoSap;
        return this;
    }

    public void setCodigoSap(Integer codigoSap) {
        this.codigoSap = codigoSap;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public ItemSolicitadoAjuste descricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
        return this;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public Integer getSaldoSap0001() {
        return saldoSap0001;
    }

    public ItemSolicitadoAjuste saldoSap0001(Integer saldoSap0001) {
        this.saldoSap0001 = saldoSap0001;
        return this;
    }

    public void setSaldoSap0001(Integer saldoSap0001) {
        this.saldoSap0001 = saldoSap0001;
    }

    public Integer getSaldoFisico0001() {
        return saldoFisico0001;
    }

    public ItemSolicitadoAjuste saldoFisico0001(Integer saldoFisico0001) {
        this.saldoFisico0001 = saldoFisico0001;
        return this;
    }

    public void setSaldoFisico0001(Integer saldoFisico0001) {
        this.saldoFisico0001 = saldoFisico0001;
    }

    public Integer getSaldoSap9000() {
        return saldoSap9000;
    }

    public ItemSolicitadoAjuste saldoSap9000(Integer saldoSap9000) {
        this.saldoSap9000 = saldoSap9000;
        return this;
    }

    public void setSaldoSap9000(Integer saldoSap9000) {
        this.saldoSap9000 = saldoSap9000;
    }

    public Integer getSaldoFisico9000() {
        return saldoFisico9000;
    }

    public ItemSolicitadoAjuste saldoFisico9000(Integer saldoFisico9000) {
        this.saldoFisico9000 = saldoFisico9000;
        return this;
    }

    public void setSaldoFisico9000(Integer saldoFisico9000) {
        this.saldoFisico9000 = saldoFisico9000;
    }

    public String getMotivoDivergencia() {
        return motivoDivergencia;
    }

    public ItemSolicitadoAjuste motivoDivergencia(String motivoDivergencia) {
        this.motivoDivergencia = motivoDivergencia;
        return this;
    }

    public void setMotivoDivergencia(String motivoDivergencia) {
        this.motivoDivergencia = motivoDivergencia;
    }

    public String getAcaoCorretivaOuPreventiva() {
        return acaoCorretivaOuPreventiva;
    }

    public ItemSolicitadoAjuste acaoCorretivaOuPreventiva(String acaoCorretivaOuPreventiva) {
        this.acaoCorretivaOuPreventiva = acaoCorretivaOuPreventiva;
        return this;
    }

    public void setAcaoCorretivaOuPreventiva(String acaoCorretivaOuPreventiva) {
        this.acaoCorretivaOuPreventiva = acaoCorretivaOuPreventiva;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public ItemSolicitadoAjuste responsavel(String responsavel) {
        this.responsavel = responsavel;
        return this;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public ItemSolicitadoAjuste avaliacao(Avaliacao avaliacao) {
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
        ItemSolicitadoAjuste itemSolicitadoAjuste = (ItemSolicitadoAjuste) o;
        if (itemSolicitadoAjuste.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemSolicitadoAjuste.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemSolicitadoAjuste{" +
            "id=" + getId() +
            ", respondidoEm='" + getRespondidoEm() + "'" +
            ", ultimaAtualizacaoEm='" + getUltimaAtualizacaoEm() + "'" +
            ", codigoDepartamento=" + getCodigoDepartamento() +
            ", codigoSap=" + getCodigoSap() +
            ", descricaoItem='" + getDescricaoItem() + "'" +
            ", saldoSap0001=" + getSaldoSap0001() +
            ", saldoFisico0001=" + getSaldoFisico0001() +
            ", saldoSap9000=" + getSaldoSap9000() +
            ", saldoFisico9000=" + getSaldoFisico9000() +
            ", motivoDivergencia='" + getMotivoDivergencia() + "'" +
            ", acaoCorretivaOuPreventiva='" + getAcaoCorretivaOuPreventiva() + "'" +
            ", responsavel='" + getResponsavel() + "'" +
            "}";
    }
}
