package br.com.lasa.dcpdesconformidades.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.lasa.dcpdesconformidades.config.Constants;
import br.com.lasa.dcpdesconformidades.domain.enumeration.Authority;

/**
 * A user.
 */
@Entity
@Table(name = "usuario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;

    @Size(max = 255)
    @Column(name = "nome", length = 50)
    private String name;

    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    private String email;

    @NotNull
    @Column(name = "ativo", nullable = false)
    private boolean activated = false;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass=Authority.class, fetch = FetchType.EAGER)
    @CollectionTable(name="usuario_perfil", joinColumns = {@JoinColumn(name = "usuario_id", referencedColumnName = "id")})
    @Column(name="nome_perfil")
    @BatchSize(size = 20)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Authority> authorities = new HashSet<>();

    @Column(name = "prontuario")
    private Integer prontuario;

    @OneToMany(mappedBy = "avaliador")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Avaliacao> avaliacoes = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "loja_avaliador", joinColumns = @JoinColumn(name = "avaliador_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "loja_id", referencedColumnName = "id"))
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnore
    private Set<Loja> lojas = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    // Lowercase the login before saving it in database
    public void setLogin(String login) {
        this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Integer getProntuario() {
        return prontuario;
    }

    public void setProntuario(Integer prontuario) {
        this.prontuario = prontuario;
    }

    public Set<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(Set<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public Set<Loja> getLojas() {
        return lojas;
    }

    public void setLojas(Set<Loja> lojas) {
        this.lojas = lojas;
    }

    public boolean hasLoja(Long idLoja){
        return lojas.stream().filter(loja -> loja.getId()==idLoja).count() > 0;
    }

    public Loja getLoja(Long idLoja){
        return lojas.stream().filter(loja -> loja.getId()==idLoja).findAny().get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return !(user.getId() == null || getId() == null) && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "User{" + "login='" + login + '\'' + ", name='" + name + '\'' + ", email='" + email + '\''
                + ", activated='" + activated + '\'' + "}";
    }
}
