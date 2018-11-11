package br.com.lasa.dcpdesconformidades.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.lasa.dcpdesconformidades.domain.ItemAvaliadoPerdaQuebraAcumulados;
import br.com.lasa.dcpdesconformidades.repository.ItemAvaliadoPerdaQuebraAcumuladosRepository;
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
 * REST controller for managing ItemAvaliadoPerdaQuebraAcumulados.
 */
@RestController
@RequestMapping("/api")
public class ItemAvaliadoPerdaQuebraAcumuladosResource {

    private final Logger log = LoggerFactory.getLogger(ItemAvaliadoPerdaQuebraAcumuladosResource.class);

    private static final String ENTITY_NAME = "itemAvaliadoPerdaQuebraAcumulados";

    private final ItemAvaliadoPerdaQuebraAcumuladosRepository itemAvaliadoPerdaQuebraAcumuladosRepository;

    public ItemAvaliadoPerdaQuebraAcumuladosResource(ItemAvaliadoPerdaQuebraAcumuladosRepository itemAvaliadoPerdaQuebraAcumuladosRepository) {
        this.itemAvaliadoPerdaQuebraAcumuladosRepository = itemAvaliadoPerdaQuebraAcumuladosRepository;
    }

    /**
     * POST  /item-avaliado-perda-quebra-acumulados : Create a new itemAvaliadoPerdaQuebraAcumulados.
     *
     * @param itemAvaliadoPerdaQuebraAcumulados the itemAvaliadoPerdaQuebraAcumulados to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemAvaliadoPerdaQuebraAcumulados, or with status 400 (Bad Request) if the itemAvaliadoPerdaQuebraAcumulados has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-avaliado-perda-quebra-acumulados")
    @Timed
    public ResponseEntity<ItemAvaliadoPerdaQuebraAcumulados> createItemAvaliadoPerdaQuebraAcumulados(@Valid @RequestBody ItemAvaliadoPerdaQuebraAcumulados itemAvaliadoPerdaQuebraAcumulados) throws URISyntaxException {
        log.debug("REST request to save ItemAvaliadoPerdaQuebraAcumulados : {}", itemAvaliadoPerdaQuebraAcumulados);
        if (itemAvaliadoPerdaQuebraAcumulados.getId() != null) {
            throw new BadRequestAlertException("A new itemAvaliadoPerdaQuebraAcumulados cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemAvaliadoPerdaQuebraAcumulados result = itemAvaliadoPerdaQuebraAcumuladosRepository.save(itemAvaliadoPerdaQuebraAcumulados);
        return ResponseEntity.created(new URI("/api/item-avaliado-perda-quebra-acumulados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-avaliado-perda-quebra-acumulados : Updates an existing itemAvaliadoPerdaQuebraAcumulados.
     *
     * @param itemAvaliadoPerdaQuebraAcumulados the itemAvaliadoPerdaQuebraAcumulados to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemAvaliadoPerdaQuebraAcumulados,
     * or with status 400 (Bad Request) if the itemAvaliadoPerdaQuebraAcumulados is not valid,
     * or with status 500 (Internal Server Error) if the itemAvaliadoPerdaQuebraAcumulados couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-avaliado-perda-quebra-acumulados")
    @Timed
    public ResponseEntity<ItemAvaliadoPerdaQuebraAcumulados> updateItemAvaliadoPerdaQuebraAcumulados(@Valid @RequestBody ItemAvaliadoPerdaQuebraAcumulados itemAvaliadoPerdaQuebraAcumulados) throws URISyntaxException {
        log.debug("REST request to update ItemAvaliadoPerdaQuebraAcumulados : {}", itemAvaliadoPerdaQuebraAcumulados);
        if (itemAvaliadoPerdaQuebraAcumulados.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemAvaliadoPerdaQuebraAcumulados result = itemAvaliadoPerdaQuebraAcumuladosRepository.save(itemAvaliadoPerdaQuebraAcumulados);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemAvaliadoPerdaQuebraAcumulados.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-avaliado-perda-quebra-acumulados : get all the itemAvaliadoPerdaQuebraAcumulados.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of itemAvaliadoPerdaQuebraAcumulados in body
     */
    @GetMapping("/item-avaliado-perda-quebra-acumulados")
    @Timed
    public List<ItemAvaliadoPerdaQuebraAcumulados> getAllItemAvaliadoPerdaQuebraAcumulados() {
        log.debug("REST request to get all ItemAvaliadoPerdaQuebraAcumulados");
        return itemAvaliadoPerdaQuebraAcumuladosRepository.findAll();
    }

    /**
     * GET  /item-avaliado-perda-quebra-acumulados/:id : get the "id" itemAvaliadoPerdaQuebraAcumulados.
     *
     * @param id the id of the itemAvaliadoPerdaQuebraAcumulados to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemAvaliadoPerdaQuebraAcumulados, or with status 404 (Not Found)
     */
    @GetMapping("/item-avaliado-perda-quebra-acumulados/{id}")
    @Timed
    public ResponseEntity<ItemAvaliadoPerdaQuebraAcumulados> getItemAvaliadoPerdaQuebraAcumulados(@PathVariable Long id) {
        log.debug("REST request to get ItemAvaliadoPerdaQuebraAcumulados : {}", id);
        Optional<ItemAvaliadoPerdaQuebraAcumulados> itemAvaliadoPerdaQuebraAcumulados = itemAvaliadoPerdaQuebraAcumuladosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(itemAvaliadoPerdaQuebraAcumulados);
    }

    /**
     * DELETE  /item-avaliado-perda-quebra-acumulados/:id : delete the "id" itemAvaliadoPerdaQuebraAcumulados.
     *
     * @param id the id of the itemAvaliadoPerdaQuebraAcumulados to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-avaliado-perda-quebra-acumulados/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemAvaliadoPerdaQuebraAcumulados(@PathVariable Long id) {
        log.debug("REST request to delete ItemAvaliadoPerdaQuebraAcumulados : {}", id);

        itemAvaliadoPerdaQuebraAcumuladosRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
