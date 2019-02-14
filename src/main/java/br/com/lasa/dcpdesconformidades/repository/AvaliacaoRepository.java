package br.com.lasa.dcpdesconformidades.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lasa.dcpdesconformidades.domain.Avaliacao;
import br.com.lasa.dcpdesconformidades.domain.enumeration.StatusAvaliacao;

/**
 * Spring Data repository for the Avaliacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

  @Query("select avaliacao from Avaliacao avaliacao where avaliacao.avaliador.login = ?#{principal.username}")
  List<Avaliacao> findByAvaliadorIsCurrentUser();

  @Modifying
  @Query("update Avaliacao a set a.status = :status, a.canceladaEm = :canceladaEm, a.motivoCancelamento = :motivoCancelamento where a.id = :id")
  int setStatusAsCancelledFor(@Param("status") StatusAvaliacao status, @Param("canceladaEm") Instant canceladaEm, @Param("motivoCancelamento") String motivoCancelamento, @Param("id") Long id);

}
