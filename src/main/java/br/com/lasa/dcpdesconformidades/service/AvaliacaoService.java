package br.com.lasa.dcpdesconformidades.service;

import br.com.lasa.dcpdesconformidades.domain.Avaliacao;
import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusAvaliacao;
import br.com.lasa.dcpdesconformidades.repository.AvaliacaoRepository;
import br.com.lasa.dcpdesconformidades.service.dto.AvaliacaoDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.AvaliacaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

/**
 * Service Implementation for managing Avaliacao.
 */
@Service
@Transactional
public class AvaliacaoService {

    private final Logger log = LoggerFactory.getLogger(AvaliacaoService.class);

    private final AvaliacaoRepository avaliacaoRepository;

    private final AvaliacaoMapper avaliacaoMapper;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, AvaliacaoMapper avaliacaoMapper) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.avaliacaoMapper = avaliacaoMapper;
    }

    /**
     * Save a avaliacao.
     *
     * @param avaliacaoDTO the entity to save
     * @return the persisted entity
     */
    public AvaliacaoDTO save(AvaliacaoDTO avaliacaoDTO) {
        log.debug("Request to save Avaliacao : {}", avaliacaoDTO);

        Avaliacao avaliacao = avaliacaoMapper.toEntity(avaliacaoDTO);
        avaliacao = avaliacaoRepository.save(avaliacao);
        return avaliacaoMapper.toDto(avaliacao);
    }

    /**
     * Get all the avaliacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AvaliacaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Avaliacaos");
        return avaliacaoRepository.findAll(pageable)
            .map(avaliacaoMapper::toDto);
    }


    /**
     * Get one avaliacao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AvaliacaoDTO> findOne(Long id) {
        log.debug("Request to get Avaliacao : {}", id);
        return avaliacaoRepository.findById(id)
            .map(avaliacaoMapper::toDto);
    }

    /**
     * Delete the avaliacao by id.
     *
     * @param id the id of the entity
     */
    public void cancel(Long id, String motivoCancelamento) {
        log.debug("Request to delete Avaliacao : {}", id);
        avaliacaoRepository.setStatusAsCancelledFor(StatusAvaliacao.CANCELADO, Instant.now(), motivoCancelamento, id);
    }
        
    public AvaliacaoDTO iniciarAvaliacaoPara(String codigoLoja){
        return null;
        
    }

    public AvaliacaoDTO submeterAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        // TODO Auto-generated method stub
        return null;
    }
}
