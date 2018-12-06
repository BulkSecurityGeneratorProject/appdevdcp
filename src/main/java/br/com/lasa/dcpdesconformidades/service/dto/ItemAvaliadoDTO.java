package br.com.lasa.dcpdesconformidades.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusItemAvaliado;

/**
 * A DTO for the ItemAvaliado entity.
 */
public class ItemAvaliadoDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant respondidoEm;

    private Instant ultimaAtualizacaoEm;

    @NotNull
    private StatusItemAvaliado status;

    private String observacoes;

    @NotNull
    private Double latitudeLocalResposta;

    @NotNull
    private Double longitudeLocalResposta;

    @NotNull
    private Integer pontosProcedimento;

    @NotNull
    private Integer pontosPessoa;

    @NotNull
    private Integer pontosProcesso;

    @NotNull
    private Integer pontosProduto;

    private Integer pontosObtidosProcedimento;

    private Integer pontosObtidosPessoa;

    private Integer pontosObtidosProcesso;

    private Integer pontosObtidosProduto;

    private Long itemAvaliacaoId;

    private String itemAvaliacaoDescricao;

    private Long avaliacaoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getRespondidoEm() {
        return respondidoEm;
    }

    public void setRespondidoEm(Instant respondidoEm) {
        this.respondidoEm = respondidoEm;
    }

    public Instant getUltimaAtualizacaoEm() {
        return ultimaAtualizacaoEm;
    }

    public void setUltimaAtualizacaoEm(Instant ultimaAtualizacaoEm) {
        this.ultimaAtualizacaoEm = ultimaAtualizacaoEm;
    }

    public StatusItemAvaliado getStatus() {
        return status;
    }

    public void setStatus(StatusItemAvaliado status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Double getLatitudeLocalResposta() {
        return latitudeLocalResposta;
    }

    public void setLatitudeLocalResposta(Double latitudeLocalResposta) {
        this.latitudeLocalResposta = latitudeLocalResposta;
    }

    public Double getLongitudeLocalResposta() {
        return longitudeLocalResposta;
    }

    public void setLongitudeLocalResposta(Double longitudeLocalResposta) {
        this.longitudeLocalResposta = longitudeLocalResposta;
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

    public Integer getPontosObtidosProcedimento() {
        return pontosObtidosProcedimento;
    }

    public void setPontosObtidosProcedimento(Integer pontosObtidosProcedimento) {
        this.pontosObtidosProcedimento = pontosObtidosProcedimento;
    }

    public Integer getPontosObtidosPessoa() {
        return pontosObtidosPessoa;
    }

    public void setPontosObtidosPessoa(Integer pontosObtidosPessoa) {
        this.pontosObtidosPessoa = pontosObtidosPessoa;
    }

    public Integer getPontosObtidosProcesso() {
        return pontosObtidosProcesso;
    }

    public void setPontosObtidosProcesso(Integer pontosObtidosProcesso) {
        this.pontosObtidosProcesso = pontosObtidosProcesso;
    }

    public Integer getPontosObtidosProduto() {
        return pontosObtidosProduto;
    }

    public void setPontosObtidosProduto(Integer pontosObtidosProduto) {
        this.pontosObtidosProduto = pontosObtidosProduto;
    }

    public Long getItemAvaliacaoId() {
        return itemAvaliacaoId;
    }

    public void setItemAvaliacaoId(Long itemAvaliacaoId) {
        this.itemAvaliacaoId = itemAvaliacaoId;
    }

    public String getItemAvaliacaoDescricao() {
        return itemAvaliacaoDescricao;
    }

    public void setItemAvaliacaoDescricao(String itemAvaliacaoDescricao) {
        this.itemAvaliacaoDescricao = itemAvaliacaoDescricao;
    }

    public Long getAvaliacaoId() {
        return avaliacaoId;
    }

    public void setAvaliacaoId(Long avaliacaoId) {
        this.avaliacaoId = avaliacaoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ItemAvaliadoDTO itemAvaliadoDTO = (ItemAvaliadoDTO) o;
        if (itemAvaliadoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemAvaliadoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemAvaliadoDTO{" +
            "id=" + getId() +
            ", respondidoEm='" + getRespondidoEm() + "'" +
            ", ultimaAtualizacaoEm='" + getUltimaAtualizacaoEm() + "'" +
            ", status='" + getStatus() + "'" +
            ", observacoes='" + getObservacoes() + "'" +
            ", latitudeLocalResposta=" + getLatitudeLocalResposta() +
            ", longitudeLocalResposta=" + getLongitudeLocalResposta() +
            ", pontosProcedimento=" + getPontosProcedimento() +
            ", pontosPessoa=" + getPontosPessoa() +
            ", pontosProcesso=" + getPontosProcesso() +
            ", pontosProduto=" + getPontosProduto() +
            ", pontosObtidosProcedimento=" + getPontosObtidosProcedimento() +
            ", pontosObtidosPessoa=" + getPontosObtidosPessoa() +
            ", pontosObtidosProcesso=" + getPontosObtidosProcesso() +
            ", pontosObtidosProduto=" + getPontosObtidosProduto() +
            ", itemAvaliacao=" + getItemAvaliacaoId() +
            ", itemAvaliacao='" + getItemAvaliacaoDescricao() + "'" +
            ", avaliacao=" + getAvaliacaoId() +
            "}";
    }
}
