package br.com.lasa.dcpdesconformidades.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.lasa.dcpdesconformidades.domain.Questionario;


/**
 * Spring Data  repository for the Questionario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionarioRepository extends JpaRepository<Questionario, Long> {
  
  @Query(value = "select distinct questionario from Questionario questionario left join fetch questionario.grupos where questionario.ativo = true")
  Optional<Questionario> findAllWithEagerRelationshipsWhereAtivoTrue();

}
