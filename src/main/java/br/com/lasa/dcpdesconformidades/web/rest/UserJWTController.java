package br.com.lasa.dcpdesconformidades.web.rest;

import br.com.lasa.dcpdesconformidades.domain.User;
import br.com.lasa.dcpdesconformidades.repository.UserRepository;
import br.com.lasa.dcpdesconformidades.security.jwt.JWTFilter;
import br.com.lasa.dcpdesconformidades.security.jwt.TokenProvider;
import br.com.lasa.dcpdesconformidades.web.rest.errors.InternalServerErrorException;
import br.com.lasa.dcpdesconformidades.web.rest.errors.NotFoundException;
import br.com.lasa.dcpdesconformidades.web.rest.vm.LoginVM;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final String NAME_CLAIM = "name";
    private final String PROTUARIO_CLAIM = "prt";

    public UserJWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/authenticate")
    @Timed
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());

            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();

            User user = userRepository.findOneByLogin(loginVM.getUsername()).orElseThrow(() -> new InternalServerErrorException("Current user login not found. Login not on user list"));
            ;
            HashMap<String, Object> aditionalClaims = new HashMap<>();

            aditionalClaims.put(NAME_CLAIM, user.getName());
            aditionalClaims.put(PROTUARIO_CLAIM, user.getProntuario());

            String jwt = tokenProvider.createToken(authentication, rememberMe, aditionalClaims);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            throw new NotFoundException("Usuário ou senha inválidos");
        }
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
