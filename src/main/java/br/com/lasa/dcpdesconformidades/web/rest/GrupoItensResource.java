package br.com.lasa.dcpdesconformidades.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.lasa.dcpdesconformidades.domain.GrupoItens;
import br.com.lasa.dcpdesconformidades.repository.GrupoItensRepository;
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
 * REST controller for managing GrupoItens.
 */
@RestController
@RequestMapping("/api")
public class GrupoItensResource {

    private final Logger log = LoggerFactory.getLogger(GrupoItensResource.class);

    private static final String ENTITY_NAME = "grupoItens";

    private final GrupoItensRepository grupoItensRepository;

    public GrupoItensResource(GrupoItensRepository grupoItensRepository) {
        this.grupoItensRepository = grupoItensRepository;
    }

    /**
     * POST  /grupo-itens : Create a new grupoItens.
     *
     * @param grupoItens the grupoItens to create
     * @return the ResponseEntity with status 201 (Created) and with body the new grupoItens, or with status 400 (Bad Request) if the grupoItens has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/grupo-itens")
    @Timed
    public ResponseEntity<GrupoItens> createGrupoItens(@Valid @RequestBody GrupoItens grupoItens) throws URISyntaxException {
        log.debug("REST request to save GrupoItens : {}", grupoItens);
        if (grupoItens.getId() != null) {
            throw new BadRequestAlertException("A new grupoItens cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GrupoItens result = grupoItensRepository.save(grupoItens);
        return ResponseEntity.created(new URI("/api/grupo-itens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /grupo-itens : Updates an existing grupoItens.
     *
     * @param grupoItens the grupoItens to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated grupoItens,
     * or with status 400 (Bad Request) if the grupoItens is not valid,
     * or with status 500 (Internal Server Error) if the grupoItens couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/grupo-itens")
    @Timed
    public ResponseEntity<GrupoItens> updateGrupoItens(@Valid @RequestBody GrupoItens grupoItens) throws URISyntaxException {
        log.debug("REST request to update GrupoItens : {}", grupoItens);
        if (grupoItens.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GrupoItens result = grupoItensRepository.save(grupoItens);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, grupoItens.getId().toString()))
            .body(result);
    }

    /**
     * GET  /grupo-itens : get all the grupoItens.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of grupoItens in body
     */
    @GetMapping("/grupo-itens")
    @Timed
    public List<GrupoItens> getAllGrupoItens(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all GrupoItens");
        return grupoItensRepository.findAllWithEagerRelationships();
    }

    /**
     * GET  /grupo-itens/:id : get the "id" grupoItens.
     *
     * @param id the id of the grupoItens to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the grupoItens, or with status 404 (Not Found)
     */
    @GetMapping("/grupo-itens/{id}")
    @Timed
    public ResponseEntity<GrupoItens> getGrupoItens(@PathVariable Long id) {
        log.debug("REST request to get GrupoItens : {}", id);
        Optional<GrupoItens> grupoItens = grupoItensRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(grupoItens);
    }

    /**
     * DELETE  /grupo-itens/:id : delete the "id" grupoItens.
     *
     * @param id the id of the grupoItens to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/grupo-itens/{id}")
    @Timed
    public ResponseEntity<Void> deleteGrupoItens(@PathVariable Long id) {
        log.debug("REST request to delete GrupoItens : {}", id);

        grupoItensRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
