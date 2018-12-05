package br.com.lasa.dcpdesconformidades.repository;

import br.com.lasa.dcpdesconformidades.domain.Avaliacao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Avaliacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("select avaliacao from Avaliacao avaliacao where avaliacao.avaliador.login = ?#{principal.username}")
    List<Avaliacao> findByAvaliadorIsCurrentUser();

}
