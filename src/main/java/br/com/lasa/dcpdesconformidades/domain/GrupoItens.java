package br.com.lasa.dcpdesconformidades.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "grupo_itens_itens",
               joinColumns = @JoinColumn(name = "grupo_itens_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "itens_id", referencedColumnName = "id"))
    private Set<ItemAvaliacao> itens = new HashSet<>();

    @ManyToMany(mappedBy = "grupos")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Questionario> questionarios = new HashSet<>();

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

    public Set<ItemAvaliacao> getItens() {
        return itens;
    }

    public GrupoItens itens(Set<ItemAvaliacao> itemAvaliacaos) {
        this.itens = itemAvaliacaos;
        return this;
    }

    public GrupoItens addItens(ItemAvaliacao itemAvaliacao) {
        this.itens.add(itemAvaliacao);
        itemAvaliacao.getGrupos().add(this);
        return this;
    }

    public GrupoItens removeItens(ItemAvaliacao itemAvaliacao) {
        this.itens.remove(itemAvaliacao);
        itemAvaliacao.getGrupos().remove(this);
        return this;
    }

    public void setItens(Set<ItemAvaliacao> itemAvaliacaos) {
        this.itens = itemAvaliacaos;
    }

    public Set<Questionario> getQuestionarios() {
        return questionarios;
    }

    public GrupoItens questionarios(Set<Questionario> questionarios) {
        this.questionarios = questionarios;
        return this;
    }

    public GrupoItens addQuestionario(Questionario questionario) {
        this.questionarios.add(questionario);
        questionario.getGrupos().add(this);
        return this;
    }

    public GrupoItens removeQuestionario(Questionario questionario) {
        this.questionarios.remove(questionario);
        questionario.getGrupos().remove(this);
        return this;
    }

    public void setQuestionarios(Set<Questionario> questionarios) {
        this.questionarios = questionarios;
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
            "}";
    }
}
