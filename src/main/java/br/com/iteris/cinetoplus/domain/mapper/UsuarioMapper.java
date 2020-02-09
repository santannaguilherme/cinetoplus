package br.com.iteris.cinetoplus.domain.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.iteris.cinetoplus.domain.dto.request.UsuarioAvatarRequest;
import br.com.iteris.cinetoplus.domain.dto.request.UsuarioCreateRequest;
import br.com.iteris.cinetoplus.domain.dto.response.UsuarioResponse;
import br.com.iteris.cinetoplus.domain.entities.Usuario;
import br.com.iteris.cinetoplus.service.UsuarioService;

@Component
public class UsuarioMapper {

  private final ModelMapper mapper;
  private final UsuarioService usuarioService;


  @Autowired
  public UsuarioMapper(ModelMapper mapper, UsuarioService usuarioService) {
    this.mapper = mapper;
    this.usuarioService = usuarioService;
  }

  public UsuarioResponse toDto(Usuario usuario) {
    return mapper.map(usuario, UsuarioResponse.class);
  }

  public Usuario fromDto(UsuarioCreateRequest usuarioCreateRequest) {
    return mapper.map(usuarioCreateRequest, Usuario.class);
  }

  public Usuario fromDtoF(UsuarioAvatarRequest input, Integer id) {
    Usuario u = usuarioService.findUsuarioById(id);
    u.setImagem(input.getFoto());
    return u;
}

}