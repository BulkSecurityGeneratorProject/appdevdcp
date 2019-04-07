package br.com.lasa.dcpdesconformidades.service;

import br.com.lasa.dcpdesconformidades.domain.PerdaQuebraAcumuladosAnoLoja;
import br.com.lasa.dcpdesconformidades.repository.PerdaQuebraAcumuladosAnoLojaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Loja.
 */
@Service
@Transactional
public class PerdaQuebraAcumuladosAnoLojaService {

  private final Logger log = LoggerFactory.getLogger(PerdaQuebraAcumuladosAnoLojaService.class);

  private final PerdaQuebraAcumuladosAnoLojaRepository perdaQuebraRepository;

  public PerdaQuebraAcumuladosAnoLojaService(PerdaQuebraAcumuladosAnoLojaRepository perdaQuebraRepository) {
    this.perdaQuebraRepository = perdaQuebraRepository;
  }

  @Transactional(readOnly = true)
  public Optional<PerdaQuebraAcumuladosAnoLoja> ultimoAplicavelParaLoja(Long lojaId) {
      Integer lastYear = LocalDateTime.now().minusYears(1L).getYear();
      return perdaQuebraRepository.findOneByLojaAno(lojaId, lastYear);
  }
}
