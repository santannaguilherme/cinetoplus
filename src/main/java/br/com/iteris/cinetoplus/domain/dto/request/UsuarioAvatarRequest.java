package br.com.iteris.cinetoplus.domain.dto.request;

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
public class UsuarioAvatarRequest {

  @NotEmpty(message = "foto is required")
  @Size(max = 1000)
  private String foto;
}