package br.com.iteris.cinetoplus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iteris.cinetoplus.domain.entities.TrilhaFilme;
import br.com.iteris.cinetoplus.domain.entities.Trilhas;
import br.com.iteris.cinetoplus.exception.BussinessRuleException;
import br.com.iteris.cinetoplus.repository.TrilhaFilmeRepository;

/**
 * TrilhaFilmeService
 */
@Service
public class TrilhaFilmeService {

    private final TrilhaFilmeRepository trilhaFilmeRepository;

    @Autowired
    public TrilhaFilmeService(TrilhaFilmeRepository trilhaFilmeRepository, FilmesService filmesService,
    TrilhaService trilhaService) {
        this.trilhaFilmeRepository = trilhaFilmeRepository;
    }

     public List<TrilhaFilme> listByFilme(Integer id) {

        return trilhaFilmeRepository.findByIdFilme(id);
    }

    public TrilhaFilme addTrilha(Integer idTrilha, Integer idFilme) {

        List<Trilhas> teste = trilhaFilmeRepository.existis(idFilme, idTrilha);

        if (teste.size() > 0) {
            throw new BussinessRuleException("Trilha ja cadastrada no filme");
        }
        TrilhaFilme trilhaFilme = TrilhaFilme.builder().idTrilha(idTrilha).idFilme(idFilme).build();

        return trilhaFilmeRepository.save(trilhaFilme);
    }


    
}