package br.com.lasa.dcpdesconformidades.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Avaliador entity.
 */
public class AvaliadorDTO implements Serializable {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String login;

    @NotNull
    private Integer prontuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getProntuario() {
        return prontuario;
    }

    public void setProntuario(Integer prontuario) {
        this.prontuario = prontuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AvaliadorDTO avaliadorDTO = (AvaliadorDTO) o;
        if (avaliadorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), avaliadorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AvaliadorDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", login='" + getLogin() + "'" +
            ", prontuario=" + getProntuario() +
            "}";
    }
}
