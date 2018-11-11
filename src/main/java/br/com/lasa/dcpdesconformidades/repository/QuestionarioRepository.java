package br.com.lasa.dcpdesconformidades.repository;

import br.com.lasa.dcpdesconformidades.domain.Questionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Questionario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionarioRepository extends JpaRepository<Questionario, Long> {

    @Query(value = "select distinct questionario from Questionario questionario left join fetch questionario.grupos",
        countQuery = "select count(distinct questionario) from Questionario questionario")
    Page<Questionario> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct questionario from Questionario questionario left join fetch questionario.grupos")
    List<Questionario> findAllWithEagerRelationships();

    @Query("select questionario from Questionario questionario left join fetch questionario.grupos where questionario.id =:id")
    Optional<Questionario> findOneWithEagerRelationships(@Param("id") Long id);

}
