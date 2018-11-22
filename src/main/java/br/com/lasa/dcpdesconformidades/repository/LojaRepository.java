package br.com.lasa.dcpdesconformidades.repository;

import br.com.lasa.dcpdesconformidades.domain.Loja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Loja entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {

    @Query(value = "select distinct loja from Loja loja left join fetch loja.avaliadores",
        countQuery = "select count(distinct loja) from Loja loja")
    Page<Loja> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct loja from Loja loja left join fetch loja.avaliadores")
    List<Loja> findAllWithEagerRelationships();

    @Query("select loja from Loja loja left join fetch loja.avaliadores where loja.id =:id")
    Optional<Loja> findOneWithEagerRelationships(@Param("id") Long id);

}
