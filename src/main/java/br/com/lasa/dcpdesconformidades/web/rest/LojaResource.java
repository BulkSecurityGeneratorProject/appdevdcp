package br.com.lasa.dcpdesconformidades.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.lasa.dcpdesconformidades.domain.Loja;
import br.com.lasa.dcpdesconformidades.service.LojaService;
import br.com.lasa.dcpdesconformidades.web.rest.errors.BadRequestAlertException;
import br.com.lasa.dcpdesconformidades.web.rest.util.HeaderUtil;
import br.com.lasa.dcpdesconformidades.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Loja.
 */
@RestController
@RequestMapping("/api")
public class LojaResource {

    private final Logger log = LoggerFactory.getLogger(LojaResource.class);

    private static final String ENTITY_NAME = "loja";

    private final LojaService lojaService;

    public LojaResource(LojaService lojaService) {
        this.lojaService = lojaService;
    }

    /**
     * POST  /lojas : Create a new loja.
     *
     * @param loja the loja to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loja, or with status 400 (Bad Request) if the loja has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/lojas")
    @Timed
    public ResponseEntity<Loja> createLoja(@Valid @RequestBody Loja loja) throws URISyntaxException {
        log.debug("REST request to save Loja : {}", loja);
        if (loja.getId() != null) {
            throw new BadRequestAlertException("A new loja cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Loja result = lojaService.save(loja);
        return ResponseEntity.created(new URI("/api/lojas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
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
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of lojas in body
     */
    @GetMapping("/lojas")
    @Timed
    public ResponseEntity<List<Loja>> getAllLojas(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Lojas");
        Page<Loja> page;
        if (eagerload) {
            page = lojaService.findAllWithEagerRelationships(pageable);
        } else {
            page = lojaService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/lojas?eagerload=%b", eagerload));
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

    /**
     * DELETE  /lojas/:id : delete the "id" loja.
     *
     * @param id the id of the loja to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/lojas/{id}")
    @Timed
    public ResponseEntity<Void> deleteLoja(@PathVariable Long id) {
        log.debug("REST request to delete Loja : {}", id);
        lojaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
