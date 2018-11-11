package br.com.lasa.dcpdesconformidades.repository;

import br.com.lasa.dcpdesconformidades.domain.ItemAvaliadoPerdaQuebraAcumulados;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ItemAvaliadoPerdaQuebraAcumulados entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemAvaliadoPerdaQuebraAcumuladosRepository extends JpaRepository<ItemAvaliadoPerdaQuebraAcumulados, Long> {

}
