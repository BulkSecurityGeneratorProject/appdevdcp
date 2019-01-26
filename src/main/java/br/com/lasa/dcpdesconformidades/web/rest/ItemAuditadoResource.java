package br.com.lasa.dcpdesconformidades.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.lasa.dcpdesconformidades.service.ItemAuditadoService;
import br.com.lasa.dcpdesconformidades.web.rest.errors.BadRequestAlertException;
import br.com.lasa.dcpdesconformidades.web.rest.util.HeaderUtil;
import br.com.lasa.dcpdesconformidades.service.dto.ItemAuditadoDTO;
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

    private final ItemAuditadoService itemAuditadoService;

    public ItemAuditadoResource(ItemAuditadoService itemAuditadoService) {
        this.itemAuditadoService = itemAuditadoService;
    }

    /**
     * POST  /item-auditados : Create a new itemAuditado.
     *
     * @param itemAuditadoDTO the itemAuditadoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemAuditadoDTO, or with status 400 (Bad Request) if the itemAuditado has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-auditados")
    @Timed
    public ResponseEntity<ItemAuditadoDTO> createItemAuditado(@Valid @RequestBody ItemAuditadoDTO itemAuditadoDTO) throws URISyntaxException {
        log.debug("REST request to save ItemAuditado : {}", itemAuditadoDTO);
        if (itemAuditadoDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemAuditado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemAuditadoDTO result = itemAuditadoService.save(itemAuditadoDTO);
        return ResponseEntity.created(new URI("/api/item-auditados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-auditados : Updates an existing itemAuditado.
     *
     * @param itemAuditadoDTO the itemAuditadoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemAuditadoDTO,
     * or with status 400 (Bad Request) if the itemAuditadoDTO is not valid,
     * or with status 500 (Internal Server Error) if the itemAuditadoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-auditados")
    @Timed
    public ResponseEntity<ItemAuditadoDTO> updateItemAuditado(@Valid @RequestBody ItemAuditadoDTO itemAuditadoDTO) throws URISyntaxException {
        log.debug("REST request to update ItemAuditado : {}", itemAuditadoDTO);
        if (itemAuditadoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemAuditadoDTO result = itemAuditadoService.save(itemAuditadoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemAuditadoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-auditados : get all the itemAuditados.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of itemAuditados in body
     */
    @GetMapping("/item-auditados")
    @Timed
    public List<ItemAuditadoDTO> getAllItemAuditados() {
        log.debug("REST request to get all ItemAuditados");
        return itemAuditadoService.findAll();
    }

    /**
     * GET  /item-auditados/:id : get the "id" itemAuditado.
     *
     * @param id the id of the itemAuditadoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemAuditadoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/item-auditados/{id}")
    @Timed
    public ResponseEntity<ItemAuditadoDTO> getItemAuditado(@PathVariable Long id) {
        log.debug("REST request to get ItemAuditado : {}", id);
        Optional<ItemAuditadoDTO> itemAuditadoDTO = itemAuditadoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemAuditadoDTO);
    }

    /**
     * DELETE  /item-auditados/:id : delete the "id" itemAuditado.
     *
     * @param id the id of the itemAuditadoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-auditados/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemAuditado(@PathVariable Long id) {
        log.debug("REST request to delete ItemAuditado : {}", id);
        itemAuditadoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
