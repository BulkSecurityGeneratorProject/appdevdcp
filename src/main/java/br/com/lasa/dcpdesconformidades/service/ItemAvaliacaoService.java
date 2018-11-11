package br.com.lasa.dcpdesconformidades.service;

import br.com.lasa.dcpdesconformidades.domain.ItemAvaliacao;
import br.com.lasa.dcpdesconformidades.repository.ItemAvaliacaoRepository;
import br.com.lasa.dcpdesconformidades.service.dto.ItemAvaliacaoDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.ItemAvaliacaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ItemAvaliacao.
 */
@Service
@Transactional
public class ItemAvaliacaoService {

    private final Logger log = LoggerFactory.getLogger(ItemAvaliacaoService.class);

    private final ItemAvaliacaoRepository itemAvaliacaoRepository;

    private final ItemAvaliacaoMapper itemAvaliacaoMapper;

    public ItemAvaliacaoService(ItemAvaliacaoRepository itemAvaliacaoRepository, ItemAvaliacaoMapper itemAvaliacaoMapper) {
        this.itemAvaliacaoRepository = itemAvaliacaoRepository;
        this.itemAvaliacaoMapper = itemAvaliacaoMapper;
    }

    /**
     * Save a itemAvaliacao.
     *
     * @param itemAvaliacaoDTO the entity to save
     * @return the persisted entity
     */
    public ItemAvaliacaoDTO save(ItemAvaliacaoDTO itemAvaliacaoDTO) {
        log.debug("Request to save ItemAvaliacao : {}", itemAvaliacaoDTO);

        ItemAvaliacao itemAvaliacao = itemAvaliacaoMapper.toEntity(itemAvaliacaoDTO);
        itemAvaliacao = itemAvaliacaoRepository.save(itemAvaliacao);
        return itemAvaliacaoMapper.toDto(itemAvaliacao);
    }

    /**
     * Get all the itemAvaliacaos.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ItemAvaliacaoDTO> findAll() {
        log.debug("Request to get all ItemAvaliacaos");
        return itemAvaliacaoRepository.findAll().stream()
            .map(itemAvaliacaoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one itemAvaliacao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ItemAvaliacaoDTO> findOne(Long id) {
        log.debug("Request to get ItemAvaliacao : {}", id);
        return itemAvaliacaoRepository.findById(id)
            .map(itemAvaliacaoMapper::toDto);
    }

    /**
     * Delete the itemAvaliacao by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ItemAvaliacao : {}", id);
        itemAvaliacaoRepository.deleteById(id);
    }
}
