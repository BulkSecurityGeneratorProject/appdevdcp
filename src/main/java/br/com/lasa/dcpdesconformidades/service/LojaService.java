package br.com.lasa.dcpdesconformidades.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lasa.dcpdesconformidades.domain.Loja;
import br.com.lasa.dcpdesconformidades.domain.User;
import br.com.lasa.dcpdesconformidades.repository.LojaRepository;
import br.com.lasa.dcpdesconformidades.service.dto.LojaDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.LojaMapper;

/**
 * Service Implementation for managing Loja.
 */
@Service
@Transactional
public class LojaService {

    private final Logger log = LoggerFactory.getLogger(LojaService.class);

    private final LojaRepository lojaRepository;

    private final LojaMapper lojaMapper;

    public LojaService(LojaRepository lojaRepository, LojaMapper lojaMapper) {
        this.lojaRepository = lojaRepository;
        this.lojaMapper = lojaMapper;
    }

    /**
     * Save a loja.
     *
     * @param lojaDTO the entity to save
     * @return the persisted entity
     */
    public LojaDTO save(LojaDTO lojaDTO) {
        log.debug("Request to save Loja : {}", lojaDTO);

        Loja loja = lojaMapper.toEntity(lojaDTO);
        loja = lojaRepository.save(loja);
        return lojaMapper.toDto(loja);
    }

    /**
     * Get all the lojas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<LojaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Lojas");
        return lojaRepository.findAll(pageable)
            .map(lojaMapper::toDto);
    }

    /**
     * Get all the Loja with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<LojaDTO> findAllWithEagerRelationships(Pageable pageable) {
        return lojaRepository.findAllWithEagerRelationships(pageable).map(lojaMapper::toDto);
    }
    

    /**
     * Get one loja by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<LojaDTO> findOne(Long id) {
        log.debug("Request to get Loja : {}", id);
        return lojaRepository.findOneWithEagerRelationships(id)
            .map(lojaMapper::toDto);
    }

    /**
     * Delete the loja by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Loja : {}", id);
        lojaRepository.deleteById(id);
    }

    public List<LojaDTO> getLojasPermitidasPara(User user) {
        // TODO Auto-generated method stub
        return null;
    }
}
