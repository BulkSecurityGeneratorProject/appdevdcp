package br.com.lasa.dcpdesconformidades.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the Questionario entity.
 */
public class QuestionarioDTO implements Serializable {

    private Long id;

    @NotNull
    private String nome;

    private String descricao;

    @NotNull
    private Boolean ativo;
    
    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private Set<GrupoItensDTO> grupos = new HashSet<>();

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Set<GrupoItensDTO> getGrupos() {
        return grupos;
    }

    public void setGrupos(Set<GrupoItensDTO> grupoItens) {
        this.grupos = grupoItens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuestionarioDTO questionarioDTO = (QuestionarioDTO) o;
        if (questionarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), questionarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
      return "QuestionarioDTO [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", ativo=" + ativo + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", lastModifiedBy="
          + lastModifiedBy + ", lastModifiedDate=" + lastModifiedDate + ", grupos=" + grupos + "]";
    }

}
