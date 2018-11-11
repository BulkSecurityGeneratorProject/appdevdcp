package br.com.lasa.dcpdesconformidades.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.lasa.dcpdesconformidades.service.ItemAvaliacaoService;
import br.com.lasa.dcpdesconformidades.web.rest.errors.BadRequestAlertException;
import br.com.lasa.dcpdesconformidades.web.rest.util.HeaderUtil;
import br.com.lasa.dcpdesconformidades.service.dto.ItemAvaliacaoDTO;
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
 * REST controller for managing ItemAvaliacao.
 */
@RestController
@RequestMapping("/api")
public class ItemAvaliacaoResource {

    private final Logger log = LoggerFactory.getLogger(ItemAvaliacaoResource.class);

    private static final String ENTITY_NAME = "itemAvaliacao";

    private final ItemAvaliacaoService itemAvaliacaoService;

    public ItemAvaliacaoResource(ItemAvaliacaoService itemAvaliacaoService) {
        this.itemAvaliacaoService = itemAvaliacaoService;
    }

    /**
     * POST  /item-avaliacaos : Create a new itemAvaliacao.
     *
     * @param itemAvaliacaoDTO the itemAvaliacaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemAvaliacaoDTO, or with status 400 (Bad Request) if the itemAvaliacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-avaliacaos")
    @Timed
    public ResponseEntity<ItemAvaliacaoDTO> createItemAvaliacao(@Valid @RequestBody ItemAvaliacaoDTO itemAvaliacaoDTO) throws URISyntaxException {
        log.debug("REST request to save ItemAvaliacao : {}", itemAvaliacaoDTO);
        if (itemAvaliacaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemAvaliacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemAvaliacaoDTO result = itemAvaliacaoService.save(itemAvaliacaoDTO);
        return ResponseEntity.created(new URI("/api/item-avaliacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-avaliacaos : Updates an existing itemAvaliacao.
     *
     * @param itemAvaliacaoDTO the itemAvaliacaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemAvaliacaoDTO,
     * or with status 400 (Bad Request) if the itemAvaliacaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the itemAvaliacaoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-avaliacaos")
    @Timed
    public ResponseEntity<ItemAvaliacaoDTO> updateItemAvaliacao(@Valid @RequestBody ItemAvaliacaoDTO itemAvaliacaoDTO) throws URISyntaxException {
        log.debug("REST request to update ItemAvaliacao : {}", itemAvaliacaoDTO);
        if (itemAvaliacaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemAvaliacaoDTO result = itemAvaliacaoService.save(itemAvaliacaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemAvaliacaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-avaliacaos : get all the itemAvaliacaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of itemAvaliacaos in body
     */
    @GetMapping("/item-avaliacaos")
    @Timed
    public List<ItemAvaliacaoDTO> getAllItemAvaliacaos() {
        log.debug("REST request to get all ItemAvaliacaos");
        return itemAvaliacaoService.findAll();
    }

    /**
     * GET  /item-avaliacaos/:id : get the "id" itemAvaliacao.
     *
     * @param id the id of the itemAvaliacaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemAvaliacaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/item-avaliacaos/{id}")
    @Timed
    public ResponseEntity<ItemAvaliacaoDTO> getItemAvaliacao(@PathVariable Long id) {
        log.debug("REST request to get ItemAvaliacao : {}", id);
        Optional<ItemAvaliacaoDTO> itemAvaliacaoDTO = itemAvaliacaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemAvaliacaoDTO);
    }

    /**
     * DELETE  /item-avaliacaos/:id : delete the "id" itemAvaliacao.
     *
     * @param id the id of the itemAvaliacaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-avaliacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemAvaliacao(@PathVariable Long id) {
        log.debug("REST request to delete ItemAvaliacao : {}", id);
        itemAvaliacaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
