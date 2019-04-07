package br.com.lasa.dcpdesconformidades.domain.enumeration;

/**
 * The StatusAvaliacao enumeration.
 */
public enum StatusAvaliacao {
    INICIADA("Iniciada"),
    CHECKLIST_FINALIZADO("Checklist Finalizado"),
    ANEXO_AUDITORIA_FINALIZADO("Anexo Auditoria Finalizado"),
    ANEXO_SOLICITACAO_AJUSTE_FINALIZADO("Anexo Solicitacao Finalizado"),
    SUBMETIDA("Submetida"),
    CANCELADA("Cancelada");

    private String descricao;

    StatusAvaliacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
