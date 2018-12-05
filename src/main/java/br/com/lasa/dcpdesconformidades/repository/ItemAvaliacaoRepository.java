package br.com.lasa.dcpdesconformidades.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lasa.dcpdesconformidades.domain.GrupoItens;
import br.com.lasa.dcpdesconformidades.domain.ItemAvaliacao;


/**
 * Spring Data  repository for the ItemAvaliacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemAvaliacaoRepository extends JpaRepository<ItemAvaliacao, Long> {

  List<ItemAvaliacao> findByGrupos(Set<GrupoItens> grupos);
  
}
