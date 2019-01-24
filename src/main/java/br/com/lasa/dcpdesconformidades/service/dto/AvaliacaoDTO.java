package br.com.lasa.dcpdesconformidades.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusAvaliacao;
import br.com.lasa.dcpdesconformidades.domain.enumeration.CriticidadePainel;
import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;
import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;
import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;
import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;
import br.com.lasa.dcpdesconformidades.domain.enumeration.NivelEficiencia;
import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusItemAvaliado;
import br.com.lasa.dcpdesconformidades.domain.enumeration.CategorizacaoPerdaQuebra;
import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusItemAvaliado;
import br.com.lasa.dcpdesconformidades.domain.enumeration.CategorizacaoPerdaQuebra;

/**
 * A DTO for the Avaliacao entity.
 */
public class AvaliacaoDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant iniciadaEm;

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

    private Double percentualPerda;

    private BigDecimal financeiroPerda;

    private Integer pontuacaoPerda;

    private StatusItemAvaliado statusPerda;

    private CategorizacaoPerdaQuebra categorizacaoPerda;

    private Double percentualQuebra;

    private BigDecimal financeiroQuebra;

    private Integer pontuacaoQuebra;

    private StatusItemAvaliado statusQuebra;

    private CategorizacaoPerdaQuebra categorizacaoQuebra;

    @NotNull
    private Boolean importadoViaPlanilha;

    private String caminhoArquivoPlanilha;

    private Long avaliadorId;

    private String avaliadorName;
    
    private String avaliadorProntuario;

    private Long questionarioId;

    private String questionarioNome;

    private Long lojaId;

    private String lojaNome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getIniciadaEm() {
        return iniciadaEm;
    }

    public void setIniciadaEm(Instant iniciadaEm) {
        this.iniciadaEm = iniciadaEm;
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

    public Double getPercentualPerda() {
        return percentualPerda;
    }

    public void setPercentualPerda(Double percentualPerda) {
        this.percentualPerda = percentualPerda;
    }

    public BigDecimal getFinanceiroPerda() {
        return financeiroPerda;
    }

    public void setFinanceiroPerda(BigDecimal financeiroPerda) {
        this.financeiroPerda = financeiroPerda;
    }

    public Integer getPontuacaoPerda() {
        return pontuacaoPerda;
    }

    public void setPontuacaoPerda(Integer pontuacaoPerda) {
        this.pontuacaoPerda = pontuacaoPerda;
    }

    public StatusItemAvaliado getStatusPerda() {
        return statusPerda;
    }

    public void setStatusPerda(StatusItemAvaliado statusPerda) {
        this.statusPerda = statusPerda;
    }

    public CategorizacaoPerdaQuebra getCategorizacaoPerda() {
        return categorizacaoPerda;
    }

    public void setCategorizacaoPerda(CategorizacaoPerdaQuebra categorizacaoPerda) {
        this.categorizacaoPerda = categorizacaoPerda;
    }

    public Double getPercentualQuebra() {
        return percentualQuebra;
    }

    public void setPercentualQuebra(Double percentualQuebra) {
        this.percentualQuebra = percentualQuebra;
    }

    public BigDecimal getFinanceiroQuebra() {
        return financeiroQuebra;
    }

    public void setFinanceiroQuebra(BigDecimal financeiroQuebra) {
        this.financeiroQuebra = financeiroQuebra;
    }

    public Integer getPontuacaoQuebra() {
        return pontuacaoQuebra;
    }

    public void setPontuacaoQuebra(Integer pontuacaoQuebra) {
        this.pontuacaoQuebra = pontuacaoQuebra;
    }

    public StatusItemAvaliado getStatusQuebra() {
        return statusQuebra;
    }

    public void setStatusQuebra(StatusItemAvaliado statusQuebra) {
        this.statusQuebra = statusQuebra;
    }

    public CategorizacaoPerdaQuebra getCategorizacaoQuebra() {
        return categorizacaoQuebra;
    }

    public void setCategorizacaoQuebra(CategorizacaoPerdaQuebra categorizacaoQuebra) {
        this.categorizacaoQuebra = categorizacaoQuebra;
    }

    public Boolean isImportadoViaPlanilha() {
        return importadoViaPlanilha;
    }

    public void setImportadoViaPlanilha(Boolean importadoViaPlanilha) {
        this.importadoViaPlanilha = importadoViaPlanilha;
    }

    public String getCaminhoArquivoPlanilha() {
        return caminhoArquivoPlanilha;
    }

    public void setCaminhoArquivoPlanilha(String caminhoArquivoPlanilha) {
        this.caminhoArquivoPlanilha = caminhoArquivoPlanilha;
    }

    public Long getAvaliadorId() {
        return avaliadorId;
    }

    public void setAvaliadorId(Long userId) {
        this.avaliadorId = userId;
    }

    public String getAvaliadorName() {
        return avaliadorName;
    }

    public void setAvaliadorName(String userName) {
        this.avaliadorName = userName;
    }
    
    public String getAvaliadorProntuario() {
      return avaliadorProntuario;
    }

    public void setAvaliadorProntuario(String avaliadorProntuario) {
      this.avaliadorProntuario = avaliadorProntuario;
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

    public Long getLojaId() {
        return lojaId;
    }

    public void setLojaId(Long lojaId) {
        this.lojaId = lojaId;
    }

    public String getLojaNome() {
        return lojaNome;
    }

    public void setLojaNome(String lojaNome) {
        this.lojaNome = lojaNome;
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
            ", iniciadaEm='" + getIniciadaEm() + "'" +
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
            ", percentualPerda=" + getPercentualPerda() +
            ", financeiroPerda=" + getFinanceiroPerda() +
            ", pontuacaoPerda=" + getPontuacaoPerda() +
            ", statusPerda='" + getStatusPerda() + "'" +
            ", categorizacaoPerda='" + getCategorizacaoPerda() + "'" +
            ", percentualQuebra=" + getPercentualQuebra() +
            ", financeiroQuebra=" + getFinanceiroQuebra() +
            ", pontuacaoQuebra=" + getPontuacaoQuebra() +
            ", statusQuebra='" + getStatusQuebra() + "'" +
            ", categorizacaoQuebra='" + getCategorizacaoQuebra() + "'" +
            ", importadoViaPlanilha='" + isImportadoViaPlanilha() + "'" +
            ", caminhoArquivoPlanilha='" + getCaminhoArquivoPlanilha() + "'" +
            ", avaliador=" + getAvaliadorId() +
            ", avaliador='" + getAvaliadorName() + "'" +
            ", questionario=" + getQuestionarioId() +
            ", questionario='" + getQuestionarioNome() + "'" +
            ", loja=" + getLojaId() +
            ", loja='" + getLojaNome() + "'" +
            "}";
    }
}
