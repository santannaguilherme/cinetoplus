package br.com.iteris.cinetoplus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.iteris.cinetoplus.domain.entities.TrilhaFilme;
import br.com.iteris.cinetoplus.domain.entities.Trilhas;


@Repository
public interface TrilhaFilmeRepository extends JpaRepository<TrilhaFilme, Integer> {

    
    List<TrilhaFilme> findByIdFilme(Integer idFilme);

    @Query(nativeQuery = true,value = "select * from TrilhaFilme where IdFilme = ?1 and IdTrilha = ?2")
	List<Trilhas> existis(Integer idFilme, Integer idTrilha);
}