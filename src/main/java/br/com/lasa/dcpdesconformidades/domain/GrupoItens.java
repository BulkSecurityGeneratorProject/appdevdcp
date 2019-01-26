package br.com.lasa.dcpdesconformidades.domain;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A GrupoItens.
 */
@Entity
@Table(name = "grupo_itens")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GrupoItens extends AbstractAuditingEntity implements Serializable {
  
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @NotNull
    @Column(name = "ordem_exibicao", nullable = false)
    private Float ordemExibicao;

    @OneToMany(mappedBy = "grupo")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OrderBy("ordemExibicao ASC")
    private Set<ItemAvaliacao> itens = new LinkedHashSet<>();
    
    @ManyToOne
    @JsonIgnoreProperties("grupos")
    private Questionario questionario;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public GrupoItens nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Float getOrdemExibicao() {
      return ordemExibicao;
    }

    public GrupoItens ordemExibicao(Float ordemExibicao) {
        this.ordemExibicao = ordemExibicao;
        return this;
    }
  
    public void setOrdemExibicao(Float ordemExibicao) {
        this.ordemExibicao = ordemExibicao;
    }

    public Set<ItemAvaliacao> getItens() {
        return itens;
    }

    public GrupoItens itens(Set<ItemAvaliacao> itemAvaliacaos) {
        this.itens = itemAvaliacaos;
        return this;
    }

    public GrupoItens addItens(ItemAvaliacao itemAvaliacao) {
        this.itens.add(itemAvaliacao);
        itemAvaliacao.setGrupo(this);
        return this;
    }

    public GrupoItens removeItens(ItemAvaliacao itemAvaliacao) {
        this.itens.remove(itemAvaliacao);
        itemAvaliacao.setGrupo(null);
        return this;
    }

    public void setItens(Set<ItemAvaliacao> itemAvaliacaos) {
        this.itens = itemAvaliacaos;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public GrupoItens questionario(Questionario questionario) {
        this.questionario = questionario;
        return this;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
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
        GrupoItens grupoItens = (GrupoItens) o;
        if (grupoItens.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), grupoItens.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GrupoItens{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", ordemExibicao=" + getOrdemExibicao() +
            "}";
    }
}
