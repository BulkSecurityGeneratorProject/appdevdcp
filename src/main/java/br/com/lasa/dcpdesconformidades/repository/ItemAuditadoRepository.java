package br.com.lasa.dcpdesconformidades.repository;

import br.com.lasa.dcpdesconformidades.domain.ItemAuditado;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ItemAuditado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemAuditadoRepository extends JpaRepository<ItemAuditado, Long> {

}
