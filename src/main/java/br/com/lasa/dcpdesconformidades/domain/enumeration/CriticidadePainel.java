package br.com.lasa.dcpdesconformidades.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The CriticidadePainel enumeration.
 */
public enum CriticidadePainel {
    INADMISSIVEL("Inadmissível"), //
    CONTROLE("Controle"), //
    VALOR_ELEVADO("Valor Elevado"), //
    CRITICO("Crítico"), //
    ATENCAO("Atenção");

    private String descricao;

    CriticidadePainel(String descricao) {
        this.descricao = descricao;
    }

    public static CriticidadePainel fromDescricao(String descricao) {
        for (CriticidadePainel c : CriticidadePainel.values()) {
            if (c.getDescricao().equals(descricao)) {
                return c;
            }
        }
        return null;
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

    public String getDescricao() {
        return descricao;
    }

}
