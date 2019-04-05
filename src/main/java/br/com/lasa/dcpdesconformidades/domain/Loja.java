package br.com.lasa.dcpdesconformidades.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Loja.
 */
@Entity
@Table(name = "loja")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Loja implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "endereco", nullable = false)
    private String endereco;

    @NotNull
    @Column(name = "cidade", nullable = false)
    private String cidade;

    @NotNull
    @Column(name = "cep", nullable = false)
    private String cep;

    @NotNull
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @NotNull
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @OneToMany(mappedBy = "loja")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Avaliacao> avaliacoes = new HashSet<>();
    
    @OneToMany(mappedBy = "loja")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnos = new HashSet<>();
    
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "loja_avaliador",
               joinColumns = @JoinColumn(name = "loja_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "avaliador_id", referencedColumnName = "id"))
    private Set<User> avaliadores = new HashSet<>();
    
    public String getNomeFormatado() {
        return String.format("L%d - %s", this.id, this.nome);
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Loja id(Long id){
        this.id = id;
        return this;
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

    public String getEndereco() {
        return endereco;
    }

    public Loja endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public Loja cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public Loja cep(String cep) {
        this.cep = cep;
        return this;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public Set<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public Loja avaliacoes(Set<Avaliacao> avaliacaos) {
        this.avaliacoes = avaliacaos;
        return this;
    }

    public Loja addAvaliacoes(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
        avaliacao.setLoja(this);
        return this;
    }

    public Loja removeAvaliacoes(Avaliacao avaliacao) {
        this.avaliacoes.remove(avaliacao);
        avaliacao.setLoja(null);
        return this;
    }

    public void setAvaliacoes(Set<Avaliacao> avaliacaos) {
        this.avaliacoes = avaliacaos;
    }

    public Set<PerdaQuebraAcumuladosAnoLoja> getPerdaQuebraAcumuladosAnos() {
        return perdaQuebraAcumuladosAnos;
    }

    public Loja perdaQuebraAcumuladosAnos(Set<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojas) {
        this.perdaQuebraAcumuladosAnos = perdaQuebraAcumuladosAnoLojas;
        return this;
    }

    public Loja addPerdaQuebraAcumuladosAno(PerdaQuebraAcumuladosAnoLoja perdaQuebraAcumuladosAnoLoja) {
        this.perdaQuebraAcumuladosAnos.add(perdaQuebraAcumuladosAnoLoja);
        perdaQuebraAcumuladosAnoLoja.setLoja(this);
        return this;
    }

    public Loja removePerdaQuebraAcumuladosAno(PerdaQuebraAcumuladosAnoLoja perdaQuebraAcumuladosAnoLoja) {
        this.perdaQuebraAcumuladosAnos.remove(perdaQuebraAcumuladosAnoLoja);
        perdaQuebraAcumuladosAnoLoja.setLoja(null);
        return this;
    }

    public void setPerdaQuebraAcumuladosAnos(Set<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLojas) {
        this.perdaQuebraAcumuladosAnos = perdaQuebraAcumuladosAnoLojas;
    }

    public Set<User> getAvaliadores() {
        return avaliadores;
    }

    public Loja avaliadores(Set<User> users) {
        this.avaliadores = users;
        return this;
    }

    public Loja addAvaliadores(User user) {
        this.avaliadores.add(user);
        user.getLojas().add(this);
        return this;
    }

    public Loja removeAvaliadores(User user) {
        this.avaliadores.remove(user);
        user.getLojas().remove(this);
        return this;
    }

    public void setAvaliadores(Set<User> users) {
        this.avaliadores = users;
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
            ", endereco='" + getEndereco() + "'" +
            ", cidade='" + getCidade() + "'" +
            ", cep='" + getCep() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            "}";
    }
}
