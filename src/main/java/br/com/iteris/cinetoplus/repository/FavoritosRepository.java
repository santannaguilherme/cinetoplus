package br.com.iteris.cinetoplus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.iteris.cinetoplus.domain.entities.Favoritos;
import br.com.iteris.cinetoplus.domain.entities.Filmes;
import br.com.iteris.cinetoplus.domain.entities.Usuario;


@Repository
public interface FavoritosRepository extends JpaRepository<Favoritos, Integer> {

    List<Filmes> findByUsuario(Usuario usuario);

    @Query(nativeQuery = true,value = "select * from Favoritos where IdFilme = ?1 and IdUsuario = ?2")
    List<Favoritos> existis(Integer f,Integer u);

}