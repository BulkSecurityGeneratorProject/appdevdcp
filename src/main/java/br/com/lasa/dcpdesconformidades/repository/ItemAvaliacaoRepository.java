package br.com.lasa.dcpdesconformidades.repository;

import br.com.lasa.dcpdesconformidades.domain.ItemAvaliacao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ItemAvaliacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemAvaliacaoRepository extends JpaRepository<ItemAvaliacao, Long> {

}
