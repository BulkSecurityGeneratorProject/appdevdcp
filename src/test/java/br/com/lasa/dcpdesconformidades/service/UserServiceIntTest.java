package br.com.lasa.dcpdesconformidades.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.lasa.dcpdesconformidades.DcpdesconformidadesApp;
import br.com.lasa.dcpdesconformidades.config.Constants;
import br.com.lasa.dcpdesconformidades.domain.User;
import br.com.lasa.dcpdesconformidades.repository.UserRepository;
import br.com.lasa.dcpdesconformidades.service.dto.UserDTO;

/**
 * Test class for the UserResource REST controller.
 *
 * @see UserService
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DcpdesconformidadesApp.class)
@Transactional
public class UserServiceIntTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private AuditingHandler auditingHandler;

  @Mock
  DateTimeProvider dateTimeProvider;

  private User user;

  @Before
  public void init() {
    user = new User();
    user.setLogin("johndoe");
    user.setActivated(true);
    user.setEmail("johndoe@localhost");
    user.setName("john");

    when(dateTimeProvider.getNow()).thenReturn(Optional.of(LocalDateTime.now()));
    auditingHandler.setDateTimeProvider(dateTimeProvider);
  }

  @Test
  @Transactional
  public void assertThatAnonymousUserIsNotGet() {
    user.setLogin(Constants.ANONYMOUS_USER);
    if (!userRepository.findOneByLogin(Constants.ANONYMOUS_USER).isPresent()) {
      userRepository.saveAndFlush(user);
    }
    final PageRequest pageable = PageRequest.of(0, (int) userRepository.count());
    final Page<UserDTO> allManagedUsers = userService.getAllManagedUsers(pageable);
    assertThat(allManagedUsers.getContent().stream().noneMatch(user -> Constants.ANONYMOUS_USER.equals(user.getLogin()))).isTrue();
  }


}
