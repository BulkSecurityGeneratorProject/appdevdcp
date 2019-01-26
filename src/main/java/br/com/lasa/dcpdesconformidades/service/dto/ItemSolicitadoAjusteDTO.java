package br.com.lasa.dcpdesconformidades.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ItemSolicitadoAjuste entity.
 */
public class ItemSolicitadoAjusteDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant respondidoEm;

    private Instant ultimaAtualizacaoEm;

    @NotNull
    private Integer codigoDepartamento;

    @NotNull
    private Integer codigoSap;

    @NotNull
    private String descricaoItem;

    @NotNull
    private Integer saldoSap0001;

    @NotNull
    private Integer saldoFisico0001;

    private Integer saldoSap9000;

    private Integer saldoFisico9000;

    private String motivoDivergencia;

    private String acaoCorretivaOuPreventiva;

    private String responsavel;

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

    public Integer getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(Integer codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public Integer getCodigoSap() {
        return codigoSap;
    }

    public void setCodigoSap(Integer codigoSap) {
        this.codigoSap = codigoSap;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public Integer getSaldoSap0001() {
        return saldoSap0001;
    }

    public void setSaldoSap0001(Integer saldoSap0001) {
        this.saldoSap0001 = saldoSap0001;
    }

    public Integer getSaldoFisico0001() {
        return saldoFisico0001;
    }

    public void setSaldoFisico0001(Integer saldoFisico0001) {
        this.saldoFisico0001 = saldoFisico0001;
    }

    public Integer getSaldoSap9000() {
        return saldoSap9000;
    }

    public void setSaldoSap9000(Integer saldoSap9000) {
        this.saldoSap9000 = saldoSap9000;
    }

    public Integer getSaldoFisico9000() {
        return saldoFisico9000;
    }

    public void setSaldoFisico9000(Integer saldoFisico9000) {
        this.saldoFisico9000 = saldoFisico9000;
    }

    public String getMotivoDivergencia() {
        return motivoDivergencia;
    }

    public void setMotivoDivergencia(String motivoDivergencia) {
        this.motivoDivergencia = motivoDivergencia;
    }

    public String getAcaoCorretivaOuPreventiva() {
        return acaoCorretivaOuPreventiva;
    }

    public void setAcaoCorretivaOuPreventiva(String acaoCorretivaOuPreventiva) {
        this.acaoCorretivaOuPreventiva = acaoCorretivaOuPreventiva;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
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

        ItemSolicitadoAjusteDTO itemSolicitadoAjusteDTO = (ItemSolicitadoAjusteDTO) o;
        if (itemSolicitadoAjusteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemSolicitadoAjusteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemSolicitadoAjusteDTO{" +
            "id=" + getId() +
            ", respondidoEm='" + getRespondidoEm() + "'" +
            ", ultimaAtualizacaoEm='" + getUltimaAtualizacaoEm() + "'" +
            ", codigoDepartamento=" + getCodigoDepartamento() +
            ", codigoSap=" + getCodigoSap() +
            ", descricaoItem='" + getDescricaoItem() + "'" +
            ", saldoSap0001=" + getSaldoSap0001() +
            ", saldoFisico0001=" + getSaldoFisico0001() +
            ", saldoSap9000=" + getSaldoSap9000() +
            ", saldoFisico9000=" + getSaldoFisico9000() +
            ", motivoDivergencia='" + getMotivoDivergencia() + "'" +
            ", acaoCorretivaOuPreventiva='" + getAcaoCorretivaOuPreventiva() + "'" +
            ", responsavel='" + getResponsavel() + "'" +
            ", avaliacao=" + getAvaliacaoId() +
            "}";
    }
}
