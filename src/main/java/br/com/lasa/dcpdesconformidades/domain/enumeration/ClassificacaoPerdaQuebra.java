package br.com.lasa.dcpdesconformidades.domain.enumeration;

/**
 * The ClassificacaoPerdaQuebra enumeration.
 */
public enum ClassificacaoPerdaQuebra {
  CONFORMIDADE("Conformidade"), //
  DESCONFORMIDADE("Desconformidade");

  private String descricao;

  ClassificacaoPerdaQuebra(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }

  public static ClassificacaoPerdaQuebra fromDescricao(String descricao) {
    for (ClassificacaoPerdaQuebra c : ClassificacaoPerdaQuebra.values()) {
      if (c.getDescricao().equals(descricao)) {
        return c;
      }
    }
    return null;
  }
}
