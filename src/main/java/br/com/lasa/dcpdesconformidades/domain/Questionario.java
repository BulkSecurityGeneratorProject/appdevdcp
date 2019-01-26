package br.com.lasa.dcpdesconformidades.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Questionario.
 */
@Entity
@Table(name = "questionario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Questionario extends AbstractAuditingEntity implements Serializable {
  
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @NotNull
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "versao")
    private Integer versao;

    @OneToMany(mappedBy = "questionario")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Avaliacao> avaliacoesRealizadas = new HashSet<>();
    
    @OneToMany(mappedBy = "questionario")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OrderBy("ordemExibicao ASC")
    private Set<GrupoItens> grupos = new LinkedHashSet<>();
    
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

    public Questionario nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Questionario descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public Questionario ativo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getVersao() {
        return versao;
    }

    public Questionario versao(Integer versao) {
        this.versao = versao;
        return this;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }

    public Set<Avaliacao> getAvaliacoesRealizadas() {
        return avaliacoesRealizadas;
    }

    public Questionario avaliacoesRealizadas(Set<Avaliacao> avaliacaos) {
        this.avaliacoesRealizadas = avaliacaos;
        return this;
    }

    public Questionario addAvaliacoesRealizadas(Avaliacao avaliacao) {
        this.avaliacoesRealizadas.add(avaliacao);
        avaliacao.setQuestionario(this);
        return this;
    }

    public Questionario removeAvaliacoesRealizadas(Avaliacao avaliacao) {
        this.avaliacoesRealizadas.remove(avaliacao);
        avaliacao.setQuestionario(null);
        return this;
    }

    public void setAvaliacoesRealizadas(Set<Avaliacao> avaliacaos) {
        this.avaliacoesRealizadas = avaliacaos;
    }

    public Set<GrupoItens> getGrupos() {
        return grupos;
    }

    public Questionario grupos(Set<GrupoItens> grupoItens) {
        this.grupos = grupoItens;
        return this;
    }

    public Questionario addGrupos(GrupoItens grupoItens) {
        this.grupos.add(grupoItens);
        grupoItens.setQuestionario(this);
        return this;
    }

    public Questionario removeGrupos(GrupoItens grupoItens) {
        this.grupos.remove(grupoItens);
        grupoItens.setQuestionario(null);
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
        Questionario questionario = (Questionario) o;
        if (questionario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), questionario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Questionario{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", ativo='" + isAtivo() + "'" +
            ", versao=" + getVersao() +
            "}";
    }
}
