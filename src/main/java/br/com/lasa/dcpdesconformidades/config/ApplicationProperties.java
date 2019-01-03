package br.com.lasa.dcpdesconformidades.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Dcpdesconformidades.
 * <p>
 * Properties are configured in the application.yml file. See
 * {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

  private final Ldap ldap = new Ldap();

  private String urlRaioxObterLojaPorCodigo;
  private String urlRaioxListarTodasLojas;
  private String raioxUser;
  private String raioxPassword;
  
  private String filesBasePath;

  public static class Ldap {
    private String url;
    private String userDn;
    private String userPassword;
    private String userSearchBase;
    private int connectTimeoutInMilliseconds;

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getUserDn() {
      return userDn;
    }

    public void setUserDn(String userDn) {
      this.userDn = userDn;
    }

    public String getUserPassword() {
      return userPassword;
    }

    public void setUserPassword(String userPassword) {
      this.userPassword = userPassword;
    }

    public String getUserSearchBase() {
      return userSearchBase;
    }

    public void setUserSearchBase(String userSearchBase) {
      this.userSearchBase = userSearchBase;
    }

    public int getConnectTimeoutInMilliseconds() {
      return connectTimeoutInMilliseconds;
    }

    public void setConnectTimeoutInMilliseconds(int connectTimeoutInMilliseconds) {
      this.connectTimeoutInMilliseconds = connectTimeoutInMilliseconds;
    }
    
  }

  public Ldap getLdap() {
    return ldap;
  }

  public String getUrlRaioxObterLojaPorCodigo() {
    return urlRaioxObterLojaPorCodigo;
  }

  public void setUrlRaioxObterLojaPorCodigo(String urlRaioxObterLojaPorCodigo) {
    this.urlRaioxObterLojaPorCodigo = urlRaioxObterLojaPorCodigo;
  }

  public String getUrlRaioxListarTodasLojas() {
    return urlRaioxListarTodasLojas;
  }

  public void setUrlRaioxListarTodasLojas(String urlRaioxListarTodasLojas) {
    this.urlRaioxListarTodasLojas = urlRaioxListarTodasLojas;
  }

  public String getRaioxUser() {
    return raioxUser;
  }

  public void setRaioxUser(String usuarioRaiox) {
    this.raioxUser = usuarioRaiox;
  }

  public String getRaioxPassword() {
    return raioxPassword;
  }

  public void setRaioxPassword(String senhaRaiox) {
    this.raioxPassword = senhaRaiox;
  }

  public String getFilesBasePath() {
    return filesBasePath;
  }

  public void setFilesBasePath(String filesBasePath) {
    this.filesBasePath = filesBasePath;
  }
  
}
