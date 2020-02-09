package br.com.iteris.cinetoplus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.iteris.cinetoplus.domain.entities.SiteUser;

/**
 * SiteUserRepository
 */
@Repository
public interface SiteUserRepository extends JpaRepository<SiteUser, Integer> {

    Optional<SiteUser> findByEmail(String email);

    Optional<SiteUser> findByEmailAndPassword(String email, String password);
}