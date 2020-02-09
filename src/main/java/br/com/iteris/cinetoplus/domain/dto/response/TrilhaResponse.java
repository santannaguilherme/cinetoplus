package br.com.iteris.cinetoplus.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrilhaResponse {

    private Integer idTrilha;

    private String nome;

    private String album;

    private String compositor;

    private String duracao;

    private String diretorio;

    private String capa;

    //private String date;
}