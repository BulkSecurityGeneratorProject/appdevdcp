package br.com.lasa.dcpdesconformidades.service;

import br.com.lasa.dcpdesconformidades.domain.Avaliador;
import br.com.lasa.dcpdesconformidades.repository.AvaliadorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Avaliador.
 */
@Service
@Transactional
public class AvaliadorService {

    private final Logger log = LoggerFactory.getLogger(AvaliadorService.class);

    private final AvaliadorRepository avaliadorRepository;

    public AvaliadorService(AvaliadorRepository avaliadorRepository) {
        this.avaliadorRepository = avaliadorRepository;
    }

    /**
     * Save a avaliador.
     *
     * @param avaliador the entity to save
     * @return the persisted entity
     */
    public Avaliador save(Avaliador avaliador) {
        log.debug("Request to save Avaliador : {}", avaliador);
        return avaliadorRepository.save(avaliador);
    }

    /**
     * Get all the avaliadors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Avaliador> findAll(Pageable pageable) {
        log.debug("Request to get all Avaliadors");
        return avaliadorRepository.findAll(pageable);
    }


    /**
     * Get one avaliador by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<Avaliador> findOne(Long id) {
        log.debug("Request to get Avaliador : {}", id);
        return avaliadorRepository.findById(id);
    }

    /**
     * Delete the avaliador by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Avaliador : {}", id);
        avaliadorRepository.deleteById(id);
    }
}
