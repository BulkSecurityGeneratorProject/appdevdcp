package br.com.lasa.dcpdesconformidades.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lasa.dcpdesconformidades.config.Constants;
import br.com.lasa.dcpdesconformidades.domain.User;
import br.com.lasa.dcpdesconformidades.domain.enumeration.Authority;
import br.com.lasa.dcpdesconformidades.repository.UserRepository;
import br.com.lasa.dcpdesconformidades.security.SecurityUtils;
import br.com.lasa.dcpdesconformidades.service.dto.UserDTO;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final CacheManager cacheManager;

    public UserService(UserRepository userRepository, CacheManager cacheManager) {
        this.userRepository = userRepository;
        this.cacheManager = cacheManager;
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin().toLowerCase());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail().toLowerCase());
        user.setActivated(true);
        user.setProntuario(userDTO.getProntuario());
        user.setAuthorities(userDTO.getAuthorities());
        user.setLojas(userDTO.getLojas());
        userRepository.save(user);
        this.clearUserCaches(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param name name of user
     * @param email email id of user
     */
    public void updateUser(String name, String email) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .ifPresent(user -> {
                user.setName(name);
                user.setEmail(email.toLowerCase());
                this.clearUserCaches(user);
                log.debug("Changed Information for User: {}", user);
            });
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update
     * @return updated user
     */
    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        return Optional.of(userRepository
            .findById(userDTO.getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(user -> {
                this.clearUserCaches(user);
                user.setLogin(userDTO.getLogin().toLowerCase());
                user.setName(userDTO.getName());
                user.setEmail(userDTO.getEmail().toLowerCase());
                user.setActivated(userDTO.isActivated());
                user.setProntuario(userDTO.getProntuario());
                user.setAuthorities(userDTO.getAuthorities());
                user.setLojas(userDTO.getLojas());
                this.clearUserCaches(user);
                log.debug("Changed Information for User: {}", user);
                return user;
            })
            .map(UserDTO::new);
    }

    public void deleteUser(String login) {
        userRepository.findOneByLogin(login).ifPresent(user -> {
            userRepository.delete(user);
            this.clearUserCaches(user);
            log.debug("Deleted User: {}", user);
        });
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAllByLoginNotIn(pageable, new HashSet<String>(Arrays.asList(Constants.ANONYMOUS_USER, Constants.SYSTEM_ACCOUNT))).map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(Long id) {
        return userRepository.findOneWithAuthoritiesById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);
    }

    /**
     * @return a list of all the authorities
     */
    public List<Authority> getAuthorities() {
        return Arrays.asList(Authority.values());
    }

    private void clearUserCaches(User user) {
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)).evict(user.getLogin());
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
        // TODO deveria limpar somente para os avaliadores dentro do objeto da loja, mas o cache não é
        // limpado devido ao objeto armazenado como key pelo hibernate ser um LongType e não um Long
        // normal (não é possível criar um LongType para efetuar a comparação).
        Objects.requireNonNull(cacheManager.getCache(br.com.lasa.dcpdesconformidades.domain.Loja.class.getName() + ".avaliadores")).clear();
    }
}
