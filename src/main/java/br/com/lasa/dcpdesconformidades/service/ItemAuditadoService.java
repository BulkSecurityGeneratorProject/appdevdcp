package br.com.lasa.dcpdesconformidades.service;

import br.com.lasa.dcpdesconformidades.domain.ItemAuditado;
import br.com.lasa.dcpdesconformidades.repository.ItemAuditadoRepository;
import br.com.lasa.dcpdesconformidades.service.dto.ItemAuditadoDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.ItemAuditadoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ItemAuditado.
 */
@Service
@Transactional
public class ItemAuditadoService {

    private final Logger log = LoggerFactory.getLogger(ItemAuditadoService.class);

    private final ItemAuditadoRepository itemAuditadoRepository;

    private final ItemAuditadoMapper itemAuditadoMapper;

    public ItemAuditadoService(ItemAuditadoRepository itemAuditadoRepository, ItemAuditadoMapper itemAuditadoMapper) {
        this.itemAuditadoRepository = itemAuditadoRepository;
        this.itemAuditadoMapper = itemAuditadoMapper;
    }

    /**
     * Save a itemAuditado.
     *
     * @param itemAuditadoDTO the entity to save
     * @return the persisted entity
     */
    public ItemAuditadoDTO save(ItemAuditadoDTO itemAuditadoDTO) {
        log.debug("Request to save ItemAuditado : {}", itemAuditadoDTO);

        ItemAuditado itemAuditado = itemAuditadoMapper.toEntity(itemAuditadoDTO);
        itemAuditado = itemAuditadoRepository.save(itemAuditado);
        return itemAuditadoMapper.toDto(itemAuditado);
    }

    /**
     * Get all the itemAuditados.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ItemAuditadoDTO> findAll() {
        log.debug("Request to get all ItemAuditados");
        return itemAuditadoRepository.findAll().stream()
            .map(itemAuditadoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one itemAuditado by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ItemAuditadoDTO> findOne(Long id) {
        log.debug("Request to get ItemAuditado : {}", id);
        return itemAuditadoRepository.findById(id)
            .map(itemAuditadoMapper::toDto);
    }

    /**
     * Delete the itemAuditado by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ItemAuditado : {}", id);
        itemAuditadoRepository.deleteById(id);
    }
}
