package br.com.lasa.dcpdesconformidades.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.lasa.dcpdesconformidades.domain.AnexoItem;
import br.com.lasa.dcpdesconformidades.service.AnexoItemService;
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
 * REST controller for managing AnexoItem.
 */
@RestController
@RequestMapping("/api")
public class AnexoItemResource {

    private final Logger log = LoggerFactory.getLogger(AnexoItemResource.class);

    private static final String ENTITY_NAME = "anexoItem";

    private final AnexoItemService anexoItemService;

    public AnexoItemResource(AnexoItemService anexoItemService) {
        this.anexoItemService = anexoItemService;
    }

    /**
     * POST  /anexo-items : Create a new anexoItem.
     *
     * @param anexoItem the anexoItem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new anexoItem, or with status 400 (Bad Request) if the anexoItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/anexo-items")
    @Timed
    public ResponseEntity<AnexoItem> createAnexoItem(@Valid @RequestBody AnexoItem anexoItem) throws URISyntaxException {
        log.debug("REST request to save AnexoItem : {}", anexoItem);
        if (anexoItem.getId() != null) {
            throw new BadRequestAlertException("A new anexoItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnexoItem result = anexoItemService.save(anexoItem);
        return ResponseEntity.created(new URI("/api/anexo-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /anexo-items : Updates an existing anexoItem.
     *
     * @param anexoItem the anexoItem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated anexoItem,
     * or with status 400 (Bad Request) if the anexoItem is not valid,
     * or with status 500 (Internal Server Error) if the anexoItem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/anexo-items")
    @Timed
    public ResponseEntity<AnexoItem> updateAnexoItem(@Valid @RequestBody AnexoItem anexoItem) throws URISyntaxException {
        log.debug("REST request to update AnexoItem : {}", anexoItem);
        if (anexoItem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AnexoItem result = anexoItemService.save(anexoItem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, anexoItem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /anexo-items : get all the anexoItems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of anexoItems in body
     */
    @GetMapping("/anexo-items")
    @Timed
    public List<AnexoItem> getAllAnexoItems() {
        log.debug("REST request to get all AnexoItems");
        return anexoItemService.findAll();
    }

    /**
     * GET  /anexo-items/:id : get the "id" anexoItem.
     *
     * @param id the id of the anexoItem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the anexoItem, or with status 404 (Not Found)
     */
    @GetMapping("/anexo-items/{id}")
    @Timed
    public ResponseEntity<AnexoItem> getAnexoItem(@PathVariable Long id) {
        log.debug("REST request to get AnexoItem : {}", id);
        Optional<AnexoItem> anexoItem = anexoItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(anexoItem);
    }

    /**
     * DELETE  /anexo-items/:id : delete the "id" anexoItem.
     *
     * @param id the id of the anexoItem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/anexo-items/{id}")
    @Timed
    public ResponseEntity<Void> deleteAnexoItem(@PathVariable Long id) {
        log.debug("REST request to delete AnexoItem : {}", id);
        anexoItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
