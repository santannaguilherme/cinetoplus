package br.com.iteris.cinetoplus.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.iteris.cinetoplus.domain.dto.request.UsuarioAvatarRequest;
import br.com.iteris.cinetoplus.domain.dto.request.UsuarioCreateRequest;
import br.com.iteris.cinetoplus.domain.dto.response.UsuarioResponse;
import br.com.iteris.cinetoplus.domain.entities.Usuario;
import br.com.iteris.cinetoplus.domain.mapper.UsuarioMapper;
import br.com.iteris.cinetoplus.service.SiteUserService;
import br.com.iteris.cinetoplus.service.UsuarioService;
import br.com.iteris.cinetoplus.utils.SiteRoles;
import io.swagger.annotations.ApiOperation;

/**
 * UsuariosController
 */
@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

  private UsuarioService usuarioService;
  private UsuarioMapper mapper;
  
  @Autowired
  public UsuariosController(UsuarioService usuarioService, SiteUserService siteUserService, UsuarioMapper mapper) {
    this.usuarioService = usuarioService;
    this.mapper = mapper;
  }
  
  // @ApiOperation("Cadastrar novo usuário")
  // @ResponseStatus(code = HttpStatus.CREATED)
  // @ResponseBody
  // @PostMapping
  // public ResponseEntity<UsuarioResponse> createUsuario(@Valid @RequestBody UsuarioCreateRequest usuarioCreateRequest) {
  //   return ResponseEntity.ok(mapper.toDto(usuarioService.createUsuario(mapper.fromDto(usuarioCreateRequest))));
  // }

  @ApiOperation("Cadastrar novo usuário")
  @ResponseStatus(code = HttpStatus.CREATED)
  @ResponseBody
  @PostMapping
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<UsuarioResponse> createUsuario(@Valid @RequestBody UsuarioCreateRequest usuarioCreateRequest) {
    return ResponseEntity.ok(mapper.toDto(usuarioService.createUsuario(mapper.fromDto(usuarioCreateRequest))));
  }

  @Secured({SiteRoles.APP_USER})
  @ApiOperation("Alterar foto")
  @CrossOrigin(origins = "http://localhost:4200")
  @PatchMapping("/{id}/avatar")
  public void editAvatarUsuario(@Valid @RequestParam("id") Integer id, @RequestBody UsuarioAvatarRequest usuarioAvaterRequest) {
    usuarioService.editAvatarUsuario(id, usuarioAvaterRequest);
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping()
  public List<Usuario>  listUsers() {
    return usuarioService.findAll();
  }
  
}