package br.com.lasa.dcpdesconformidades.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.lasa.dcpdesconformidades.domain.PerdaQuebraAcumuladosAnoLoja;
import br.com.lasa.dcpdesconformidades.repository.PerdaQuebraAcumuladosAnoLojaRepository;
import br.com.lasa.dcpdesconformidades.web.rest.errors.BadRequestAlertException;
import br.com.lasa.dcpdesconformidades.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PerdaQuebraAcumuladosAnoLoja.
 */
@RestController
@RequestMapping("/api")
public class PerdaQuebraAcumuladosAnoLojaResource {

    private final Logger log = LoggerFactory.getLogger(PerdaQuebraAcumuladosAnoLojaResource.class);

    private static final String ENTITY_NAME = "perdaQuebraAcumuladosAnoLoja";

    private final PerdaQuebraAcumuladosAnoLojaRepository perdaQuebraAcumuladosAnoLojaRepository;

    public PerdaQuebraAcumuladosAnoLojaResource(PerdaQuebraAcumuladosAnoLojaRepository perdaQuebraAcumuladosAnoLojaRepository) {
        this.perdaQuebraAcumuladosAnoLojaRepository = perdaQuebraAcumuladosAnoLojaRepository;
    }

    /**
     * POST  /perda-quebra-acumulados-ano-lojas : Create a new perdaQuebraAcumuladosAnoLoja.
     *
     * @param perdaQuebraAcumuladosAnoLoja the perdaQuebraAcumuladosAnoLoja to create
     * @return the ResponseEntity with status 201 (Created) and with body the new perdaQuebraAcumuladosAnoLoja, or with status 400 (Bad Request) if the perdaQuebraAcumuladosAnoLoja has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/perda-quebra-acumulados-ano-lojas")
    @Timed
    public ResponseEntity<PerdaQuebraAcumuladosAnoLoja> createPerdaQuebraAcumuladosAnoLoja(@Valid @RequestBody PerdaQuebraAcumuladosAnoLoja perdaQuebraAcumuladosAnoLoja) throws URISyntaxException {
        log.debug("REST request to save PerdaQuebraAcumuladosAnoLoja : {}", perdaQuebraAcumuladosAnoLoja);
        if (perdaQuebraAcumuladosAnoLoja.getId() != null) {
            throw new BadRequestAlertException("A new perdaQuebraAcumuladosAnoLoja cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PerdaQuebraAcumuladosAnoLoja result = perdaQuebraAcumuladosAnoLojaRepository.save(perdaQuebraAcumuladosAnoLoja);
        return ResponseEntity.created(new URI("/api/perda-quebra-acumulados-ano-lojas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /perda-quebra-acumulados-ano-lojas : Updates an existing perdaQuebraAcumuladosAnoLoja.
     *
     * @param perdaQuebraAcumuladosAnoLoja the perdaQuebraAcumuladosAnoLoja to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated perdaQuebraAcumuladosAnoLoja,
     * or with status 400 (Bad Request) if the perdaQuebraAcumuladosAnoLoja is not valid,
     * or with status 500 (Internal Server Error) if the perdaQuebraAcumuladosAnoLoja couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/perda-quebra-acumulados-ano-lojas")
    @Timed
    public ResponseEntity<PerdaQuebraAcumuladosAnoLoja> updatePerdaQuebraAcumuladosAnoLoja(@Valid @RequestBody PerdaQuebraAcumuladosAnoLoja perdaQuebraAcumuladosAnoLoja) throws URISyntaxException {
        log.debug("REST request to update PerdaQuebraAcumuladosAnoLoja : {}", perdaQuebraAcumuladosAnoLoja);
        if (perdaQuebraAcumuladosAnoLoja.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PerdaQuebraAcumuladosAnoLoja result = perdaQuebraAcumuladosAnoLojaRepository.save(perdaQuebraAcumuladosAnoLoja);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, perdaQuebraAcumuladosAnoLoja.getId().toString()))
            .body(result);
    }

    /**
     * GET  /perda-quebra-acumulados-ano-lojas : get all the perdaQuebraAcumuladosAnoLojas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of perdaQuebraAcumuladosAnoLojas in body
     */
    @GetMapping("/perda-quebra-acumulados-ano-lojas")
    @Timed
    public List<PerdaQuebraAcumuladosAnoLoja> getAllPerdaQuebraAcumuladosAnoLojas() {
        log.debug("REST request to get all PerdaQuebraAcumuladosAnoLojas");
        return perdaQuebraAcumuladosAnoLojaRepository.findAll();
    }

    /**
     * GET  /perda-quebra-acumulados-ano-lojas/:id : get the "id" perdaQuebraAcumuladosAnoLoja.
     *
     * @param id the id of the perdaQuebraAcumuladosAnoLoja to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the perdaQuebraAcumuladosAnoLoja, or with status 404 (Not Found)
     */
    @GetMapping("/perda-quebra-acumulados-ano-lojas/{id}")
    @Timed
    public ResponseEntity<PerdaQuebraAcumuladosAnoLoja> getPerdaQuebraAcumuladosAnoLoja(@PathVariable Long id) {
        log.debug("REST request to get PerdaQuebraAcumuladosAnoLoja : {}", id);
        Optional<PerdaQuebraAcumuladosAnoLoja> perdaQuebraAcumuladosAnoLoja = perdaQuebraAcumuladosAnoLojaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(perdaQuebraAcumuladosAnoLoja);
    }

    /**
     * DELETE  /perda-quebra-acumulados-ano-lojas/:id : delete the "id" perdaQuebraAcumuladosAnoLoja.
     *
     * @param id the id of the perdaQuebraAcumuladosAnoLoja to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/perda-quebra-acumulados-ano-lojas/{id}")
    @Timed
    public ResponseEntity<Void> deletePerdaQuebraAcumuladosAnoLoja(@PathVariable Long id) {
        log.debug("REST request to delete PerdaQuebraAcumuladosAnoLoja : {}", id);

        perdaQuebraAcumuladosAnoLojaRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
