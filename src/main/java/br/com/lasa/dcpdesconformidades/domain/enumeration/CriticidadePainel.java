package br.com.lasa.dcpdesconformidades.domain.enumeration;

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

  public String getDescricao() {
    return descricao;
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
