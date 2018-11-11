package br.com.lasa.dcpdesconformidades.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.lasa.dcpdesconformidades.domain.ItemAvaliado;
import br.com.lasa.dcpdesconformidades.repository.ItemAvaliadoRepository;
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
 * REST controller for managing ItemAvaliado.
 */
@RestController
@RequestMapping("/api")
public class ItemAvaliadoResource {

    private final Logger log = LoggerFactory.getLogger(ItemAvaliadoResource.class);

    private static final String ENTITY_NAME = "itemAvaliado";

    private final ItemAvaliadoRepository itemAvaliadoRepository;

    public ItemAvaliadoResource(ItemAvaliadoRepository itemAvaliadoRepository) {
        this.itemAvaliadoRepository = itemAvaliadoRepository;
    }

    /**
     * POST  /item-avaliados : Create a new itemAvaliado.
     *
     * @param itemAvaliado the itemAvaliado to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemAvaliado, or with status 400 (Bad Request) if the itemAvaliado has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-avaliados")
    @Timed
    public ResponseEntity<ItemAvaliado> createItemAvaliado(@Valid @RequestBody ItemAvaliado itemAvaliado) throws URISyntaxException {
        log.debug("REST request to save ItemAvaliado : {}", itemAvaliado);
        if (itemAvaliado.getId() != null) {
            throw new BadRequestAlertException("A new itemAvaliado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemAvaliado result = itemAvaliadoRepository.save(itemAvaliado);
        return ResponseEntity.created(new URI("/api/item-avaliados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-avaliados : Updates an existing itemAvaliado.
     *
     * @param itemAvaliado the itemAvaliado to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemAvaliado,
     * or with status 400 (Bad Request) if the itemAvaliado is not valid,
     * or with status 500 (Internal Server Error) if the itemAvaliado couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-avaliados")
    @Timed
    public ResponseEntity<ItemAvaliado> updateItemAvaliado(@Valid @RequestBody ItemAvaliado itemAvaliado) throws URISyntaxException {
        log.debug("REST request to update ItemAvaliado : {}", itemAvaliado);
        if (itemAvaliado.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemAvaliado result = itemAvaliadoRepository.save(itemAvaliado);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemAvaliado.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-avaliados : get all the itemAvaliados.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of itemAvaliados in body
     */
    @GetMapping("/item-avaliados")
    @Timed
    public List<ItemAvaliado> getAllItemAvaliados() {
        log.debug("REST request to get all ItemAvaliados");
        return itemAvaliadoRepository.findAll();
    }

    /**
     * GET  /item-avaliados/:id : get the "id" itemAvaliado.
     *
     * @param id the id of the itemAvaliado to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemAvaliado, or with status 404 (Not Found)
     */
    @GetMapping("/item-avaliados/{id}")
    @Timed
    public ResponseEntity<ItemAvaliado> getItemAvaliado(@PathVariable Long id) {
        log.debug("REST request to get ItemAvaliado : {}", id);
        Optional<ItemAvaliado> itemAvaliado = itemAvaliadoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(itemAvaliado);
    }

    /**
     * DELETE  /item-avaliados/:id : delete the "id" itemAvaliado.
     *
     * @param id the id of the itemAvaliado to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-avaliados/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemAvaliado(@PathVariable Long id) {
        log.debug("REST request to delete ItemAvaliado : {}", id);

        itemAvaliadoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
