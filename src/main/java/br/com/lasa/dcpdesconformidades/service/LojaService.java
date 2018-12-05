package br.com.lasa.dcpdesconformidades.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lasa.dcpdesconformidades.domain.Loja;
import br.com.lasa.dcpdesconformidades.domain.User;
import br.com.lasa.dcpdesconformidades.repository.LojaRaioxRepository;
import br.com.lasa.dcpdesconformidades.repository.UserRepository;
import br.com.lasa.dcpdesconformidades.service.dto.LojaParaAvaliacaoDTO;
import br.com.lasa.dcpdesconformidades.service.mapper.LojaParaAvaliacaoMapper;
import br.com.lasa.dcpdesconformidades.service.mapper.LojaRaioxMapper;

/**
 * Service Implementation for managing Loja.
 */
@Service
@Transactional
public class LojaService {

  private final Logger log = LoggerFactory.getLogger(LojaService.class);

  private final LojaRaioxRepository lojaRaioxRepository;

  private final LojaParaAvaliacaoMapper lojaParaAvaliacaoMapper;

  private final LojaRaioxMapper lojaRaioxMapper;

  private final UserRepository userRepository;

  public LojaService(LojaRaioxRepository lojaRaioxRepository, LojaParaAvaliacaoMapper lojaParaAvaliacaoMapper, LojaRaioxMapper lojaRaioxMapper, UserRepository userRepository) {
    this.lojaRaioxRepository = lojaRaioxRepository;
    this.lojaParaAvaliacaoMapper = lojaParaAvaliacaoMapper;
    this.lojaRaioxMapper = lojaRaioxMapper;
    this.userRepository = userRepository;
  }
  
  /**
   * Save a loja.
   *
   * @param loja the entity to save
   * @return the persisted entity
   */
  public Loja save(Loja loja) {
      log.debug("Request to save Loja : {}", loja);

//      return lojaRepository.save(loja);
      return null;
  }

  /**
   * Get all the lojas.
   *
   * @param pageable the pagination information
   * @return the list of entities
   */
  @Transactional(readOnly = true)
  public Page<Loja> findAll(Pageable pageable) {
    log.debug("Request to get all Lojas");
    return lojaRaioxRepository.findAll(pageable) //
        .map(lojaRaioxMapper::toEntity);
  }

  /**
   * Get one loja by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Transactional(readOnly = true)
  public Optional<Loja> findOne(Long id) {
    log.debug("Request to get Loja : {}", id);
    return lojaRaioxRepository.findOne(id) //
        .map(lojaRaioxMapper::toEntity).map(loja -> {
          loja.setAvaliadores(userRepository.findAllByIdLoja(id));
          return loja;
        });
  }

  @Transactional(readOnly = true)
  public List<LojaParaAvaliacaoDTO> getLojasPermitidasParaAvaliacaoPara(User user) {
    return user.getLojas().stream() //
        .map(lojaParaAvaliacaoMapper::toDto) //
        .map(loja -> { //
          loja.setSubmissaoUltimaAvaliacao(null); // TODO obter e incluir //
          return loja; //
        }) //
        .collect(Collectors.toList());
  }
}
