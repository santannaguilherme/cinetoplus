package br.com.iteris.cinetoplus.repository;

import br.com.iteris.cinetoplus.domain.entities.Filmes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface FilmesRepository extends JpaRepository<Filmes, Integer> {

    @Query(nativeQuery = true, value = "select distinct name from Filmes")
    List<String> listDistinct();

} 