package br.com.lasa.dcpdesconformidades.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

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
    private Instant criadoEm;

    @NotNull
    private Integer pontosProcedimento;

    @NotNull
    private Integer pontosPessoa;

    @NotNull
    private Integer pontosProcesso;

    @NotNull
    private Integer pontosProduto;

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

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
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
            ", criadoEm='" + getCriadoEm() + "'" +
            ", pontosProcedimento=" + getPontosProcedimento() +
            ", pontosPessoa=" + getPontosPessoa() +
            ", pontosProcesso=" + getPontosProcesso() +
            ", pontosProduto=" + getPontosProduto() +
            "}";
    }
}
