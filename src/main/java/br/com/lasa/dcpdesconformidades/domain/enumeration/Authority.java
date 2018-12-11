package br.com.lasa.dcpdesconformidades.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import br.com.lasa.dcpdesconformidades.security.AuthoritiesConstants;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Authority {
  ROLE_ADMIN(AuthoritiesConstants.ADMIN, "Administrador Sistema"), //
  ROLE_USER(AuthoritiesConstants.USER, "Usuário Sistema"), //
  ROLE_AVALIADOR(AuthoritiesConstants.AVALIADOR, "Avaliador"); //

  private String name;
  
  private String descricao;

  Authority(String name, String descricao) {
    this.name = name;
    this.descricao = descricao;
  }
  
  public String getName() {
    return name;
  }

  public String getDescricao() {
    return descricao;
  }

  @JsonCreator
  public static Authority fromName(String name) {
    for (Authority a : Authority.values()) {
      if (a.getName().equals(name)) {
        return a;
      }
    }
    return null;
  }
}
