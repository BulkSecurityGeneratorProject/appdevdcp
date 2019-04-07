package br.com.lasa.dcpdesconformidades.web.rest.vm;


import br.com.lasa.dcpdesconformidades.domain.Avaliacao;
import br.com.lasa.dcpdesconformidades.domain.enumeration.CriticidadePainel;
import br.com.lasa.dcpdesconformidades.domain.enumeration.TipoItemAuditado;

import java.util.List;
import java.util.Map;

public class IniciarAvaliacaoOutputVM {

    private Avaliacao avaliacao;

    private List<Map<String,String>> tiposAuditoria = TipoItemAuditado.getAllOptions();

    private List<Map<String,String>> criticidadesPainel = CriticidadePainel.getAllOptions();

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Map<String, String>> getTiposAuditoria() {
        return tiposAuditoria;
    }

    public List<Map<String, String>> getCriticidadesPainel() {
        return criticidadesPainel;
    }
}
