package br.com.lasa.dcpdesconformidades.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.lasa.dcpdesconformidades.domain.ItemSolicitadoAjuste;
import br.com.lasa.dcpdesconformidades.repository.ItemSolicitadoAjusteRepository;
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
 * REST controller for managing ItemSolicitadoAjuste.
 */
@RestController
@RequestMapping("/api")
public class ItemSolicitadoAjusteResource {

    private final Logger log = LoggerFactory.getLogger(ItemSolicitadoAjusteResource.class);

    private static final String ENTITY_NAME = "itemSolicitadoAjuste";

    private final ItemSolicitadoAjusteRepository itemSolicitadoAjusteRepository;

    public ItemSolicitadoAjusteResource(ItemSolicitadoAjusteRepository itemSolicitadoAjusteRepository) {
        this.itemSolicitadoAjusteRepository = itemSolicitadoAjusteRepository;
    }

    /**
     * POST  /item-solicitado-ajustes : Create a new itemSolicitadoAjuste.
     *
     * @param itemSolicitadoAjuste the itemSolicitadoAjuste to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemSolicitadoAjuste, or with status 400 (Bad Request) if the itemSolicitadoAjuste has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-solicitado-ajustes")
    @Timed
    public ResponseEntity<ItemSolicitadoAjuste> createItemSolicitadoAjuste(@Valid @RequestBody ItemSolicitadoAjuste itemSolicitadoAjuste) throws URISyntaxException {
        log.debug("REST request to save ItemSolicitadoAjuste : {}", itemSolicitadoAjuste);
        if (itemSolicitadoAjuste.getId() != null) {
            throw new BadRequestAlertException("A new itemSolicitadoAjuste cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemSolicitadoAjuste result = itemSolicitadoAjusteRepository.save(itemSolicitadoAjuste);
        return ResponseEntity.created(new URI("/api/item-solicitado-ajustes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-solicitado-ajustes : Updates an existing itemSolicitadoAjuste.
     *
     * @param itemSolicitadoAjuste the itemSolicitadoAjuste to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemSolicitadoAjuste,
     * or with status 400 (Bad Request) if the itemSolicitadoAjuste is not valid,
     * or with status 500 (Internal Server Error) if the itemSolicitadoAjuste couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-solicitado-ajustes")
    @Timed
    public ResponseEntity<ItemSolicitadoAjuste> updateItemSolicitadoAjuste(@Valid @RequestBody ItemSolicitadoAjuste itemSolicitadoAjuste) throws URISyntaxException {
        log.debug("REST request to update ItemSolicitadoAjuste : {}", itemSolicitadoAjuste);
        if (itemSolicitadoAjuste.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemSolicitadoAjuste result = itemSolicitadoAjusteRepository.save(itemSolicitadoAjuste);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemSolicitadoAjuste.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-solicitado-ajustes : get all the itemSolicitadoAjustes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of itemSolicitadoAjustes in body
     */
    @GetMapping("/item-solicitado-ajustes")
    @Timed
    public List<ItemSolicitadoAjuste> getAllItemSolicitadoAjustes() {
        log.debug("REST request to get all ItemSolicitadoAjustes");
        return itemSolicitadoAjusteRepository.findAll();
    }

    /**
     * GET  /item-solicitado-ajustes/:id : get the "id" itemSolicitadoAjuste.
     *
     * @param id the id of the itemSolicitadoAjuste to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemSolicitadoAjuste, or with status 404 (Not Found)
     */
    @GetMapping("/item-solicitado-ajustes/{id}")
    @Timed
    public ResponseEntity<ItemSolicitadoAjuste> getItemSolicitadoAjuste(@PathVariable Long id) {
        log.debug("REST request to get ItemSolicitadoAjuste : {}", id);
        Optional<ItemSolicitadoAjuste> itemSolicitadoAjuste = itemSolicitadoAjusteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(itemSolicitadoAjuste);
    }

    /**
     * DELETE  /item-solicitado-ajustes/:id : delete the "id" itemSolicitadoAjuste.
     *
     * @param id the id of the itemSolicitadoAjuste to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-solicitado-ajustes/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemSolicitadoAjuste(@PathVariable Long id) {
        log.debug("REST request to delete ItemSolicitadoAjuste : {}", id);

        itemSolicitadoAjusteRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
