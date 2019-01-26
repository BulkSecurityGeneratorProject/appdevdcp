package br.com.lasa.dcpdesconformidades.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the ItemAvaliacao entity.
 */
public class ItemAvaliacaoDTO implements Serializable {

    private Long id;

    @NotNull
    private String descricao;

    @NotNull
    private Boolean anexoObrigatorio;

    @NotNull
    private Integer pontosProcedimento;

    @NotNull
    private Integer pontosPessoa;

    @NotNull
    private Integer pontosProcesso;

    @NotNull
    private Integer pontosProduto;
    
    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;
    
    @NotNull
    private Float ordemExibicao;

    private Long grupoId;

    private String grupoNome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean isAnexoObrigatorio() {
        return anexoObrigatorio;
    }

    public void setAnexoObrigatorio(Boolean anexoObrigatorio) {
        this.anexoObrigatorio = anexoObrigatorio;
    }

    public Integer getPontosProcedimento() {
        return pontosProcedimento;
    }

    public void setPontosProcedimento(Integer pontosProcedimento) {
        this.pontosProcedimento = pontosProcedimento;
    }

    public Integer getPontosPessoa() {
        return pontosPessoa;
    }

    public void setPontosPessoa(Integer pontosPessoa) {
        this.pontosPessoa = pontosPessoa;
    }

    public Integer getPontosProcesso() {
        return pontosProcesso;
    }

    public void setPontosProcesso(Integer pontosProcesso) {
        this.pontosProcesso = pontosProcesso;
    }

    public Integer getPontosProduto() {
        return pontosProduto;
    }

    public void setPontosProduto(Integer pontosProduto) {
        this.pontosProduto = pontosProduto;
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
    
    public Float getOrdemExibicao() {
      return ordemExibicao;
    }

    public void setOrdemExibicao(Float ordemExibicao) {
      this.ordemExibicao = ordemExibicao;
    }

    public Long getGrupoId() {
      return grupoId;
    }

    public void setGrupoId(Long grupoId) {
      this.grupoId = grupoId;
    }

    public String getGrupoNome() {
      return grupoNome;
    }

    public void setGrupoNome(String grupoNome) {
      this.grupoNome = grupoNome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ItemAvaliacaoDTO itemAvaliacaoDTO = (ItemAvaliacaoDTO) o;
        if (itemAvaliacaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemAvaliacaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
      return "ItemAvaliacaoDTO{" +
          "id=" + getId() +
          ", descricao='" + getDescricao() + "'" +
          ", anexoObrigatorio='" + isAnexoObrigatorio() + "'" +
          ", pontosProcedimento=" + getPontosProcedimento() +
          ", pontosPessoa=" + getPontosPessoa() +
          ", pontosProcesso=" + getPontosProcesso() +
          ", pontosProduto=" + getPontosProduto() +
          ", ordemExibicao=" + getOrdemExibicao() +
          ", grupo=" + getGrupoId() +
          ", grupo='" + getGrupoNome() + "'" +
          "}";
    }

}
