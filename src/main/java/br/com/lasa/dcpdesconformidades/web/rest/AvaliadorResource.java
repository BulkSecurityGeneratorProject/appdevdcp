package br.com.lasa.dcpdesconformidades.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.lasa.dcpdesconformidades.domain.Avaliador;
import br.com.lasa.dcpdesconformidades.service.AvaliadorService;
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
 * REST controller for managing Avaliador.
 */
@RestController
@RequestMapping("/api")
public class AvaliadorResource {

    private final Logger log = LoggerFactory.getLogger(AvaliadorResource.class);

    private static final String ENTITY_NAME = "avaliador";

    private final AvaliadorService avaliadorService;

    public AvaliadorResource(AvaliadorService avaliadorService) {
        this.avaliadorService = avaliadorService;
    }

    /**
     * POST  /avaliadors : Create a new avaliador.
     *
     * @param avaliador the avaliador to create
     * @return the ResponseEntity with status 201 (Created) and with body the new avaliador, or with status 400 (Bad Request) if the avaliador has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/avaliadors")
    @Timed
    public ResponseEntity<Avaliador> createAvaliador(@Valid @RequestBody Avaliador avaliador) throws URISyntaxException {
        log.debug("REST request to save Avaliador : {}", avaliador);
        if (avaliador.getId() != null) {
            throw new BadRequestAlertException("A new avaliador cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Avaliador result = avaliadorService.save(avaliador);
        return ResponseEntity.created(new URI("/api/avaliadors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /avaliadors : Updates an existing avaliador.
     *
     * @param avaliador the avaliador to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated avaliador,
     * or with status 400 (Bad Request) if the avaliador is not valid,
     * or with status 500 (Internal Server Error) if the avaliador couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/avaliadors")
    @Timed
    public ResponseEntity<Avaliador> updateAvaliador(@Valid @RequestBody Avaliador avaliador) throws URISyntaxException {
        log.debug("REST request to update Avaliador : {}", avaliador);
        if (avaliador.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Avaliador result = avaliadorService.save(avaliador);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, avaliador.getId().toString()))
            .body(result);
    }

    /**
     * GET  /avaliadors : get all the avaliadors.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of avaliadors in body
     */
    @GetMapping("/avaliadors")
    @Timed
    public ResponseEntity<List<Avaliador>> getAllAvaliadors(Pageable pageable) {
        log.debug("REST request to get a page of Avaliadors");
        Page<Avaliador> page = avaliadorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/avaliadors");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /avaliadors/:id : get the "id" avaliador.
     *
     * @param id the id of the avaliador to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the avaliador, or with status 404 (Not Found)
     */
    @GetMapping("/avaliadors/{id}")
    @Timed
    public ResponseEntity<Avaliador> getAvaliador(@PathVariable Long id) {
        log.debug("REST request to get Avaliador : {}", id);
        Optional<Avaliador> avaliador = avaliadorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(avaliador);
    }

    /**
     * DELETE  /avaliadors/:id : delete the "id" avaliador.
     *
     * @param id the id of the avaliador to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/avaliadors/{id}")
    @Timed
    public ResponseEntity<Void> deleteAvaliador(@PathVariable Long id) {
        log.debug("REST request to delete Avaliador : {}", id);
        avaliadorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
