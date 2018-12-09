package br.com.lasa.dcpdesconformidades.repository;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.query.SearchScope;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Repository;

import br.com.lasa.dcpdesconformidades.config.ApplicationProperties;
import br.com.lasa.dcpdesconformidades.service.dto.LdapUserDTO;

@Repository
public class UserLdapRepository {

  private static final String ATTRIBUTE_PRONTUARIO = "prontuario";

  private static final String ATTRIBUTE_EMAIL = "mail";

  private static final String ATTRIBUTE_NAME = "name";

  private static final String ATTRIBUTE_LOGIN = "sAMAccountName";

  private static final Logger LOGGER = LoggerFactory.getLogger(UserLdapRepository.class);

  private final LdapTemplate ldapTemplate;

  private final ApplicationProperties applicationProperties;

  public UserLdapRepository(LdapTemplate ldapTemplate, ApplicationProperties applicationProperties) {
    this.ldapTemplate = ldapTemplate;
    this.applicationProperties = applicationProperties;
  }

  public LdapUserDTO getUserByLogin(String login) throws LdapException {

    LdapQuery query = LdapQueryBuilder.query() //
        .searchScope(SearchScope.SUBTREE) //
        .countLimit(1) //
        .attributes(ATTRIBUTE_LOGIN, ATTRIBUTE_NAME, ATTRIBUTE_EMAIL, ATTRIBUTE_PRONTUARIO) //
        .base(LdapUtils.emptyLdapName()) //
        .where("objectclass").is("person") //
        .and(ATTRIBUTE_LOGIN).is(login);

    try {
      List<LdapUserDTO> users = ldapTemplate.search(query, new UserAttributesMapper());
      if (users == null || users.isEmpty()) {
        return null;
      }

      return users.get(0);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new LdapException("Ocorreu um erro ao consultar usuários no AD por filtro.", e);
    }

  }

  public List<LdapUserDTO> getUsersByNameLike(String name) throws LdapException {

    LdapQuery query = LdapQueryBuilder.query() //
        .searchScope(SearchScope.SUBTREE) //
        .attributes(ATTRIBUTE_LOGIN, ATTRIBUTE_NAME, ATTRIBUTE_EMAIL, ATTRIBUTE_PRONTUARIO) //
        .base(LdapUtils.emptyLdapName()) //
        .where("objectclass").is("person") //
        .and(ATTRIBUTE_NAME).like(String.format("*%s*", name)) //
        .and(ATTRIBUTE_LOGIN).isPresent();

    try {
      List<LdapUserDTO> users = ldapTemplate.search(query, new UserAttributesMapper());
      if (users == null) {
        return new ArrayList<>();
      }

      return users;
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new LdapException("Ocorreu um erro ao consultar usuários no AD por filtro.", e);
    }

  }

  private class UserAttributesMapper implements AttributesMapper<LdapUserDTO> {
    public LdapUserDTO mapFromAttributes(Attributes attrs) throws NamingException {
      LdapUserDTO user = new LdapUserDTO();

      user.setLogin((String) attrs.get(ATTRIBUTE_LOGIN).get());

      user.setNome((String) attrs.get(ATTRIBUTE_NAME).get());

      Attribute emailAttribute = attrs.get(ATTRIBUTE_EMAIL);
      user.setEmail(emailAttribute == null ? null : (String) emailAttribute.get());

      Attribute prontuarioAttribute = attrs.get(ATTRIBUTE_PRONTUARIO);
      if (prontuarioAttribute != null) {
        boolean isNumber = ((String) prontuarioAttribute.get()).replaceAll("[0-9]", "").length() == 0;
        user.setProntuario(isNumber ? Integer.valueOf((String) prontuarioAttribute.get()) : null);
      }
      return user;
    }
  }

}
