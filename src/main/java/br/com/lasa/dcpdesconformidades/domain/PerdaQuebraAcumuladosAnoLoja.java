package br.com.lasa.dcpdesconformidades.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusItemAvaliado;

import br.com.lasa.dcpdesconformidades.domain.enumeration.CategorizacaoPerdaQuebra;

/**
 * A PerdaQuebraAcumuladosAnoLoja.
 */
@Entity
@Table(name = "perda_quebra_acumulados_loja")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PerdaQuebraAcumuladosAnoLoja implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ano", nullable = false)
    private Integer ano;

    @NotNull
    @Column(name = "percentual_perda", nullable = false)
    private Double percentualPerda;

    @NotNull
    @Column(name = "financeiro_perda", precision = 10, scale = 2, nullable = false)
    private BigDecimal financeiroPerda;

    @NotNull
    @Column(name = "pontuacao_perda", nullable = false)
    private Integer pontuacaoPerda;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_perda", nullable = false)
    private StatusItemAvaliado statusPerda;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "categorizacao_perda", nullable = false)
    private CategorizacaoPerdaQuebra categorizacaoPerda;

    @NotNull
    @Column(name = "percentual_quebra", nullable = false)
    private Double percentualQuebra;

    @NotNull
    @Column(name = "financeiro_quebra", precision = 10, scale = 2, nullable = false)
    private BigDecimal financeiroQuebra;

    @NotNull
    @Column(name = "pontuacao_quebra", nullable = false)
    private Integer pontuacaoQuebra;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_quebra", nullable = false)
    private StatusItemAvaliado statusQuebra;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "categorizacao_quebra", nullable = false)
    private CategorizacaoPerdaQuebra categorizacaoQuebra;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("perdaQuebraAcumuladosAnos")
    private Loja loja;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public PerdaQuebraAcumuladosAnoLoja ano(Integer ano) {
        this.ano = ano;
        return this;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getPercentualPerda() {
        return percentualPerda;
    }

    public PerdaQuebraAcumuladosAnoLoja percentualPerda(Double percentualPerda) {
        this.percentualPerda = percentualPerda;
        return this;
    }

    public void setPercentualPerda(Double percentualPerda) {
        this.percentualPerda = percentualPerda;
    }

    public BigDecimal getFinanceiroPerda() {
        return financeiroPerda;
    }

    public PerdaQuebraAcumuladosAnoLoja financeiroPerda(BigDecimal financeiroPerda) {
        this.financeiroPerda = financeiroPerda;
        return this;
    }

    public void setFinanceiroPerda(BigDecimal financeiroPerda) {
        this.financeiroPerda = financeiroPerda;
    }

    public Integer getPontuacaoPerda() {
        return pontuacaoPerda;
    }

    public PerdaQuebraAcumuladosAnoLoja pontuacaoPerda(Integer pontuacaoPerda) {
        this.pontuacaoPerda = pontuacaoPerda;
        return this;
    }

    public void setPontuacaoPerda(Integer pontuacaoPerda) {
        this.pontuacaoPerda = pontuacaoPerda;
    }

    public StatusItemAvaliado getStatusPerda() {
        return statusPerda;
    }

    public PerdaQuebraAcumuladosAnoLoja statusPerda(StatusItemAvaliado statusPerda) {
        this.statusPerda = statusPerda;
        return this;
    }

    public void setStatusPerda(StatusItemAvaliado statusPerda) {
        this.statusPerda = statusPerda;
    }

    public CategorizacaoPerdaQuebra getCategorizacaoPerda() {
        return categorizacaoPerda;
    }

    public PerdaQuebraAcumuladosAnoLoja categorizacaoPerda(CategorizacaoPerdaQuebra categorizacaoPerda) {
        this.categorizacaoPerda = categorizacaoPerda;
        return this;
    }

    public void setCategorizacaoPerda(CategorizacaoPerdaQuebra categorizacaoPerda) {
        this.categorizacaoPerda = categorizacaoPerda;
    }

    public Double getPercentualQuebra() {
        return percentualQuebra;
    }

    public PerdaQuebraAcumuladosAnoLoja percentualQuebra(Double percentualQuebra) {
        this.percentualQuebra = percentualQuebra;
        return this;
    }

    public void setPercentualQuebra(Double percentualQuebra) {
        this.percentualQuebra = percentualQuebra;
    }

    public BigDecimal getFinanceiroQuebra() {
        return financeiroQuebra;
    }

    public PerdaQuebraAcumuladosAnoLoja financeiroQuebra(BigDecimal financeiroQuebra) {
        this.financeiroQuebra = financeiroQuebra;
        return this;
    }

    public void setFinanceiroQuebra(BigDecimal financeiroQuebra) {
        this.financeiroQuebra = financeiroQuebra;
    }

    public Integer getPontuacaoQuebra() {
        return pontuacaoQuebra;
    }

    public PerdaQuebraAcumuladosAnoLoja pontuacaoQuebra(Integer pontuacaoQuebra) {
        this.pontuacaoQuebra = pontuacaoQuebra;
        return this;
    }

    public void setPontuacaoQuebra(Integer pontuacaoQuebra) {
        this.pontuacaoQuebra = pontuacaoQuebra;
    }

    public StatusItemAvaliado getStatusQuebra() {
        return statusQuebra;
    }

    public PerdaQuebraAcumuladosAnoLoja statusQuebra(StatusItemAvaliado statusQuebra) {
        this.statusQuebra = statusQuebra;
        return this;
    }

    public void setStatusQuebra(StatusItemAvaliado statusQuebra) {
        this.statusQuebra = statusQuebra;
    }

    public CategorizacaoPerdaQuebra getCategorizacaoQuebra() {
        return categorizacaoQuebra;
    }

    public PerdaQuebraAcumuladosAnoLoja categorizacaoQuebra(CategorizacaoPerdaQuebra categorizacaoQuebra) {
        this.categorizacaoQuebra = categorizacaoQuebra;
        return this;
    }

    public void setCategorizacaoQuebra(CategorizacaoPerdaQuebra categorizacaoQuebra) {
        this.categorizacaoQuebra = categorizacaoQuebra;
    }

    public Loja getLoja() {
        return loja;
    }

    public PerdaQuebraAcumuladosAnoLoja loja(Loja loja) {
        this.loja = loja;
        return this;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
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
        PerdaQuebraAcumuladosAnoLoja perdaQuebraAcumuladosAnoLoja = (PerdaQuebraAcumuladosAnoLoja) o;
        if (perdaQuebraAcumuladosAnoLoja.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), perdaQuebraAcumuladosAnoLoja.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PerdaQuebraAcumuladosAnoLoja{" +
            "id=" + getId() +
            ", ano=" + getAno() +
            ", percentualPerda=" + getPercentualPerda() +
            ", financeiroPerda=" + getFinanceiroPerda() +
            ", pontuacaoPerda=" + getPontuacaoPerda() +
            ", statusPerda='" + getStatusPerda() + "'" +
            ", categorizacaoPerda='" + getCategorizacaoPerda() + "'" +
            ", percentualQuebra=" + getPercentualQuebra() +
            ", financeiroQuebra=" + getFinanceiroQuebra() +
            ", pontuacaoQuebra=" + getPontuacaoQuebra() +
            ", statusQuebra='" + getStatusQuebra() + "'" +
            ", categorizacaoQuebra='" + getCategorizacaoQuebra() + "'" +
            "}";
    }
}
