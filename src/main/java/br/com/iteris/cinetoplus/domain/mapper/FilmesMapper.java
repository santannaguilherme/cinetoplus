package br.com.iteris.cinetoplus.domain.mapper;

import br.com.iteris.cinetoplus.domain.dto.request.FilmesRequest;
import br.com.iteris.cinetoplus.domain.dto.response.FilmesResponse;
import br.com.iteris.cinetoplus.domain.entities.Filmes;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmesMapper {

    private final ModelMapper mapper;

    @Autowired
    public FilmesMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public FilmesResponse toDto(Filmes x) {
        return mapper.map(x, FilmesResponse.class);
    }

    public Filmes fromDto(FilmesRequest input) {
        return mapper.map(input, Filmes.class);
    }

    // public EventoUpdateRequest name() {
        
    // }

}