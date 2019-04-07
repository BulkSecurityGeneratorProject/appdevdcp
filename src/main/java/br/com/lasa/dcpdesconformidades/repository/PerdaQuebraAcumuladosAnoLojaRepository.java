package br.com.lasa.dcpdesconformidades.repository;

import br.com.lasa.dcpdesconformidades.domain.PerdaQuebraAcumuladosAnoLoja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the PerdaQuebraAcumuladosAnoLoja entity.
 */
@Repository
public interface PerdaQuebraAcumuladosAnoLojaRepository extends JpaRepository<PerdaQuebraAcumuladosAnoLoja, Long> {

        @Query("select perda from PerdaQuebraAcumuladosAnoLoja perda WHERE perda.loja.id=:idLoja AND perda.ano=:ano")
    Optional<PerdaQuebraAcumuladosAnoLoja> findOneByLojaAno(@Param("idLoja") Long idLoja, @Param("ano") Integer ano );
}
