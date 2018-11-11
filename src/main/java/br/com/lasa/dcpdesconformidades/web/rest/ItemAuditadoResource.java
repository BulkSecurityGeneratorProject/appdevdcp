package br.com.lasa.dcpdesconformidades.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.lasa.dcpdesconformidades.domain.ItemAuditado;
import br.com.lasa.dcpdesconformidades.repository.ItemAuditadoRepository;
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
 * REST controller for managing ItemAuditado.
 */
@RestController
@RequestMapping("/api")
public class ItemAuditadoResource {

    private final Logger log = LoggerFactory.getLogger(ItemAuditadoResource.class);

    private static final String ENTITY_NAME = "itemAuditado";

    private final ItemAuditadoRepository itemAuditadoRepository;

    public ItemAuditadoResource(ItemAuditadoRepository itemAuditadoRepository) {
        this.itemAuditadoRepository = itemAuditadoRepository;
    }

    /**
     * POST  /item-auditados : Create a new itemAuditado.
     *
     * @param itemAuditado the itemAuditado to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemAuditado, or with status 400 (Bad Request) if the itemAuditado has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-auditados")
    @Timed
    public ResponseEntity<ItemAuditado> createItemAuditado(@Valid @RequestBody ItemAuditado itemAuditado) throws URISyntaxException {
        log.debug("REST request to save ItemAuditado : {}", itemAuditado);
        if (itemAuditado.getId() != null) {
            throw new BadRequestAlertException("A new itemAuditado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemAuditado result = itemAuditadoRepository.save(itemAuditado);
        return ResponseEntity.created(new URI("/api/item-auditados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-auditados : Updates an existing itemAuditado.
     *
     * @param itemAuditado the itemAuditado to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemAuditado,
     * or with status 400 (Bad Request) if the itemAuditado is not valid,
     * or with status 500 (Internal Server Error) if the itemAuditado couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-auditados")
    @Timed
    public ResponseEntity<ItemAuditado> updateItemAuditado(@Valid @RequestBody ItemAuditado itemAuditado) throws URISyntaxException {
        log.debug("REST request to update ItemAuditado : {}", itemAuditado);
        if (itemAuditado.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemAuditado result = itemAuditadoRepository.save(itemAuditado);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemAuditado.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-auditados : get all the itemAuditados.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of itemAuditados in body
     */
    @GetMapping("/item-auditados")
    @Timed
    public List<ItemAuditado> getAllItemAuditados() {
        log.debug("REST request to get all ItemAuditados");
        return itemAuditadoRepository.findAll();
    }

    /**
     * GET  /item-auditados/:id : get the "id" itemAuditado.
     *
     * @param id the id of the itemAuditado to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemAuditado, or with status 404 (Not Found)
     */
    @GetMapping("/item-auditados/{id}")
    @Timed
    public ResponseEntity<ItemAuditado> getItemAuditado(@PathVariable Long id) {
        log.debug("REST request to get ItemAuditado : {}", id);
        Optional<ItemAuditado> itemAuditado = itemAuditadoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(itemAuditado);
    }

    /**
     * DELETE  /item-auditados/:id : delete the "id" itemAuditado.
     *
     * @param id the id of the itemAuditado to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-auditados/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemAuditado(@PathVariable Long id) {
        log.debug("REST request to delete ItemAuditado : {}", id);

        itemAuditadoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
