package br.com.lasa.dcpdesconformidades.domain.enumeration;

/**
 * The StatusItemAvaliado enumeration.
 */
public enum StatusItemAvaliado {
    OK("Conformidade"), //
    NAO_OK("Desconformidade"), //
    N_A("N/A");
  
  private String classificacao;

  StatusItemAvaliado(String classificacao) {
    this.classificacao = classificacao;
  }

  public String getClassificacao() {
    return classificacao;
  }

  public static StatusItemAvaliado fromClassificacao(String classificacao) {
    for (StatusItemAvaliado s : StatusItemAvaliado.values()) {
      if (s.getClassificacao().equals(classificacao)) {
        return s;
      }
    }
    return null;
  }
}
