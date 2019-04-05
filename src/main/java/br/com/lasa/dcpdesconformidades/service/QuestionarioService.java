package br.com.lasa.dcpdesconformidades.service;

import br.com.lasa.dcpdesconformidades.domain.Questionario;
import br.com.lasa.dcpdesconformidades.repository.QuestionarioRepository;
import br.com.lasa.dcpdesconformidades.service.dto.QuestionarioDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.QuestionarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Questionario.
 */
@Service
@Transactional
public class QuestionarioService {

    private final Logger log = LoggerFactory.getLogger(QuestionarioService.class);

    private final QuestionarioRepository questionarioRepository;

    private final QuestionarioMapper questionarioMapper;

    public QuestionarioService(QuestionarioRepository questionarioRepository, QuestionarioMapper questionarioMapper) {
        this.questionarioRepository = questionarioRepository;
        this.questionarioMapper = questionarioMapper;
    }

    /**
     * Save a questionario.
     *
     * @param questionarioDTO the entity to save
     * @return the persisted entity
     */
    public QuestionarioDTO save(QuestionarioDTO questionarioDTO) {
        log.debug("Request to save Questionario : {}", questionarioDTO);

        Questionario questionario = questionarioMapper.toEntity(questionarioDTO);
        questionario = questionarioRepository.save(questionario);
        return questionarioMapper.toDto(questionario);
    }

    /**
     * Get all the questionarios.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<QuestionarioDTO> findAll() {
        log.debug("Request to get all Questionarios");
        return questionarioRepository.findAll().stream()
            .map(questionarioMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one questionario by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<QuestionarioDTO> findOne(Long id) {
        log.debug("Request to get Questionario : {}", id);
        return questionarioRepository.findById(id)
            .map(questionarioMapper::toDto);
    }

    /**
     * Delete the questionario by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Questionario : {}", id);
        questionarioRepository.deleteById(id);
    }

    public Optional<Questionario> buscaQuestionarioAtivo(){
        return questionarioRepository.findAllWithEagerRelationshipsWhereAtivoTrue();
    }
}
