package br.com.lasa.dcpdesconformidades.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import br.com.lasa.dcpdesconformidades.domain.enumeration.TipoAnexoItem;

/**
 * A DTO for the AnexoItem entity.
 */
public class AnexoItemDTO implements Serializable {

    private Long id;

    @NotNull
    private TipoAnexoItem tipo;

    @NotNull
    private String caminhoArquivo;

    private Long itemAvaliadoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoAnexoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoAnexoItem tipo) {
        this.tipo = tipo;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public Long getItemAvaliadoId() {
        return itemAvaliadoId;
    }

    public void setItemAvaliadoId(Long itemAvaliadoId) {
        this.itemAvaliadoId = itemAvaliadoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AnexoItemDTO anexoItemDTO = (AnexoItemDTO) o;
        if (anexoItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), anexoItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AnexoItemDTO{" +
            "id=" + getId() +
            ", tipo='" + getTipo() + "'" +
            ", caminhoArquivo='" + getCaminhoArquivo() + "'" +
            ", itemAvaliado=" + getItemAvaliadoId() +
            "}";
    }
}
