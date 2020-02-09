package br.com.iteris.cinetoplus.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import br.com.iteris.cinetoplus.domain.dto.request.FilmesRequest;
import br.com.iteris.cinetoplus.domain.dto.response.FilmesResponse;
import br.com.iteris.cinetoplus.domain.entities.Filmes;
import br.com.iteris.cinetoplus.domain.mapper.FilmesMapper;
import br.com.iteris.cinetoplus.service.FilmesService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmes")
public class FilmesController {

    private final FilmesService filmesService;
    private final FilmesMapper mapper;

    @Autowired
    public FilmesController(FilmesService filmesService, FilmesMapper filmesMapper) {
        this.mapper = filmesMapper;
        this.filmesService = filmesService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/{idFilmes}")
    public ResponseEntity<FilmesResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(mapper.toDto(filmesService.findById(id)));

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<FilmesResponse>> list() {
        return ResponseEntity.ok(filmesService.listFilmes().stream() //
                .map(x -> mapper.toDto(x)) //
                .collect(Collectors.toList()));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<FilmesResponse> post(@Valid @RequestBody FilmesRequest model) {

        Filmes filmes = mapper.fromDto(model);

        Filmes f = filmesService.createFilme(filmes);

        return ResponseEntity.ok(mapper.toDto(f));
    }

}
