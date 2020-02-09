package br.com.iteris.cinetoplus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iteris.cinetoplus.domain.dto.request.UsuarioAvatarRequest;
import br.com.iteris.cinetoplus.domain.entities.Usuario;
import br.com.iteris.cinetoplus.exception.BussinessRuleException;
import br.com.iteris.cinetoplus.exception.DataNotFoundException;
import br.com.iteris.cinetoplus.repository.UsuarioRepository;

/**
 * UsuarioService
 */
@Service
public class UsuarioService {

  private UsuarioRepository usuarioRepository;

  @Autowired
  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public Usuario createUsuario(Usuario usuario) {
    isNewUsuario(usuario.getEmail());
    usuario.setImagem("Usuário novo");
    usuario.setAdministrador(false);
    usuario.setAtivo(true);
    return usuarioRepository.save(usuario);
  }

  public List<Usuario> findAll(){
    return usuarioRepository.findAll();
  }
  public void isNewUsuario(String email) {
    Usuario usuario = usuarioRepository.findByEmail(email);
    if (usuario != null) {
      throw new BussinessRuleException("E-mail já cadastrado");
    }
  }

  public Usuario findUsuarioById(Integer id) {
    Optional<Usuario> usuario = usuarioRepository.findById(id);
    return usuario.orElseThrow(() -> new DataNotFoundException("Usuário não encontrado"));
  }

  public void editAvatarUsuario(Integer id, UsuarioAvatarRequest usuarioAvatarRequest) {
    Usuario usuario = findUsuarioById(id);
    usuario.setImagem(usuarioAvatarRequest.getFoto());
    usuarioRepository.save(usuario);
  }

}