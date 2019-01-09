package br.com.lasa.dcpdesconformidades.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.lasa.dcpdesconformidades.service.AvaliacaoService;
import br.com.lasa.dcpdesconformidades.service.dto.AvaliacaoDTO;
import br.com.lasa.dcpdesconformidades.web.rest.errors.BadRequestAlertException;
import br.com.lasa.dcpdesconformidades.web.rest.util.HeaderUtil;
import br.com.lasa.dcpdesconformidades.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

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
    @PostMapping("/avaliacaos/{id}")
    @Timed
    public ResponseEntity<Void> cancelAvaliacao(@PathVariable Long id, @RequestBody AvaliacaoDTO avaliacaoDTO) {
        log.debug("REST request to delete Avaliacao : {}", id);
        avaliacaoService.cancel(id, avaliacaoDTO.getMotivoCancelamento());
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
        
    @GetMapping("/avaliacaos/{codigoLoja}/iniciar")
    @Timed
    public AvaliacaoDTO iniciarAvaliacaoPara(@PathVariable String codigoLoja) {
        log.debug("REST request to iniciar Avaliacao : {}", codigoLoja);
        AvaliacaoDTO avaliacaoDTO = avaliacaoService.iniciarAvaliacaoPara(codigoLoja);
        return avaliacaoDTO;
    }
    
    @PostMapping("/avaliacaos/{codigoLoja}/iniciar")
    @Timed
    public AvaliacaoDTO submeterAvaliacaoPara(@Valid @RequestBody AvaliacaoDTO avaliacaoDTO) {
        log.debug("REST request to submeter Avaliacao : {}", avaliacaoDTO);
        return avaliacaoService.submeterAvaliacao(avaliacaoDTO);
    }
}
