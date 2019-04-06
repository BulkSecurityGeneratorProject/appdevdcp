package br.com.lasa.dcpdesconformidades.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The CriticidadePainel enumeration.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
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

  public String getDescricao() {
    return descricao;
  }

    public String getId() {
        return this.name();
    }

  public static CriticidadePainel fromDescricao(String descricao) {
    for (CriticidadePainel c : CriticidadePainel.values()) {
      if (c.getDescricao().equals(descricao)) {
        return c;
      }
    }
    return null;
  }


}
