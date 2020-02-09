package br.com.iteris.cinetoplus.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmesResponse {

    private Integer idFilmes;

    private String titulo;

    private Integer ano;
  
    private String censura;
    
    private Integer duracao;
    
    private String genero;

    private String diretor;

    private String elenco;
    
    private String sinopse;

    private Integer idTrilha;
}
    

