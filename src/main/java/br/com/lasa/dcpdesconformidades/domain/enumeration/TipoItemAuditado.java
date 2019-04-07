package br.com.lasa.dcpdesconformidades.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The TipoItemAuditado enumeration.
 */
public enum TipoItemAuditado {
    TOP_5_PERDAS("Top 5 Perdas"), AUTO_RISCO("Auto Risco"), TROCA_CANCELAMENTO_DVC("Troca/Cancelamento/DVC");

    private String descricao;

    TipoItemAuditado(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static List<Map<String, String>> getAllOptions() {
        return Arrays.stream(CriticidadePainel.values())
            .map((item) -> Collections.unmodifiableMap(new HashMap<String, String>() {
                    {
                        put("id", item.name());
                        put("descricao", item.getDescricao());
                    }
                })
            )
            .collect(Collectors.toList());
    }
}
