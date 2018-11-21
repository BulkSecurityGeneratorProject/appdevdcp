package br.com.lasa.dcpdesconformidades.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusAvaliacao;
import br.com.lasa.dcpdesconformidades.domain.enumeration.CriticidadePainel;
import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;
import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;
import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;
import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;
import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;

/**
 * A DTO for the Avaliacao entity.
 */
public class AvaliacaoDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant dataInicio;

    @NotNull
    private Double latitudeInicioAvaliacao;

    @NotNull
    private Double longitudeInicioAvaliacao;

    private String nomeResponsavelLoja;

    private Integer prontuarioResponsavelLoja;

    private Instant submetidoEm;

    private Double latitudeSubmissaoAvaliacao;

    private Double longitudeSubmissaoAvaliacao;

    private String observacaoSubmissaoEnviadaForaDaLoja;

    @NotNull
    private StatusAvaliacao status;

    private CriticidadePainel criticidadePainel;

    private NivelEficiencia nivelEficienciaGeral;

    private NivelEficiencia nivelEficienciaProcedimento;

    private NivelEficiencia nivelEficienciaPessoa;

    private NivelEficiencia nivelEficienciaProcesso;

    private NivelEficiencia nivelEficienciaProduto;

    private Instant canceladoEm;

    private String motivoCancelamento;

    private Long questionarioId;

    private String questionarioNome;

    private Long avaliadorId;

    private String avaliadorNome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Instant dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Double getLatitudeInicioAvaliacao() {
        return latitudeInicioAvaliacao;
    }

    public void setLatitudeInicioAvaliacao(Double latitudeInicioAvaliacao) {
        this.latitudeInicioAvaliacao = latitudeInicioAvaliacao;
    }

    public Double getLongitudeInicioAvaliacao() {
        return longitudeInicioAvaliacao;
    }

    public void setLongitudeInicioAvaliacao(Double longitudeInicioAvaliacao) {
        this.longitudeInicioAvaliacao = longitudeInicioAvaliacao;
    }

    public String getNomeResponsavelLoja() {
        return nomeResponsavelLoja;
    }

    public void setNomeResponsavelLoja(String nomeResponsavelLoja) {
        this.nomeResponsavelLoja = nomeResponsavelLoja;
    }

    public Integer getProntuarioResponsavelLoja() {
        return prontuarioResponsavelLoja;
    }

    public void setProntuarioResponsavelLoja(Integer prontuarioResponsavelLoja) {
        this.prontuarioResponsavelLoja = prontuarioResponsavelLoja;
    }

    public Instant getSubmetidoEm() {
        return submetidoEm;
    }

    public void setSubmetidoEm(Instant submetidoEm) {
        this.submetidoEm = submetidoEm;
    }

    public Double getLatitudeSubmissaoAvaliacao() {
        return latitudeSubmissaoAvaliacao;
    }

    public void setLatitudeSubmissaoAvaliacao(Double latitudeSubmissaoAvaliacao) {
        this.latitudeSubmissaoAvaliacao = latitudeSubmissaoAvaliacao;
    }

    public Double getLongitudeSubmissaoAvaliacao() {
        return longitudeSubmissaoAvaliacao;
    }

    public void setLongitudeSubmissaoAvaliacao(Double longitudeSubmissaoAvaliacao) {
        this.longitudeSubmissaoAvaliacao = longitudeSubmissaoAvaliacao;
    }

    public String getObservacaoSubmissaoEnviadaForaDaLoja() {
        return observacaoSubmissaoEnviadaForaDaLoja;
    }

    public void setObservacaoSubmissaoEnviadaForaDaLoja(String observacaoSubmissaoEnviadaForaDaLoja) {
        this.observacaoSubmissaoEnviadaForaDaLoja = observacaoSubmissaoEnviadaForaDaLoja;
    }

    public StatusAvaliacao getStatus() {
        return status;
    }

    public void setStatus(StatusAvaliacao status) {
        this.status = status;
    }

    public CriticidadePainel getCriticidadePainel() {
        return criticidadePainel;
    }

    public void setCriticidadePainel(CriticidadePainel criticidadePainel) {
        this.criticidadePainel = criticidadePainel;
    }

    public NivelEficiencia getNivelEficienciaGeral() {
        return nivelEficienciaGeral;
    }

    public void setNivelEficienciaGeral(NivelEficiencia nivelEficienciaGeral) {
        this.nivelEficienciaGeral = nivelEficienciaGeral;
    }

    public NivelEficiencia getNivelEficienciaProcedimento() {
        return nivelEficienciaProcedimento;
    }

    public void setNivelEficienciaProcedimento(NivelEficiencia nivelEficienciaProcedimento) {
        this.nivelEficienciaProcedimento = nivelEficienciaProcedimento;
    }

    public NivelEficiencia getNivelEficienciaPessoa() {
        return nivelEficienciaPessoa;
    }

    public void setNivelEficienciaPessoa(NivelEficiencia nivelEficienciaPessoa) {
        this.nivelEficienciaPessoa = nivelEficienciaPessoa;
    }

    public NivelEficiencia getNivelEficienciaProcesso() {
        return nivelEficienciaProcesso;
    }

    public void setNivelEficienciaProcesso(NivelEficiencia nivelEficienciaProcesso) {
        this.nivelEficienciaProcesso = nivelEficienciaProcesso;
    }

    public NivelEficiencia getNivelEficienciaProduto() {
        return nivelEficienciaProduto;
    }

    public void setNivelEficienciaProduto(NivelEficiencia nivelEficienciaProduto) {
        this.nivelEficienciaProduto = nivelEficienciaProduto;
    }

    public Instant getCanceladoEm() {
        return canceladoEm;
    }

    public void setCanceladoEm(Instant canceladoEm) {
        this.canceladoEm = canceladoEm;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
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

    public Long getAvaliadorId() {
        return avaliadorId;
    }

    public void setAvaliadorId(Long avaliadorId) {
        this.avaliadorId = avaliadorId;
    }

    public String getAvaliadorNome() {
        return avaliadorNome;
    }

    public void setAvaliadorNome(String avaliadorNome) {
        this.avaliadorNome = avaliadorNome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AvaliacaoDTO avaliacaoDTO = (AvaliacaoDTO) o;
        if (avaliacaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), avaliacaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AvaliacaoDTO{" +
            "id=" + getId() +
            ", dataInicio='" + getDataInicio() + "'" +
            ", latitudeInicioAvaliacao=" + getLatitudeInicioAvaliacao() +
            ", longitudeInicioAvaliacao=" + getLongitudeInicioAvaliacao() +
            ", nomeResponsavelLoja='" + getNomeResponsavelLoja() + "'" +
            ", prontuarioResponsavelLoja=" + getProntuarioResponsavelLoja() +
            ", submetidoEm='" + getSubmetidoEm() + "'" +
            ", latitudeSubmissaoAvaliacao=" + getLatitudeSubmissaoAvaliacao() +
            ", longitudeSubmissaoAvaliacao=" + getLongitudeSubmissaoAvaliacao() +
            ", observacaoSubmissaoEnviadaForaDaLoja='" + getObservacaoSubmissaoEnviadaForaDaLoja() + "'" +
            ", status='" + getStatus() + "'" +
            ", criticidadePainel='" + getCriticidadePainel() + "'" +
            ", nivelEficienciaGeral='" + getNivelEficienciaGeral() + "'" +
            ", nivelEficienciaProcedimento='" + getNivelEficienciaProcedimento() + "'" +
            ", nivelEficienciaPessoa='" + getNivelEficienciaPessoa() + "'" +
            ", nivelEficienciaProcesso='" + getNivelEficienciaProcesso() + "'" +
            ", nivelEficienciaProduto='" + getNivelEficienciaProduto() + "'" +
            ", canceladoEm='" + getCanceladoEm() + "'" +
            ", motivoCancelamento='" + getMotivoCancelamento() + "'" +
            ", questionario=" + getQuestionarioId() +
            ", questionario='" + getQuestionarioNome() + "'" +
            ", avaliador=" + getAvaliadorId() +
            ", avaliador='" + getAvaliadorNome() + "'" +
            "}";
    }
}
