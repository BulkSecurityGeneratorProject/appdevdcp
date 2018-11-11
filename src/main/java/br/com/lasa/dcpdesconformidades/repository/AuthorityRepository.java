package br.com.lasa.dcpdesconformidades.repository;

import br.com.lasa.dcpdesconformidades.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
