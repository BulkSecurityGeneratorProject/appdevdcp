package br.com.lasa.dcpdesconformidades.repository;

import br.com.lasa.dcpdesconformidades.domain.GrupoItens;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the GrupoItens entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GrupoItensRepository extends JpaRepository<GrupoItens, Long> {

    @Query(value = "select distinct grupo_itens from GrupoItens grupo_itens left join fetch grupo_itens.itens",
        countQuery = "select count(distinct grupo_itens) from GrupoItens grupo_itens")
    Page<GrupoItens> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct grupo_itens from GrupoItens grupo_itens left join fetch grupo_itens.itens")
    List<GrupoItens> findAllWithEagerRelationships();

    @Query("select grupo_itens from GrupoItens grupo_itens left join fetch grupo_itens.itens where grupo_itens.id =:id")
    Optional<GrupoItens> findOneWithEagerRelationships(@Param("id") Long id);

}
