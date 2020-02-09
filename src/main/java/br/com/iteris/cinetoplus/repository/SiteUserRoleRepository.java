package br.com.iteris.cinetoplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.iteris.cinetoplus.domain.entities.SiteUserRole;

/**
 * SiteUserRepository
 */
@Repository
public interface SiteUserRoleRepository extends JpaRepository<SiteUserRole, Integer> {

}