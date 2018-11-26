package br.com.lasa.dcpdesconformidades.security;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

import br.com.lasa.dcpdesconformidades.domain.User;
import br.com.lasa.dcpdesconformidades.repository.UserRepository;

@Component
public class LdapAuthenticationManager implements AuthenticationManager {

  LdapAuthenticationProvider provider = null;

  private static final Logger log = LoggerFactory.getLogger(LdapAuthenticationManager.class);

  private final UserRepository userRepository;

  @Autowired
  private final LdapContextSource ldapContextSource;

  public LdapAuthenticationManager(UserRepository userRepository, LdapContextSource ldapContextSource) {
    this.userRepository = userRepository;
    this.ldapContextSource = ldapContextSource;
  }

  @Override
  public Authentication authenticate(Authentication authentication) {
    BindAuthenticator bindAuth = new BindAuthenticator(ldapContextSource);
    FilterBasedLdapUserSearch userSearch = new FilterBasedLdapUserSearch("", "(sAMAccountName={0})", ldapContextSource);
    try {
      bindAuth.setUserSearch(userSearch);
      bindAuth.afterPropertiesSet();
    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
    }
    provider = new LdapAuthenticationProvider(bindAuth);
    provider.setUserDetailsContextMapper(new UserDetailsContextMapper() {
      @Override
      public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
        String lowercaseLogin = username.toLowerCase(Locale.ENGLISH);
        return userRepository.findOneWithAuthoritiesByLogin(lowercaseLogin) //
            .map(user -> createSpringSecurityUser(lowercaseLogin, user)) //
            .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));  //TODO certitficar de que a exceção é propagada até a interface e o erro é mostrado ao usuário
      }

      @Override
      public void mapUserToContext(UserDetails ud, DirContextAdapter dca) {

      }
    });
    return provider.authenticate(authentication);
  }

  private UserDetails createSpringSecurityUser(String lowercaseLogin, User user) {
    if (!user.getActivated()) {
      throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated"); //TODO certitficar de que a exceção é propagada até a interface e o erro é mostrado ao usuário
    }
    List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream() //
        .map(authority -> new SimpleGrantedAuthority(authority.getName())) //
        .collect(Collectors.toList());
    return new org.springframework.security.core.userdetails.User(user.getLogin(), "1", grantedAuthorities);
  }

}
