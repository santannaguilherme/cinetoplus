package br.com.iteris.cinetoplus.repository;

import br.com.iteris.cinetoplus.domain.entities.Trilhas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrilhaRepository extends JpaRepository<Trilhas, Integer> {

}