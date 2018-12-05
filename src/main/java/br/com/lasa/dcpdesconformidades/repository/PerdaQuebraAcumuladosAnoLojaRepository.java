package br.com.lasa.dcpdesconformidades.repository;

import br.com.lasa.dcpdesconformidades.domain.PerdaQuebraAcumuladosAnoLoja;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PerdaQuebraAcumuladosAnoLoja entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PerdaQuebraAcumuladosAnoLojaRepository extends JpaRepository<PerdaQuebraAcumuladosAnoLoja, Long> {

}
