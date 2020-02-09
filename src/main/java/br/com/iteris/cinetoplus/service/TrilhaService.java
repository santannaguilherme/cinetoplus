package br.com.iteris.cinetoplus.service;

import java.util.List;
import java.util.Optional;

import br.com.iteris.cinetoplus.domain.entities.Trilhas;
import br.com.iteris.cinetoplus.exception.DataNotFoundException;
import br.com.iteris.cinetoplus.repository.TrilhaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrilhaService {

    private final TrilhaRepository trilhaRepository;

    @Autowired
    public TrilhaService(TrilhaRepository trilhaRepository) {
        this.trilhaRepository = trilhaRepository;
    }

    // n fazer nos satatus
    public Trilhas createTrilha(Trilhas model) {
        return trilhaRepository.save(model);
    }

    public List<Trilhas> listTrilha() {
        return trilhaRepository.findAll();
    }

    public Trilhas findById(Integer id) {
        Optional<Trilhas> trilhas = trilhaRepository.findById(id);
        return trilhas.orElseThrow(() -> new DataNotFoundException("Trilha Not found"));
    }

    
}