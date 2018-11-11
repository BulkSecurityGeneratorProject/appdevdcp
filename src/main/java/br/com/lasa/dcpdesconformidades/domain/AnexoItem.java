package br.com.lasa.dcpdesconformidades.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import br.com.lasa.dcpdesconformidades.domain.enumeration.TipoAnexoItem;

/**
 * A AnexoItem.
 */
@Entity
@Table(name = "anexo_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AnexoItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoAnexoItem tipo;

    @NotNull
    @Column(name = "caminho_arquivo", nullable = false)
    private String caminhoArquivo;

    @ManyToOne
    @JsonIgnoreProperties("anexos")
    private ItemAvaliado itemAvaliado;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoAnexoItem getTipo() {
        return tipo;
    }

    public AnexoItem tipo(TipoAnexoItem tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(TipoAnexoItem tipo) {
        this.tipo = tipo;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public AnexoItem caminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
        return this;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public ItemAvaliado getItemAvaliado() {
        return itemAvaliado;
    }

    public AnexoItem itemAvaliado(ItemAvaliado itemAvaliado) {
        this.itemAvaliado = itemAvaliado;
        return this;
    }

    public void setItemAvaliado(ItemAvaliado itemAvaliado) {
        this.itemAvaliado = itemAvaliado;
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
        AnexoItem anexoItem = (AnexoItem) o;
        if (anexoItem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), anexoItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AnexoItem{" +
            "id=" + getId() +
            ", tipo='" + getTipo() + "'" +
            ", caminhoArquivo='" + getCaminhoArquivo() + "'" +
            "}";
    }
}
