package br.com.iteris.cinetoplus.domain.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmesRequest {


    private Integer idFilmes;

    @NotEmpty(message = "titulo is required")
    @Size(max = 500)
    private String titulo;

    private Integer ano;
    
    @NotEmpty(message = "censura is required")
    @Size(max = 500)
    private String censura;
    
    @NotEmpty(message = "genero is required")
    @Size(max = 250)
    private String genero;

    @NotEmpty(message = "diretor is required")
    @Size(max = 250)
    private String diretor;

    @NotEmpty(message = "elenco is required")
    @Size(max = 500)
    private String elenco;

    @NotEmpty(message = "sinopse is required")
    @Size(max = 500)
    private String sinopse;

    @NotEmpty(message = "imagem is required")
    @Size(max = 500)
    private String imagem;

    private Integer duracao;
    
    private Integer idTrilha;
}