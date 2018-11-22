package br.com.lasa.dcpdesconformidades.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Loja entity.
 */
public class LojaDTO implements Serializable {

    private Long id;

    @NotNull
    private String codigo;

    @NotNull
    private String nome;

    @NotNull
    private String nomeResponsavel;

    @NotNull
    private Integer prontuarioResponsavel;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    private Set<UserDTO> avaliadores = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public Integer getProntuarioResponsavel() {
        return prontuarioResponsavel;
    }

    public void setProntuarioResponsavel(Integer prontuarioResponsavel) {
        this.prontuarioResponsavel = prontuarioResponsavel;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Set<UserDTO> getAvaliadores() {
        return avaliadores;
    }

    public void setAvaliadores(Set<UserDTO> avaliadores) {
        this.avaliadores = avaliadores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LojaDTO lojaDTO = (LojaDTO) o;
        if (lojaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lojaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LojaDTO{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", nome='" + getNome() + "'" +
            ", nomeResponsavel='" + getNomeResponsavel() + "'" +
            ", prontuarioResponsavel=" + getProntuarioResponsavel() +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            "}";
    }
}
