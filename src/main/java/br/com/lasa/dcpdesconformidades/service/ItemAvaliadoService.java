package br.com.lasa.dcpdesconformidades.service;

import br.com.lasa.dcpdesconformidades.domain.ItemAvaliado;
import br.com.lasa.dcpdesconformidades.repository.ItemAvaliadoRepository;
import br.com.lasa.dcpdesconformidades.service.dto.ItemAvaliadoDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.ItemAvaliadoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ItemAvaliado.
 */
@Service
@Transactional
public class ItemAvaliadoService {

    private final Logger log = LoggerFactory.getLogger(ItemAvaliadoService.class);

    private final ItemAvaliadoRepository itemAvaliadoRepository;

    private final ItemAvaliadoMapper itemAvaliadoMapper;

    public ItemAvaliadoService(ItemAvaliadoRepository itemAvaliadoRepository, ItemAvaliadoMapper itemAvaliadoMapper) {
        this.itemAvaliadoRepository = itemAvaliadoRepository;
        this.itemAvaliadoMapper = itemAvaliadoMapper;
    }

    /**
     * Save a itemAvaliado.
     *
     * @param itemAvaliadoDTO the entity to save
     * @return the persisted entity
     */
    public ItemAvaliadoDTO save(ItemAvaliadoDTO itemAvaliadoDTO) {
        log.debug("Request to save ItemAvaliado : {}", itemAvaliadoDTO);

        ItemAvaliado itemAvaliado = itemAvaliadoMapper.toEntity(itemAvaliadoDTO);
        itemAvaliado = itemAvaliadoRepository.save(itemAvaliado);
        return itemAvaliadoMapper.toDto(itemAvaliado);
    }

    /**
     * Get all the itemAvaliados.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ItemAvaliadoDTO> findAll() {
        log.debug("Request to get all ItemAvaliados");
        return itemAvaliadoRepository.findAll().stream()
            .map(itemAvaliadoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one itemAvaliado by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ItemAvaliadoDTO> findOne(Long id) {
        log.debug("Request to get ItemAvaliado : {}", id);
        return itemAvaliadoRepository.findById(id)
            .map(itemAvaliadoMapper::toDto);
    }

    /**
     * Delete the itemAvaliado by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ItemAvaliado : {}", id);
        itemAvaliadoRepository.deleteById(id);
    }
}
