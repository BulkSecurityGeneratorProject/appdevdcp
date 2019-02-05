package br.com.lasa.dcpdesconformidades.service.dto;

import java.io.Serializable;

public class LdapUserDTO implements Serializable {
  private static final long serialVersionUID = -7746452165600340335L;

  private String login;

  private Integer prontuario;

  private String name;

  private String email;
  
  public LdapUserDTO() {
    // Empty
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public Integer getProntuario() {
    return prontuario;
  }

  public void setProntuario(Integer prontuario) {
    this.prontuario = prontuario;
  }

  public String getName() {
    return name;
  }

  public void setName(String nome) {
    this.name = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }



}
