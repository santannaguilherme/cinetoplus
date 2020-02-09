package br.com.iteris.cinetoplus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iteris.cinetoplus.domain.entities.Favoritos;
import br.com.iteris.cinetoplus.domain.entities.Filmes;
import br.com.iteris.cinetoplus.domain.entities.Usuario;
import br.com.iteris.cinetoplus.exception.BussinessRuleException;
import br.com.iteris.cinetoplus.repository.FavoritosRepository;

/**
 * FavoritosService
 */
@Service
public class FavoritosService {

    private final FavoritosRepository favoritosRepository;
    private final FilmesService filmesService;
    private final UsuarioService usuarioService;

    @Autowired
    public FavoritosService(FavoritosRepository favoritosRepository, FilmesService filmesService,
            UsuarioService usuarioService) {
        this.favoritosRepository = favoritosRepository;
        this.filmesService = filmesService;
        this.usuarioService = usuarioService;
    }

    public List<Filmes> listByUser(Integer id) {
        Usuario u = usuarioService.findUsuarioById(id);

        return favoritosRepository.findByUsuario(u);
    }

    public Favoritos addFavorito(Integer idUser, Integer idFilme) {

        Filmes f = filmesService.findById(idFilme);
        Usuario u = usuarioService.findUsuarioById(idUser);
        List<Favoritos> teste = favoritosRepository.existis(idFilme, idUser);

        if (teste.size() > 0) {
            throw new BussinessRuleException("Filme ja favoritado pelo usuário");
        }
        Favoritos favorito = Favoritos.builder().usuario(u).filme(f).build();

        return favoritosRepository.save(favorito);
    }

    public void deleteFavorito(Integer idUser, Integer idFilme) {
        List<Favoritos> favoritos = favoritosRepository.existis(idFilme, idUser);
        favoritosRepository.deleteAll(favoritos);

    }

}