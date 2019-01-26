package br.com.lasa.dcpdesconformidades.service;

import br.com.lasa.dcpdesconformidades.domain.GrupoItens;
import br.com.lasa.dcpdesconformidades.repository.GrupoItensRepository;
import br.com.lasa.dcpdesconformidades.service.dto.GrupoItensDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.GrupoItensMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing GrupoItens.
 */
@Service
@Transactional
public class GrupoItensService {

    private final Logger log = LoggerFactory.getLogger(GrupoItensService.class);

    private final GrupoItensRepository grupoItensRepository;

    private final GrupoItensMapper grupoItensMapper;

    public GrupoItensService(GrupoItensRepository grupoItensRepository, GrupoItensMapper grupoItensMapper) {
        this.grupoItensRepository = grupoItensRepository;
        this.grupoItensMapper = grupoItensMapper;
    }

    /**
     * Save a grupoItens.
     *
     * @param grupoItensDTO the entity to save
     * @return the persisted entity
     */
    public GrupoItensDTO save(GrupoItensDTO grupoItensDTO) {
        log.debug("Request to save GrupoItens : {}", grupoItensDTO);

        GrupoItens grupoItens = grupoItensMapper.toEntity(grupoItensDTO);
        grupoItens = grupoItensRepository.save(grupoItens);
        return grupoItensMapper.toDto(grupoItens);
    }

    /**
     * Get all the grupoItens.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<GrupoItensDTO> findAll() {
        log.debug("Request to get all GrupoItens");
        return grupoItensRepository.findAll().stream()
            .map(grupoItensMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one grupoItens by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<GrupoItensDTO> findOne(Long id) {
        log.debug("Request to get GrupoItens : {}", id);
        return grupoItensRepository.findById(id)
            .map(grupoItensMapper::toDto);
    }

    /**
     * Delete the grupoItens by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete GrupoItens : {}", id);
        grupoItensRepository.deleteById(id);
    }
}
