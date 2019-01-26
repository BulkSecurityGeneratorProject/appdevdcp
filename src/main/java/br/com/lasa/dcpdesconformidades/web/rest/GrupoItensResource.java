package br.com.lasa.dcpdesconformidades.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.lasa.dcpdesconformidades.service.GrupoItensService;
import br.com.lasa.dcpdesconformidades.web.rest.errors.BadRequestAlertException;
import br.com.lasa.dcpdesconformidades.web.rest.util.HeaderUtil;
import br.com.lasa.dcpdesconformidades.service.dto.GrupoItensDTO;
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
 * REST controller for managing GrupoItens.
 */
@RestController
@RequestMapping("/api")
public class GrupoItensResource {

    private final Logger log = LoggerFactory.getLogger(GrupoItensResource.class);

    private static final String ENTITY_NAME = "grupoItens";

    private final GrupoItensService grupoItensService;

    public GrupoItensResource(GrupoItensService grupoItensService) {
        this.grupoItensService = grupoItensService;
    }

    /**
     * POST  /grupo-itens : Create a new grupoItens.
     *
     * @param grupoItensDTO the grupoItensDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new grupoItensDTO, or with status 400 (Bad Request) if the grupoItens has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/grupo-itens")
    @Timed
    public ResponseEntity<GrupoItensDTO> createGrupoItens(@Valid @RequestBody GrupoItensDTO grupoItensDTO) throws URISyntaxException {
        log.debug("REST request to save GrupoItens : {}", grupoItensDTO);
        if (grupoItensDTO.getId() != null) {
            throw new BadRequestAlertException("A new grupoItens cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GrupoItensDTO result = grupoItensService.save(grupoItensDTO);
        return ResponseEntity.created(new URI("/api/grupo-itens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getNome()))
            .body(result);
    }

    /**
     * PUT  /grupo-itens : Updates an existing grupoItens.
     *
     * @param grupoItensDTO the grupoItensDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated grupoItensDTO,
     * or with status 400 (Bad Request) if the grupoItensDTO is not valid,
     * or with status 500 (Internal Server Error) if the grupoItensDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/grupo-itens")
    @Timed
    public ResponseEntity<GrupoItensDTO> updateGrupoItens(@Valid @RequestBody GrupoItensDTO grupoItensDTO) throws URISyntaxException {
        log.debug("REST request to update GrupoItens : {}", grupoItensDTO);
        if (grupoItensDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GrupoItensDTO result = grupoItensService.save(grupoItensDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, grupoItensDTO.getNome()))
            .body(result);
    }

    /**
     * GET  /grupo-itens : get all the grupoItens.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of grupoItens in body
     */
    @GetMapping("/grupo-itens")
    @Timed
    public List<GrupoItensDTO> getAllGrupoItens() {
        log.debug("REST request to get all GrupoItens");
        return grupoItensService.findAll();
    }

    /**
     * GET  /grupo-itens/:id : get the "id" grupoItens.
     *
     * @param id the id of the grupoItensDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the grupoItensDTO, or with status 404 (Not Found)
     */
    @GetMapping("/grupo-itens/{id}")
    @Timed
    public ResponseEntity<GrupoItensDTO> getGrupoItens(@PathVariable Long id) {
        log.debug("REST request to get GrupoItens : {}", id);
        Optional<GrupoItensDTO> grupoItensDTO = grupoItensService.findOne(id);
        return ResponseUtil.wrapOrNotFound(grupoItensDTO);
    }

    /**
     * DELETE  /grupo-itens/:id : delete the "id" grupoItens.
     *
     * @param id the id of the grupoItensDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/grupo-itens/{id}")
    @Timed
    public ResponseEntity<Void> deleteGrupoItens(@PathVariable Long id) {
        log.debug("REST request to delete GrupoItens : {}", id);
        grupoItensService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
