package br.com.lasa.dcpdesconformidades.repository;

import br.com.lasa.dcpdesconformidades.domain.AnexoItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AnexoItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnexoItemRepository extends JpaRepository<AnexoItem, Long> {

}
