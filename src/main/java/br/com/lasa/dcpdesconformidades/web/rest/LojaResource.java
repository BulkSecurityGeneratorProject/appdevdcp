package br.com.lasa.dcpdesconformidades.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.lasa.dcpdesconformidades.domain.Loja;
import br.com.lasa.dcpdesconformidades.domain.User;
import br.com.lasa.dcpdesconformidades.security.AuthoritiesConstants;
import br.com.lasa.dcpdesconformidades.service.LojaService;
import br.com.lasa.dcpdesconformidades.service.UserService;
import br.com.lasa.dcpdesconformidades.service.dto.LojaParaAvaliacaoDTO;
import br.com.lasa.dcpdesconformidades.web.rest.errors.BadRequestAlertException;
import br.com.lasa.dcpdesconformidades.web.rest.errors.InternalServerErrorException;
import br.com.lasa.dcpdesconformidades.web.rest.util.HeaderUtil;
import br.com.lasa.dcpdesconformidades.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Loja.
 */
@RestController
@RequestMapping("/api")
public class LojaResource {

    private final Logger log = LoggerFactory.getLogger(LojaResource.class);
    
    private static final String ENTITY_NAME = "loja";

    private final LojaService lojaService;

    private final UserService userService;

    public LojaResource(LojaService lojaService, UserService userService) {
        this.lojaService = lojaService;
        this.userService = userService;
    }
    
    
    /**
     * PUT  /lojas : Updates an existing loja.
     *
     * @param loja the loja to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loja,
     * or with status 400 (Bad Request) if the loja is not valid,
     * or with status 500 (Internal Server Error) if the loja couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/lojas")
    @Timed
    public ResponseEntity<Loja> updateLoja(@Valid @RequestBody Loja loja) throws URISyntaxException {
        log.debug("REST request to update Loja : {}", loja);
        if (loja.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Loja result = lojaService.save(loja);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, loja.getId().toString()))
            .body(result);
    }

    /**
     * GET  /lojas : get all the lojas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of lojas in body
     */
    @GetMapping("/lojas")
    @Timed
    public ResponseEntity<List<Loja>> getAllLojas(Pageable pageable) {
        log.debug("REST request to get a page of Lojas");
        Page<Loja> page = lojaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/lojas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /lojas/:id : get the "id" loja.
     *
     * @param id the id of the loja to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loja, or with status 404 (Not Found)
     */
    @GetMapping("/lojas/{id}")
    @Timed
    public ResponseEntity<Loja> getLoja(@PathVariable Long id) {
        log.debug("REST request to get Loja : {}", id);
        Optional<Loja> loja = lojaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loja);
    }
    
    @GetMapping("/lojas/permitidas-para-avaliacao")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.AVALIADOR + "\")")
    public List<LojaParaAvaliacaoDTO> getLojasPermitidasParaAvaliacaoParaUsuarioLogado() {
        log.debug("REST request to get a page of Lojas");
        
        final User user = userService.getUserWithAuthorities().orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
        
        return lojaService.getLojasPermitidasParaAvaliacaoPara(user);
    }
}
