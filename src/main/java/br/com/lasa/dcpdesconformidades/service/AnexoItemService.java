package br.com.lasa.dcpdesconformidades.service;

import br.com.lasa.dcpdesconformidades.domain.AnexoItem;
import br.com.lasa.dcpdesconformidades.repository.AnexoItemRepository;
import br.com.lasa.dcpdesconformidades.service.dto.AnexoItemDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.AnexoItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing AnexoItem.
 */
@Service
@Transactional
public class AnexoItemService {

    private final Logger log = LoggerFactory.getLogger(AnexoItemService.class);

    private final AnexoItemRepository anexoItemRepository;

    private final AnexoItemMapper anexoItemMapper;

    public AnexoItemService(AnexoItemRepository anexoItemRepository, AnexoItemMapper anexoItemMapper) {
        this.anexoItemRepository = anexoItemRepository;
        this.anexoItemMapper = anexoItemMapper;
    }

    /**
     * Save a anexoItem.
     *
     * @param anexoItemDTO the entity to save
     * @return the persisted entity
     */
    public AnexoItemDTO save(AnexoItemDTO anexoItemDTO) {
        log.debug("Request to save AnexoItem : {}", anexoItemDTO);

        AnexoItem anexoItem = anexoItemMapper.toEntity(anexoItemDTO);
        anexoItem = anexoItemRepository.save(anexoItem);
        return anexoItemMapper.toDto(anexoItem);
    }

    /**
     * Get all the anexoItems.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<AnexoItemDTO> findAll() {
        log.debug("Request to get all AnexoItems");
        return anexoItemRepository.findAll().stream()
            .map(anexoItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one anexoItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AnexoItemDTO> findOne(Long id) {
        log.debug("Request to get AnexoItem : {}", id);
        return anexoItemRepository.findById(id)
            .map(anexoItemMapper::toDto);
    }

    /**
     * Delete the anexoItem by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AnexoItem : {}", id);
        anexoItemRepository.deleteById(id);
    }
}
