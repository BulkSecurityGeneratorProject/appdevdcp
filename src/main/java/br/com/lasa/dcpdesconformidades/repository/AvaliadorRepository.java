package br.com.lasa.dcpdesconformidades.repository;

import br.com.lasa.dcpdesconformidades.domain.Avaliador;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Avaliador entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvaliadorRepository extends JpaRepository<Avaliador, Long> {

}
