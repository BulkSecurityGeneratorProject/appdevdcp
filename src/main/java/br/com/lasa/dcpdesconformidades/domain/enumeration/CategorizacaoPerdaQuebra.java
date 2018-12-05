package br.com.lasa.dcpdesconformidades.domain.enumeration;

/**
 * The CategorizacaoPerdaQuebra enumeration.
 */
public enum CategorizacaoPerdaQuebra {
  INADMISSIVEL("Inadmissível"), //
  CRITICO("Crítico"), //
  VALOR_ELEVADO("Valor elevado"), //
  ATENCAO("Atenção"), //
  CONTROLE("Controle"), //
  SOBRA_DESCONTROLE("Sobra, descontrole");

  private String descricao;

  CategorizacaoPerdaQuebra(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }

  public static CategorizacaoPerdaQuebra fromDescricao(String descricao) {
    for (CategorizacaoPerdaQuebra c : CategorizacaoPerdaQuebra.values()) {
      if (c.getDescricao().equals(descricao)) {
        return c;
      }
    }
    return null;
  }
}
