package br.com.iteris.cinetoplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.iteris.cinetoplus.domain.entities.SiteRole;

/**
 * SiteRoleRepository
 */
@Repository
public interface SiteRoleRepository extends JpaRepository<SiteRole, String> {

} 