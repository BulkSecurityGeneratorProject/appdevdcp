package br.com.lasa.dcpdesconformidades.service;

import br.com.lasa.dcpdesconformidades.domain.ItemSolicitadoAjuste;
import br.com.lasa.dcpdesconformidades.repository.ItemSolicitadoAjusteRepository;
import br.com.lasa.dcpdesconformidades.service.dto.ItemSolicitadoAjusteDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.ItemSolicitadoAjusteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ItemSolicitadoAjuste.
 */
@Service
@Transactional
public class ItemSolicitadoAjusteService {

    private final Logger log = LoggerFactory.getLogger(ItemSolicitadoAjusteService.class);

    private final ItemSolicitadoAjusteRepository itemSolicitadoAjusteRepository;

    private final ItemSolicitadoAjusteMapper itemSolicitadoAjusteMapper;

    public ItemSolicitadoAjusteService(ItemSolicitadoAjusteRepository itemSolicitadoAjusteRepository, ItemSolicitadoAjusteMapper itemSolicitadoAjusteMapper) {
        this.itemSolicitadoAjusteRepository = itemSolicitadoAjusteRepository;
        this.itemSolicitadoAjusteMapper = itemSolicitadoAjusteMapper;
    }

    /**
     * Save a itemSolicitadoAjuste.
     *
     * @param itemSolicitadoAjusteDTO the entity to save
     * @return the persisted entity
     */
    public ItemSolicitadoAjusteDTO save(ItemSolicitadoAjusteDTO itemSolicitadoAjusteDTO) {
        log.debug("Request to save ItemSolicitadoAjuste : {}", itemSolicitadoAjusteDTO);

        ItemSolicitadoAjuste itemSolicitadoAjuste = itemSolicitadoAjusteMapper.toEntity(itemSolicitadoAjusteDTO);
        itemSolicitadoAjuste = itemSolicitadoAjusteRepository.save(itemSolicitadoAjuste);
        return itemSolicitadoAjusteMapper.toDto(itemSolicitadoAjuste);
    }

    /**
     * Get all the itemSolicitadoAjustes.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ItemSolicitadoAjusteDTO> findAll() {
        log.debug("Request to get all ItemSolicitadoAjustes");
        return itemSolicitadoAjusteRepository.findAll().stream()
            .map(itemSolicitadoAjusteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one itemSolicitadoAjuste by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ItemSolicitadoAjusteDTO> findOne(Long id) {
        log.debug("Request to get ItemSolicitadoAjuste : {}", id);
        return itemSolicitadoAjusteRepository.findById(id)
            .map(itemSolicitadoAjusteMapper::toDto);
    }

    /**
     * Delete the itemSolicitadoAjuste by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ItemSolicitadoAjuste : {}", id);
        itemSolicitadoAjusteRepository.deleteById(id);
    }
}
