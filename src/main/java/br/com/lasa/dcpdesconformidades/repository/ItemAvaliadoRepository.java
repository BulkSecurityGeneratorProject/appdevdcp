package br.com.lasa.dcpdesconformidades.repository;

import br.com.lasa.dcpdesconformidades.domain.ItemAvaliado;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ItemAvaliado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemAvaliadoRepository extends JpaRepository<ItemAvaliado, Long> {

}
