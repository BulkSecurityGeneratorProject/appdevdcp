package br.com.lasa.dcpdesconformidades.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.lasa.dcpdesconformidades.service.AvaliacaoService;
import br.com.lasa.dcpdesconformidades.web.rest.errors.BadRequestAlertException;
import br.com.lasa.dcpdesconformidades.web.rest.util.HeaderUtil;
import br.com.lasa.dcpdesconformidades.web.rest.util.PaginationUtil;
import br.com.lasa.dcpdesconformidades.service.dto.AvaliacaoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Avaliacao.
 */
@RestController
@RequestMapping("/api")
public class AvaliacaoResource {

    private final Logger log = LoggerFactory.getLogger(AvaliacaoResource.class);

    private static final String ENTITY_NAME = "avaliacao";

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoResource(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    /**
     * POST  /avaliacaos : Create a new avaliacao.
     *
     * @param avaliacaoDTO the avaliacaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new avaliacaoDTO, or with status 400 (Bad Request) if the avaliacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/avaliacaos")
    @Timed
    public ResponseEntity<AvaliacaoDTO> createAvaliacao(@Valid @RequestBody AvaliacaoDTO avaliacaoDTO) throws URISyntaxException {
        log.debug("REST request to save Avaliacao : {}", avaliacaoDTO);
        if (avaliacaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new avaliacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AvaliacaoDTO result = avaliacaoService.save(avaliacaoDTO);
        return ResponseEntity.created(new URI("/api/avaliacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /avaliacaos : Updates an existing avaliacao.
     *
     * @param avaliacaoDTO the avaliacaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated avaliacaoDTO,
     * or with status 400 (Bad Request) if the avaliacaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the avaliacaoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/avaliacaos")
    @Timed
    public ResponseEntity<AvaliacaoDTO> updateAvaliacao(@Valid @RequestBody AvaliacaoDTO avaliacaoDTO) throws URISyntaxException {
        log.debug("REST request to update Avaliacao : {}", avaliacaoDTO);
        if (avaliacaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AvaliacaoDTO result = avaliacaoService.save(avaliacaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, avaliacaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /avaliacaos : get all the avaliacaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of avaliacaos in body
     */
    @GetMapping("/avaliacaos")
    @Timed
    public ResponseEntity<List<AvaliacaoDTO>> getAllAvaliacaos(Pageable pageable) {
        log.debug("REST request to get a page of Avaliacaos");
        Page<AvaliacaoDTO> page = avaliacaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/avaliacaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /avaliacaos/:id : get the "id" avaliacao.
     *
     * @param id the id of the avaliacaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the avaliacaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/avaliacaos/{id}")
    @Timed
    public ResponseEntity<AvaliacaoDTO> getAvaliacao(@PathVariable Long id) {
        log.debug("REST request to get Avaliacao : {}", id);
        Optional<AvaliacaoDTO> avaliacaoDTO = avaliacaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(avaliacaoDTO);
    }

    /**
     * DELETE  /avaliacaos/:id : delete the "id" avaliacao.
     *
     * @param id the id of the avaliacaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/avaliacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable Long id) {
        log.debug("REST request to delete Avaliacao : {}", id);
        avaliacaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
