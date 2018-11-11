package br.com.lasa.dcpdesconformidades.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ItemAvaliacao.
 */
@Entity
@Table(name = "item_avaliacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemAvaliacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @NotNull
    @Column(name = "anexo_obrigatorio", nullable = false)
    private Boolean anexoObrigatorio;

    @NotNull
    @Column(name = "criado_em", nullable = false)
    private Instant criadoEm;

    @OneToMany(mappedBy = "itemAvaliacao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemAvaliado> itensAvaliados = new HashSet<>();
    @ManyToMany(mappedBy = "itens")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<GrupoItens> grupos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public ItemAvaliacao descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean isAnexoObrigatorio() {
        return anexoObrigatorio;
    }

    public ItemAvaliacao anexoObrigatorio(Boolean anexoObrigatorio) {
        this.anexoObrigatorio = anexoObrigatorio;
        return this;
    }

    public void setAnexoObrigatorio(Boolean anexoObrigatorio) {
        this.anexoObrigatorio = anexoObrigatorio;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public ItemAvaliacao criadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
        return this;
    }

    public void setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Set<ItemAvaliado> getItensAvaliados() {
        return itensAvaliados;
    }

    public ItemAvaliacao itensAvaliados(Set<ItemAvaliado> itemAvaliados) {
        this.itensAvaliados = itemAvaliados;
        return this;
    }

    public ItemAvaliacao addItensAvaliados(ItemAvaliado itemAvaliado) {
        this.itensAvaliados.add(itemAvaliado);
        itemAvaliado.setItemAvaliacao(this);
        return this;
    }

    public ItemAvaliacao removeItensAvaliados(ItemAvaliado itemAvaliado) {
        this.itensAvaliados.remove(itemAvaliado);
        itemAvaliado.setItemAvaliacao(null);
        return this;
    }

    public void setItensAvaliados(Set<ItemAvaliado> itemAvaliados) {
        this.itensAvaliados = itemAvaliados;
    }

    public Set<GrupoItens> getGrupos() {
        return grupos;
    }

    public ItemAvaliacao grupos(Set<GrupoItens> grupoItens) {
        this.grupos = grupoItens;
        return this;
    }

    public ItemAvaliacao addGrupo(GrupoItens grupoItens) {
        this.grupos.add(grupoItens);
        grupoItens.getItens().add(this);
        return this;
    }

    public ItemAvaliacao removeGrupo(GrupoItens grupoItens) {
        this.grupos.remove(grupoItens);
        grupoItens.getItens().remove(this);
        return this;
    }

    public void setGrupos(Set<GrupoItens> grupoItens) {
        this.grupos = grupoItens;
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
        ItemAvaliacao itemAvaliacao = (ItemAvaliacao) o;
        if (itemAvaliacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemAvaliacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemAvaliacao{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", anexoObrigatorio='" + isAnexoObrigatorio() + "'" +
            ", criadoEm='" + getCriadoEm() + "'" +
            "}";
    }
}
