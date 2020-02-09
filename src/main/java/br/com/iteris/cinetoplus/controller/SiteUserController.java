package br.com.iteris.cinetoplus.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.iteris.cinetoplus.domain.dto.request.LoginRequest;
import br.com.iteris.cinetoplus.domain.dto.request.UsuarioAvatarRequest;
import br.com.iteris.cinetoplus.domain.dto.request.UsuarioCreateRequest;
import br.com.iteris.cinetoplus.domain.entities.Usuario;
import br.com.iteris.cinetoplus.service.SiteUserService;
import br.com.iteris.cinetoplus.service.UsuarioService;
import br.com.iteris.cinetoplus.utils.SiteRoles;
import io.swagger.annotations.ApiOperation;

/**
 * SiteUserController
 */
@RestController
@RequestMapping("/user")
public class SiteUserController {

  private final SiteUserService service;
  private final UsuarioService usuarioService;

  @Autowired
  public SiteUserController(SiteUserService service, UsuarioService usuarioService)  {
    this.service = service;
    this.usuarioService = usuarioService;
  }
  
  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping
  public void createUsuario(@Valid @RequestBody UsuarioCreateRequest usuarioCreateRequest) {
    service.createUser(usuarioCreateRequest);
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping("/login")
  public Usuario login(@Valid @RequestBody LoginRequest login) {
    String email = login.getEmail();
    String senha = login.getSenha();
    return service.ValidateUser(email, senha);
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @Secured({SiteRoles.APP_USER})
  @ApiOperation("Alterar foto")
  @PutMapping("/{id}/avatar")
  public void editAvatarUsuario(@Valid @RequestParam("id") Integer id, @RequestBody UsuarioAvatarRequest usuarioAvaterRequest) {
    usuarioService.editAvatarUsuario(id, usuarioAvaterRequest);
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping()
  public List<Usuario>  listUsers() {
    return usuarioService.findAll();
  }

}