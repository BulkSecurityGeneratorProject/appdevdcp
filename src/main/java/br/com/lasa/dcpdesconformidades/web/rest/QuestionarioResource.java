package br.com.lasa.dcpdesconformidades.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.lasa.dcpdesconformidades.service.QuestionarioService;
import br.com.lasa.dcpdesconformidades.web.rest.errors.BadRequestAlertException;
import br.com.lasa.dcpdesconformidades.web.rest.util.HeaderUtil;
import br.com.lasa.dcpdesconformidades.service.dto.QuestionarioDTO;
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
 * REST controller for managing Questionario.
 */
@RestController
@RequestMapping("/api")
public class QuestionarioResource {

    private final Logger log = LoggerFactory.getLogger(QuestionarioResource.class);

    private static final String ENTITY_NAME = "questionario";

    private final QuestionarioService questionarioService;

    public QuestionarioResource(QuestionarioService questionarioService) {
        this.questionarioService = questionarioService;
    }

    /**
     * POST  /questionarios : Create a new questionario.
     *
     * @param questionarioDTO the questionarioDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new questionarioDTO, or with status 400 (Bad Request) if the questionario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/questionarios")
    @Timed
    public ResponseEntity<QuestionarioDTO> createQuestionario(@Valid @RequestBody QuestionarioDTO questionarioDTO) throws URISyntaxException {
        log.debug("REST request to save Questionario : {}", questionarioDTO);
        if (questionarioDTO.getId() != null) {
            throw new BadRequestAlertException("A new questionario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuestionarioDTO result = questionarioService.save(questionarioDTO);
        return ResponseEntity.created(new URI("/api/questionarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /questionarios : Updates an existing questionario.
     *
     * @param questionarioDTO the questionarioDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated questionarioDTO,
     * or with status 400 (Bad Request) if the questionarioDTO is not valid,
     * or with status 500 (Internal Server Error) if the questionarioDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/questionarios")
    @Timed
    public ResponseEntity<QuestionarioDTO> updateQuestionario(@Valid @RequestBody QuestionarioDTO questionarioDTO) throws URISyntaxException {
        log.debug("REST request to update Questionario : {}", questionarioDTO);
        if (questionarioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuestionarioDTO result = questionarioService.save(questionarioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, questionarioDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /questionarios : get all the questionarios.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of questionarios in body
     */
    @GetMapping("/questionarios")
    @Timed
    public List<QuestionarioDTO> getAllQuestionarios(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Questionarios");
        return questionarioService.findAll();
    }

    /**
     * GET  /questionarios/:id : get the "id" questionario.
     *
     * @param id the id of the questionarioDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the questionarioDTO, or with status 404 (Not Found)
     */
    @GetMapping("/questionarios/{id}")
    @Timed
    public ResponseEntity<QuestionarioDTO> getQuestionario(@PathVariable Long id) {
        log.debug("REST request to get Questionario : {}", id);
        Optional<QuestionarioDTO> questionarioDTO = questionarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionarioDTO);
    }

    /**
     * DELETE  /questionarios/:id : delete the "id" questionario.
     *
     * @param id the id of the questionarioDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/questionarios/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuestionario(@PathVariable Long id) {
        log.debug("REST request to delete Questionario : {}", id);
        questionarioService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
