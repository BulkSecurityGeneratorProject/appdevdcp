package br.com.lasa.dcpdesconformidades.service;

import br.com.lasa.dcpdesconformidades.domain.AnexoItem;
import br.com.lasa.dcpdesconformidades.repository.AnexoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing AnexoItem.
 */
@Service
@Transactional
public class AnexoItemService {

    private final Logger log = LoggerFactory.getLogger(AnexoItemService.class);

    private final AnexoItemRepository anexoItemRepository;

    public AnexoItemService(AnexoItemRepository anexoItemRepository) {
        this.anexoItemRepository = anexoItemRepository;
    }

    /**
     * Save a anexoItem.
     *
     * @param anexoItem the entity to save
     * @return the persisted entity
     */
    public AnexoItem save(AnexoItem anexoItem) {
        log.debug("Request to save AnexoItem : {}", anexoItem);
        return anexoItemRepository.save(anexoItem);
    }

    /**
     * Get all the anexoItems.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<AnexoItem> findAll() {
        log.debug("Request to get all AnexoItems");
        return anexoItemRepository.findAll();
    }


    /**
     * Get one anexoItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AnexoItem> findOne(Long id) {
        log.debug("Request to get AnexoItem : {}", id);
        return anexoItemRepository.findById(id);
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
