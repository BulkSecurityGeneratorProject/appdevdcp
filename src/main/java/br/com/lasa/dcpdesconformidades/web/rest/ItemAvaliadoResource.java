package br.com.lasa.dcpdesconformidades.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.lasa.dcpdesconformidades.service.ItemAvaliadoService;
import br.com.lasa.dcpdesconformidades.web.rest.errors.BadRequestAlertException;
import br.com.lasa.dcpdesconformidades.web.rest.util.HeaderUtil;
import br.com.lasa.dcpdesconformidades.service.dto.ItemAvaliadoDTO;
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
 * REST controller for managing ItemAvaliado.
 */
@RestController
@RequestMapping("/api")
public class ItemAvaliadoResource {

    private final Logger log = LoggerFactory.getLogger(ItemAvaliadoResource.class);

    private static final String ENTITY_NAME = "itemAvaliado";

    private final ItemAvaliadoService itemAvaliadoService;

    public ItemAvaliadoResource(ItemAvaliadoService itemAvaliadoService) {
        this.itemAvaliadoService = itemAvaliadoService;
    }

    /**
     * POST  /item-avaliados : Create a new itemAvaliado.
     *
     * @param itemAvaliadoDTO the itemAvaliadoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemAvaliadoDTO, or with status 400 (Bad Request) if the itemAvaliado has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-avaliados")
    @Timed
    public ResponseEntity<ItemAvaliadoDTO> createItemAvaliado(@Valid @RequestBody ItemAvaliadoDTO itemAvaliadoDTO) throws URISyntaxException {
        log.debug("REST request to save ItemAvaliado : {}", itemAvaliadoDTO);
        if (itemAvaliadoDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemAvaliado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemAvaliadoDTO result = itemAvaliadoService.save(itemAvaliadoDTO);
        return ResponseEntity.created(new URI("/api/item-avaliados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-avaliados : Updates an existing itemAvaliado.
     *
     * @param itemAvaliadoDTO the itemAvaliadoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemAvaliadoDTO,
     * or with status 400 (Bad Request) if the itemAvaliadoDTO is not valid,
     * or with status 500 (Internal Server Error) if the itemAvaliadoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-avaliados")
    @Timed
    public ResponseEntity<ItemAvaliadoDTO> updateItemAvaliado(@Valid @RequestBody ItemAvaliadoDTO itemAvaliadoDTO) throws URISyntaxException {
        log.debug("REST request to update ItemAvaliado : {}", itemAvaliadoDTO);
        if (itemAvaliadoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemAvaliadoDTO result = itemAvaliadoService.save(itemAvaliadoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemAvaliadoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-avaliados : get all the itemAvaliados.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of itemAvaliados in body
     */
    @GetMapping("/item-avaliados")
    @Timed
    public List<ItemAvaliadoDTO> getAllItemAvaliados() {
        log.debug("REST request to get all ItemAvaliados");
        return itemAvaliadoService.findAll();
    }

    /**
     * GET  /item-avaliados/:id : get the "id" itemAvaliado.
     *
     * @param id the id of the itemAvaliadoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemAvaliadoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/item-avaliados/{id}")
    @Timed
    public ResponseEntity<ItemAvaliadoDTO> getItemAvaliado(@PathVariable Long id) {
        log.debug("REST request to get ItemAvaliado : {}", id);
        Optional<ItemAvaliadoDTO> itemAvaliadoDTO = itemAvaliadoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemAvaliadoDTO);
    }

    /**
     * DELETE  /item-avaliados/:id : delete the "id" itemAvaliado.
     *
     * @param id the id of the itemAvaliadoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-avaliados/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemAvaliado(@PathVariable Long id) {
        log.debug("REST request to delete ItemAvaliado : {}", id);
        itemAvaliadoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
