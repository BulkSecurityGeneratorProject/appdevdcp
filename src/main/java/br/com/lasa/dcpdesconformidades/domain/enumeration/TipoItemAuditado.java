package br.com.lasa.dcpdesconformidades.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The TipoItemAuditado enumeration.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoItemAuditado {
    TOP_5_PERDAS("Top 5 Perdas"), AUTO_RISCO("Auto Risco"), TROCA_CANCELAMENTO_DVC("Troca/Cancelamento/DVC");

    private String descricao;

    TipoItemAuditado(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getId() {
        return this.name();
    }

    public static List<Map<String, String>> getAllOptions() {
        return Arrays.stream(TipoItemAuditado.values())
            .map((item) -> Collections.singletonMap(item.name(), item.getDescricao()))
            .collect(Collectors.toList());
    }
}
