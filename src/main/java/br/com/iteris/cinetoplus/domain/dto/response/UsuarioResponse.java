package br.com.iteris.cinetoplus.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UsuarioResponde
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponse {

  private Integer idUsuario;
  private String nome;
  private String sobrenome;
  private String email;
  // private String imagem;
  // private String favoritos; // Acredito que posteriormente será uma lista de filmes
}