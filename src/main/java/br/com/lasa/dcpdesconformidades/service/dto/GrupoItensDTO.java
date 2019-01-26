package br.com.lasa.dcpdesconformidades.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the GrupoItens entity.
 */
public class GrupoItensDTO implements Serializable {

    private Long id;

    @NotNull
    private String nome;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private Set<ItemAvaliacaoDTO> itens = new HashSet<>();
    
    private Long questionarioId;

    private String questionarioNome;

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

    public Set<ItemAvaliacaoDTO> getItens() {
        return itens;
    }

    public void setItens(Set<ItemAvaliacaoDTO> itemAvaliacaos) {
        this.itens = itemAvaliacaos;
    }
    
    public String getCreatedBy() {
      return createdBy;
    }

    public void setCreatedBy(String createdBy) {
      this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
      return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
      this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
      return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
      this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
      return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
      this.lastModifiedDate = lastModifiedDate;
    }
    
    public Long getQuestionarioId() {
      return questionarioId;
    }

    public void setQuestionarioId(Long questionarioId) {
      this.questionarioId = questionarioId;
    }

    public String getQuestionarioNome() {
      return questionarioNome;
    }

    public void setQuestionarioNome(String questionarioNome) {
      this.questionarioNome = questionarioNome;
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
          ", questionario=" + getQuestionarioId() +
          ", questionario='" + getQuestionarioNome() + "'" +
          "}";
    }
    
    

}
