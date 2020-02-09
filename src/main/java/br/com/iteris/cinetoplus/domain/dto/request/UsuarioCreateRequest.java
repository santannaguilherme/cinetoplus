package br.com.iteris.cinetoplus.domain.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UsuarioCreateRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioCreateRequest {

  @NotEmpty(message = "nome is required")
  @Size(max = 500)
  private String nome;

  @NotEmpty(message = "sobrenome is required")
  @Size(max = 500)
  private String sobrenome;

  @Email(message = "email must be valid")
  @NotEmpty(message = "email is required")
  @Size(max = 500)
  private String email;

  @NotEmpty(message = "senha is required")
  @Size(max = 500)
  private String senha;

  // private String imagem;
}