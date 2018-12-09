package br.com.lasa.dcpdesconformidades.repository;


public class LdapException extends Exception {
  private static final long serialVersionUID = -2026798653551746941L;

  public LdapException(Exception e) {
    super(e.getMessage(), e);
  }

  public LdapException(String mensagem) {
    super(mensagem);
  }

  public LdapException(String mensagem, Exception e) {
    super(mensagem, e);
  }

}
