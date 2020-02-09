package br.com.iteris.cinetoplus.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iteris.cinetoplus.domain.entities.Filmes;
import br.com.iteris.cinetoplus.exception.DataNotFoundException;
import br.com.iteris.cinetoplus.repository.FilmesRepository;

@Service
public class FilmesService {

    private final FilmesRepository filmesRepository;

    @Autowired
    public FilmesService(FilmesRepository filmesRepository) {
        this.filmesRepository = filmesRepository;
    }

    public List<Filmes> listFilmes() {
        return filmesRepository.findAll();
    }

    public Filmes findById(Integer id) {
        Optional<Filmes> filmes = filmesRepository.findById(id);
        return filmes.orElseThrow(() -> new DataNotFoundException("Filme Not found"));
    }

    public Filmes createFilme(Filmes model) {
        return filmesRepository.save(model);
    }

    public Filmes filmePut(Integer idFilme, Filmes newFilme) {
        Filmes filme = findById(idFilme);
        filme.setTitulo(newFilme.getTitulo());
        filme.setAno(newFilme.getAno());
        filme.setCensura(newFilme.getCensura());
        filme.setDuracao(newFilme.getDuracao());
        filme.setGenero(newFilme.getGenero());
        filme.setDiretor(newFilme.getDiretor());
        filme.setElenco(newFilme.getElenco());
        filme.setSinopse(newFilme.getSinopse());

        return filmesRepository.save(filme);
    }

}