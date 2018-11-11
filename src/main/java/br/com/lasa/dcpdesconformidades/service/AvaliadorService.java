package br.com.lasa.dcpdesconformidades.service;

import br.com.lasa.dcpdesconformidades.domain.Avaliador;
import br.com.lasa.dcpdesconformidades.repository.AvaliadorRepository;
import br.com.lasa.dcpdesconformidades.service.dto.AvaliadorDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.AvaliadorMapper;
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

    private final AvaliadorMapper avaliadorMapper;

    public AvaliadorService(AvaliadorRepository avaliadorRepository, AvaliadorMapper avaliadorMapper) {
        this.avaliadorRepository = avaliadorRepository;
        this.avaliadorMapper = avaliadorMapper;
    }

    /**
     * Save a avaliador.
     *
     * @param avaliadorDTO the entity to save
     * @return the persisted entity
     */
    public AvaliadorDTO save(AvaliadorDTO avaliadorDTO) {
        log.debug("Request to save Avaliador : {}", avaliadorDTO);

        Avaliador avaliador = avaliadorMapper.toEntity(avaliadorDTO);
        avaliador = avaliadorRepository.save(avaliador);
        return avaliadorMapper.toDto(avaliador);
    }

    /**
     * Get all the avaliadors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AvaliadorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Avaliadors");
        return avaliadorRepository.findAll(pageable)
            .map(avaliadorMapper::toDto);
    }


    /**
     * Get one avaliador by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AvaliadorDTO> findOne(Long id) {
        log.debug("Request to get Avaliador : {}", id);
        return avaliadorRepository.findById(id)
            .map(avaliadorMapper::toDto);
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
