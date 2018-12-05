package br.com.lasa.dcpdesconformidades.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the Loja entity.
 */
public class LojaParaAvaliacaoDTO implements Serializable {

    private Long id;

    @NotNull
    private String codigo;

    @NotNull
    private String nome;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private Instant submissaoUltimaAvaliacao;

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

    public Instant getSubmissaoUltimaAvaliacao() {
        return submissaoUltimaAvaliacao;
    }

    public void setSubmissaoUltimaAvaliacao(Instant submissaoUltimaAvaliacao) {
        this.submissaoUltimaAvaliacao = submissaoUltimaAvaliacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LojaParaAvaliacaoDTO lojaDTO = (LojaParaAvaliacaoDTO) o;
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
        return "LojaParaAvaliacaoDTO [id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", latitude="
                + latitude + ", longitude=" + longitude + ", submissaoUltimaAvaliacao=" + submissaoUltimaAvaliacao + "]";
    }

}
