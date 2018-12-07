package br.com.lasa.dcpdesconformidades.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusItemAvaliado;

/**
 * A ItemAvaliado.
 */
@Entity
@Table(name = "item_avaliado")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemAvaliado implements Serializable {

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
    @Column(name = "status", nullable = false)
    private StatusItemAvaliado status;

    @Column(name = "observacoes")
    private String observacoes;

    @NotNull
    @Column(name = "latitude_local_resposta", nullable = false)
    private Double latitudeLocalResposta;

    @NotNull
    @Column(name = "longitude_local_resposta", nullable = false)
    private Double longitudeLocalResposta;

    @NotNull
    @Column(name = "pontos_procedimento", nullable = false)
    private Integer pontosProcedimento;

    @NotNull
    @Column(name = "pontos_pessoa", nullable = false)
    private Integer pontosPessoa;

    @NotNull
    @Column(name = "pontos_processo", nullable = false)
    private Integer pontosProcesso;

    @NotNull
    @Column(name = "pontos_produto", nullable = false)
    private Integer pontosProduto;

    @Column(name = "pontos_obtidos_procedimento")
    private Integer pontosObtidosProcedimento;

    @Column(name = "pontos_obtidos_pessoa")
    private Integer pontosObtidosPessoa;

    @Column(name = "pontos_obtidos_processo")
    private Integer pontosObtidosProcesso;

    @Column(name = "pontos_obtidos_produto")
    private Integer pontosObtidosProduto;

    @OneToMany(mappedBy = "itemAvaliado", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AnexoItem> anexos = new HashSet<>();
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("itensAvaliados")
    private ItemAvaliacao itemAvaliacao;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("itensAvaliados")
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

    public ItemAvaliado respondidoEm(Instant respondidoEm) {
        this.respondidoEm = respondidoEm;
        return this;
    }

    public void setRespondidoEm(Instant respondidoEm) {
        this.respondidoEm = respondidoEm;
    }

    public Instant getUltimaAtualizacaoEm() {
        return ultimaAtualizacaoEm;
    }

    public ItemAvaliado ultimaAtualizacaoEm(Instant ultimaAtualizacaoEm) {
        this.ultimaAtualizacaoEm = ultimaAtualizacaoEm;
        return this;
    }

    public void setUltimaAtualizacaoEm(Instant ultimaAtualizacaoEm) {
        this.ultimaAtualizacaoEm = ultimaAtualizacaoEm;
    }

    public StatusItemAvaliado getStatus() {
        return status;
    }

    public ItemAvaliado status(StatusItemAvaliado status) {
        this.status = status;
        return this;
    }

    public void setStatus(StatusItemAvaliado status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public ItemAvaliado observacoes(String observacoes) {
        this.observacoes = observacoes;
        return this;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Double getLatitudeLocalResposta() {
        return latitudeLocalResposta;
    }

    public ItemAvaliado latitudeLocalResposta(Double latitudeLocalResposta) {
        this.latitudeLocalResposta = latitudeLocalResposta;
        return this;
    }

    public void setLatitudeLocalResposta(Double latitudeLocalResposta) {
        this.latitudeLocalResposta = latitudeLocalResposta;
    }

    public Double getLongitudeLocalResposta() {
        return longitudeLocalResposta;
    }

    public ItemAvaliado longitudeLocalResposta(Double longitudeLocalResposta) {
        this.longitudeLocalResposta = longitudeLocalResposta;
        return this;
    }

    public void setLongitudeLocalResposta(Double longitudeLocalResposta) {
        this.longitudeLocalResposta = longitudeLocalResposta;
    }

    public Integer getPontosProcedimento() {
        return pontosProcedimento;
    }

    public ItemAvaliado pontosProcedimento(Integer pontosProcedimento) {
        this.pontosProcedimento = pontosProcedimento;
        return this;
    }

    public void setPontosProcedimento(Integer pontosProcedimento) {
        this.pontosProcedimento = pontosProcedimento;
    }

    public Integer getPontosPessoa() {
        return pontosPessoa;
    }

    public ItemAvaliado pontosPessoa(Integer pontosPessoa) {
        this.pontosPessoa = pontosPessoa;
        return this;
    }

    public void setPontosPessoa(Integer pontosPessoa) {
        this.pontosPessoa = pontosPessoa;
    }

    public Integer getPontosProcesso() {
        return pontosProcesso;
    }

    public ItemAvaliado pontosProcesso(Integer pontosProcesso) {
        this.pontosProcesso = pontosProcesso;
        return this;
    }

    public void setPontosProcesso(Integer pontosProcesso) {
        this.pontosProcesso = pontosProcesso;
    }

    public Integer getPontosProduto() {
        return pontosProduto;
    }

    public ItemAvaliado pontosProduto(Integer pontosProduto) {
        this.pontosProduto = pontosProduto;
        return this;
    }

    public void setPontosProduto(Integer pontosProduto) {
        this.pontosProduto = pontosProduto;
    }

    public Integer getPontosObtidosProcedimento() {
        return pontosObtidosProcedimento;
    }

    public ItemAvaliado pontosObtidosProcedimento(Integer pontosObtidosProcedimento) {
        this.pontosObtidosProcedimento = pontosObtidosProcedimento;
        return this;
    }

    public void setPontosObtidosProcedimento(Integer pontosObtidosProcedimento) {
        this.pontosObtidosProcedimento = pontosObtidosProcedimento;
    }

    public Integer getPontosObtidosPessoa() {
        return pontosObtidosPessoa;
    }

    public ItemAvaliado pontosObtidosPessoa(Integer pontosObtidosPessoa) {
        this.pontosObtidosPessoa = pontosObtidosPessoa;
        return this;
    }

    public void setPontosObtidosPessoa(Integer pontosObtidosPessoa) {
        this.pontosObtidosPessoa = pontosObtidosPessoa;
    }

    public Integer getPontosObtidosProcesso() {
        return pontosObtidosProcesso;
    }

    public ItemAvaliado pontosObtidosProcesso(Integer pontosObtidosProcesso) {
        this.pontosObtidosProcesso = pontosObtidosProcesso;
        return this;
    }

    public void setPontosObtidosProcesso(Integer pontosObtidosProcesso) {
        this.pontosObtidosProcesso = pontosObtidosProcesso;
    }

    public Integer getPontosObtidosProduto() {
        return pontosObtidosProduto;
    }

    public ItemAvaliado pontosObtidosProduto(Integer pontosObtidosProduto) {
        this.pontosObtidosProduto = pontosObtidosProduto;
        return this;
    }

    public void setPontosObtidosProduto(Integer pontosObtidosProduto) {
        this.pontosObtidosProduto = pontosObtidosProduto;
    }

    public Set<AnexoItem> getAnexos() {
        return anexos;
    }

    public ItemAvaliado anexos(Set<AnexoItem> anexoItems) {
        this.anexos = anexoItems;
        return this;
    }

    public ItemAvaliado addAnexos(AnexoItem anexoItem) {
        this.anexos.add(anexoItem);
        anexoItem.setItemAvaliado(this);
        return this;
    }

    public ItemAvaliado removeAnexos(AnexoItem anexoItem) {
        this.anexos.remove(anexoItem);
        anexoItem.setItemAvaliado(null);
        return this;
    }

    public void setAnexos(Set<AnexoItem> anexoItems) {
        this.anexos = anexoItems;
    }

    public ItemAvaliacao getItemAvaliacao() {
        return itemAvaliacao;
    }

    public ItemAvaliado itemAvaliacao(ItemAvaliacao itemAvaliacao) {
        this.itemAvaliacao = itemAvaliacao;
        return this;
    }

    public void setItemAvaliacao(ItemAvaliacao itemAvaliacao) {
        this.itemAvaliacao = itemAvaliacao;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public ItemAvaliado avaliacao(Avaliacao avaliacao) {
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
        ItemAvaliado itemAvaliado = (ItemAvaliado) o;
        if (itemAvaliado.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemAvaliado.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemAvaliado{" +
            "id=" + getId() +
            ", respondidoEm='" + getRespondidoEm() + "'" +
            ", ultimaAtualizacaoEm='" + getUltimaAtualizacaoEm() + "'" +
            ", status='" + getStatus() + "'" +
            ", observacoes='" + getObservacoes() + "'" +
            ", latitudeLocalResposta=" + getLatitudeLocalResposta() +
            ", longitudeLocalResposta=" + getLongitudeLocalResposta() +
            ", pontosProcedimento=" + getPontosProcedimento() +
            ", pontosPessoa=" + getPontosPessoa() +
            ", pontosProcesso=" + getPontosProcesso() +
            ", pontosProduto=" + getPontosProduto() +
            ", pontosObtidosProcedimento=" + getPontosObtidosProcedimento() +
            ", pontosObtidosPessoa=" + getPontosObtidosPessoa() +
            ", pontosObtidosProcesso=" + getPontosObtidosProcesso() +
            ", pontosObtidosProduto=" + getPontosObtidosProduto() +
            "}";
    }
}
