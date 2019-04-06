package br.com.lasa.dcpdesconformidades.web.rest.vm;


import br.com.lasa.dcpdesconformidades.domain.Avaliacao;
import br.com.lasa.dcpdesconformidades.domain.enumeration.CriticidadePainel;
import br.com.lasa.dcpdesconformidades.domain.enumeration.TipoItemAuditado;

import java.util.Arrays;
import java.util.List;

public class IniciarAvaliacaoOutputVM {

    private Avaliacao avaliacao;

    private List<TipoItemAuditado> tiposAuditoria = Arrays.asList(TipoItemAuditado.values());

    private List<CriticidadePainel> criticidadesPainel = Arrays.asList(CriticidadePainel.values());

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<TipoItemAuditado> getTiposAuditoria() {
        return tiposAuditoria;
    }

    public List<CriticidadePainel> getCriticidadesPainel() {
        return criticidadesPainel;
    }
}
