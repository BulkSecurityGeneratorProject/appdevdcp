package br.com.lasa.dcpdesconformidades.repository;

import br.com.lasa.dcpdesconformidades.domain.GrupoItens;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GrupoItens entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GrupoItensRepository extends JpaRepository<GrupoItens, Long> {

}
