package br.com.iteris.cinetoplus.domain.mapper;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.iteris.cinetoplus.domain.dto.request.TrilhaCreateRequest;
import br.com.iteris.cinetoplus.domain.dto.response.TrilhaResponse;
import br.com.iteris.cinetoplus.domain.entities.Trilhas;

@Component
public class TrilhaMapper {

    private final ModelMapper mapper;

    @Autowired
    public TrilhaMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public TrilhaResponse toDto(Trilhas input) {
        return mapper.map(input, TrilhaResponse.class);
    }

    public Trilhas fromDto(TrilhaCreateRequest input) {
        return mapper.map(input, Trilhas.class);
    }

    public Trilhas DELETE(TrilhaCreateRequest input) {
        return mapper.map(input, Trilhas.class);
    }

}