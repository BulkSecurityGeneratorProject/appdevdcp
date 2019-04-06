package br.com.lasa.dcpdesconformidades.web.rest.vm;

import br.com.lasa.dcpdesconformidades.domain.enumeration.CriticidadePainel;
import br.com.lasa.dcpdesconformidades.service.dto.ItemAuditadoDTO;
import br.com.lasa.dcpdesconformidades.service.dto.ItemAvaliadoDTO;
import br.com.lasa.dcpdesconformidades.service.dto.ItemSolicitadoAjusteDTO;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

public class SubmeterAvaliacaoInputVM {

    @NotNull
    private Long id;
    private Double latitude;
    private Double longitude;
    @NotNull
    private CriticidadePainel criticidadePainel;
    private String observacaoSubmissaoEnviadaForaDaLoja;
    @NotNull
    private Set<ItemAvaliadoDTO> itensAvaliados;
    @NotNull
    private LinkedHashSet<ItemAuditadoDTO> itensAuditados;
    @NotNull
    private Set<ItemSolicitadoAjusteDTO> itensComAjusteSolicitados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public CriticidadePainel getCriticidadePainel() {
        return criticidadePainel;
    }

    public void setCriticidadePainel(CriticidadePainel criticidadePainel) {
        this.criticidadePainel = criticidadePainel;
    }

    public String getObservacaoSubmissaoEnviadaForaDaLoja() {
        return observacaoSubmissaoEnviadaForaDaLoja;
    }

    public void setObservacaoSubmissaoEnviadaForaDaLoja(String observacaoSubmissaoEnviadaForaDaLoja) {
        this.observacaoSubmissaoEnviadaForaDaLoja = observacaoSubmissaoEnviadaForaDaLoja;
    }

    public Set<ItemAvaliadoDTO> getItensAvaliados() {
        return itensAvaliados;
    }

    public void setItensAvaliados(Set<ItemAvaliadoDTO> itensAvaliados) {
        this.itensAvaliados = itensAvaliados;
    }

    public LinkedHashSet<ItemAuditadoDTO> getItensAuditados() {
        return itensAuditados;
    }

    public void setItensAuditados(LinkedHashSet<ItemAuditadoDTO> itensAuditados) {
        this.itensAuditados = itensAuditados;
    }

    public Set<ItemSolicitadoAjusteDTO> getItensComAjusteSolicitados() {
        return itensComAjusteSolicitados;
    }

    public void setItensComAjusteSolicitados(Set<ItemSolicitadoAjusteDTO> itensComAjusteSolicitados) {
        this.itensComAjusteSolicitados = itensComAjusteSolicitados;
    }
}
