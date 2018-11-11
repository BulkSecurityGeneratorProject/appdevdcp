package br.com.lasa.dcpdesconformidades.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

import br.com.lasa.dcpdesconformidades.domain.enumeration.TipoItemAvaliadoPerdaQuebra;

/**
 * A ItemAvaliadoPerdaQuebraAcumulados.
 */
@Entity
@Table(name = "item_perda_quebra_acumulados")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemAvaliadoPerdaQuebraAcumulados implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoItemAvaliadoPerdaQuebra tipo;

    @NotNull
    @Column(name = "respondido_em", nullable = false)
    private Instant respondidoEm;

    @Column(name = "ultima_atualizacao_em")
    private Instant ultimaAtualizacaoEm;

    @NotNull
    @Column(name = "percentual", nullable = false)
    private Double percentual;

    @NotNull
    @Column(name = "financeiro", precision = 10, scale = 2, nullable = false)
    private BigDecimal financeiro;

    @NotNull
    @Column(name = "pontuacao", nullable = false)
    private Integer pontuacao;

    @NotNull
    @Column(name = "latitude_local_resposta", nullable = false)
    private Double latitudeLocalResposta;

    @NotNull
    @Column(name = "longitude_local_resposta", nullable = false)
    private Double longitudeLocalResposta;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("itensPerdaEQuebraAcumulados")
    private Avaliacao avaliacao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoItemAvaliadoPerdaQuebra getTipo() {
        return tipo;
    }

    public ItemAvaliadoPerdaQuebraAcumulados tipo(TipoItemAvaliadoPerdaQuebra tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(TipoItemAvaliadoPerdaQuebra tipo) {
        this.tipo = tipo;
    }

    public Instant getRespondidoEm() {
        return respondidoEm;
    }

    public ItemAvaliadoPerdaQuebraAcumulados respondidoEm(Instant respondidoEm) {
        this.respondidoEm = respondidoEm;
        return this;
    }

    public void setRespondidoEm(Instant respondidoEm) {
        this.respondidoEm = respondidoEm;
    }

    public Instant getUltimaAtualizacaoEm() {
        return ultimaAtualizacaoEm;
    }

    public ItemAvaliadoPerdaQuebraAcumulados ultimaAtualizacaoEm(Instant ultimaAtualizacaoEm) {
        this.ultimaAtualizacaoEm = ultimaAtualizacaoEm;
        return this;
    }

    public void setUltimaAtualizacaoEm(Instant ultimaAtualizacaoEm) {
        this.ultimaAtualizacaoEm = ultimaAtualizacaoEm;
    }

    public Double getPercentual() {
        return percentual;
    }

    public ItemAvaliadoPerdaQuebraAcumulados percentual(Double percentual) {
        this.percentual = percentual;
        return this;
    }

    public void setPercentual(Double percentual) {
        this.percentual = percentual;
    }

    public BigDecimal getFinanceiro() {
        return financeiro;
    }

    public ItemAvaliadoPerdaQuebraAcumulados financeiro(BigDecimal financeiro) {
        this.financeiro = financeiro;
        return this;
    }

    public void setFinanceiro(BigDecimal financeiro) {
        this.financeiro = financeiro;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public ItemAvaliadoPerdaQuebraAcumulados pontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
        return this;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Double getLatitudeLocalResposta() {
        return latitudeLocalResposta;
    }

    public ItemAvaliadoPerdaQuebraAcumulados latitudeLocalResposta(Double latitudeLocalResposta) {
        this.latitudeLocalResposta = latitudeLocalResposta;
        return this;
    }

    public void setLatitudeLocalResposta(Double latitudeLocalResposta) {
        this.latitudeLocalResposta = latitudeLocalResposta;
    }

    public Double getLongitudeLocalResposta() {
        return longitudeLocalResposta;
    }

    public ItemAvaliadoPerdaQuebraAcumulados longitudeLocalResposta(Double longitudeLocalResposta) {
        this.longitudeLocalResposta = longitudeLocalResposta;
        return this;
    }

    public void setLongitudeLocalResposta(Double longitudeLocalResposta) {
        this.longitudeLocalResposta = longitudeLocalResposta;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public ItemAvaliadoPerdaQuebraAcumulados avaliacao(Avaliacao avaliacao) {
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
        ItemAvaliadoPerdaQuebraAcumulados itemAvaliadoPerdaQuebraAcumulados = (ItemAvaliadoPerdaQuebraAcumulados) o;
        if (itemAvaliadoPerdaQuebraAcumulados.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemAvaliadoPerdaQuebraAcumulados.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemAvaliadoPerdaQuebraAcumulados{" +
            "id=" + getId() +
            ", tipo='" + getTipo() + "'" +
            ", respondidoEm='" + getRespondidoEm() + "'" +
            ", ultimaAtualizacaoEm='" + getUltimaAtualizacaoEm() + "'" +
            ", percentual=" + getPercentual() +
            ", financeiro=" + getFinanceiro() +
            ", pontuacao=" + getPontuacao() +
            ", latitudeLocalResposta=" + getLatitudeLocalResposta() +
            ", longitudeLocalResposta=" + getLongitudeLocalResposta() +
            "}";
    }
}
