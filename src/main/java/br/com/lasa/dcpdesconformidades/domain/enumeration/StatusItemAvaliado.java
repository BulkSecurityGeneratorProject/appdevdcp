package br.com.lasa.dcpdesconformidades.domain.enumeration;

/**
 * The StatusItemAvaliado enumeration.
 */
public enum StatusItemAvaliado {
    OK("OK", "Conformidade"), //
    NAO_OK("Não OK", "Desconformidade"), //
    N_A("N/A", "N/A");
  
  private String descricao;
  private String classificacao;

  StatusItemAvaliado(String descricao, String classificacao) {
    this.descricao = descricao;
    this.classificacao = classificacao;
  }

  public String getDescricao() {
    return descricao;
  }
  
  public String getClassificacao() {
    return classificacao;
  }

  public static StatusItemAvaliado fromDescricao(String descricao) {
    for (StatusItemAvaliado s : StatusItemAvaliado.values()) {
      if (s.getDescricao().equals(descricao)) {
        return s;
      }
    }
    return null;
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
