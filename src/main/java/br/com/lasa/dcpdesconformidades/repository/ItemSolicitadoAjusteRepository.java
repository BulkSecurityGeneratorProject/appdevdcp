package br.com.lasa.dcpdesconformidades.repository;

import br.com.lasa.dcpdesconformidades.domain.ItemSolicitadoAjuste;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ItemSolicitadoAjuste entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemSolicitadoAjusteRepository extends JpaRepository<ItemSolicitadoAjuste, Long> {

}
