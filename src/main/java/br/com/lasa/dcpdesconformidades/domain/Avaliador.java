package br.com.lasa.dcpdesconformidades.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Avaliador.
 */
@Entity
@Table(name = "avaliador")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Avaliador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "login", nullable = false)
    private String login;

    @NotNull
    @Column(name = "prontuario", nullable = false)
    private Integer prontuario;

    @OneToMany(mappedBy = "avaliador")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Avaliacao> avaliacaos = new HashSet<>();
    @ManyToMany(mappedBy = "avaliadors")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Loja> lojas = new HashSet<>();

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

    public Avaliador nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public Avaliador login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getProntuario() {
        return prontuario;
    }

    public Avaliador prontuario(Integer prontuario) {
        this.prontuario = prontuario;
        return this;
    }

    public void setProntuario(Integer prontuario) {
        this.prontuario = prontuario;
    }

    public Set<Avaliacao> getAvaliacaos() {
        return avaliacaos;
    }

    public Avaliador avaliacaos(Set<Avaliacao> avaliacaos) {
        this.avaliacaos = avaliacaos;
        return this;
    }

    public Avaliador addAvaliacao(Avaliacao avaliacao) {
        this.avaliacaos.add(avaliacao);
        avaliacao.setAvaliador(this);
        return this;
    }

    public Avaliador removeAvaliacao(Avaliacao avaliacao) {
        this.avaliacaos.remove(avaliacao);
        avaliacao.setAvaliador(null);
        return this;
    }

    public void setAvaliacaos(Set<Avaliacao> avaliacaos) {
        this.avaliacaos = avaliacaos;
    }

    public Set<Loja> getLojas() {
        return lojas;
    }

    public Avaliador lojas(Set<Loja> lojas) {
        this.lojas = lojas;
        return this;
    }

    public Avaliador addLoja(Loja loja) {
        this.lojas.add(loja);
        loja.getAvaliadors().add(this);
        return this;
    }

    public Avaliador removeLoja(Loja loja) {
        this.lojas.remove(loja);
        loja.getAvaliadors().remove(this);
        return this;
    }

    public void setLojas(Set<Loja> lojas) {
        this.lojas = lojas;
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
        Avaliador avaliador = (Avaliador) o;
        if (avaliador.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), avaliador.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Avaliador{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", login='" + getLogin() + "'" +
            ", prontuario=" + getProntuario() +
            "}";
    }
}
