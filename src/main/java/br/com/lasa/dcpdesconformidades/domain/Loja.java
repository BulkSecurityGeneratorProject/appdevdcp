package br.com.lasa.dcpdesconformidades.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Loja.
 */
@Entity
@Table(name = "loja")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Loja implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "nome_responsavel", nullable = false)
    private String nomeResponsavel;

    @NotNull
    @Column(name = "prontuario_responsavel", nullable = false)
    private Integer prontuarioResponsavel;

    @NotNull
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @NotNull
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "loja_avaliador",
               joinColumns = @JoinColumn(name = "lojas_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "avaliadors_id", referencedColumnName = "id"))
    private Set<Avaliador> avaliadors = new HashSet<>();

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

    public Loja nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public Loja nomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
        return this;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public Integer getProntuarioResponsavel() {
        return prontuarioResponsavel;
    }

    public Loja prontuarioResponsavel(Integer prontuarioResponsavel) {
        this.prontuarioResponsavel = prontuarioResponsavel;
        return this;
    }

    public void setProntuarioResponsavel(Integer prontuarioResponsavel) {
        this.prontuarioResponsavel = prontuarioResponsavel;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Loja latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Loja longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Set<Avaliador> getAvaliadors() {
        return avaliadors;
    }

    public Loja avaliadors(Set<Avaliador> avaliadors) {
        this.avaliadors = avaliadors;
        return this;
    }

    public Loja addAvaliador(Avaliador avaliador) {
        this.avaliadors.add(avaliador);
        avaliador.getLojas().add(this);
        return this;
    }

    public Loja removeAvaliador(Avaliador avaliador) {
        this.avaliadors.remove(avaliador);
        avaliador.getLojas().remove(this);
        return this;
    }

    public void setAvaliadors(Set<Avaliador> avaliadors) {
        this.avaliadors = avaliadors;
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
        Loja loja = (Loja) o;
        if (loja.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), loja.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Loja{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", nomeResponsavel='" + getNomeResponsavel() + "'" +
            ", prontuarioResponsavel=" + getProntuarioResponsavel() +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            "}";
    }
}
