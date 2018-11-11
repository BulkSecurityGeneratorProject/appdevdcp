package br.com.lasa.dcpdesconformidades.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the GrupoItens entity.
 */
public class GrupoItensDTO implements Serializable {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private Instant criadoEm;

    private Set<ItemAvaliacaoDTO> itens = new HashSet<>();

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

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Set<ItemAvaliacaoDTO> getItens() {
        return itens;
    }

    public void setItens(Set<ItemAvaliacaoDTO> itemAvaliacaos) {
        this.itens = itemAvaliacaos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GrupoItensDTO grupoItensDTO = (GrupoItensDTO) o;
        if (grupoItensDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), grupoItensDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GrupoItensDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", criadoEm='" + getCriadoEm() + "'" +
            "}";
    }
}
