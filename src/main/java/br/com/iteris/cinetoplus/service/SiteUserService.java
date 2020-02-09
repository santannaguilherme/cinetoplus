package br.com.iteris.cinetoplus.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.iteris.cinetoplus.domain.dto.request.UsuarioCreateRequest;
import br.com.iteris.cinetoplus.domain.entities.SiteRole;
import br.com.iteris.cinetoplus.domain.entities.SiteUserRole;
import br.com.iteris.cinetoplus.domain.entities.Usuario;
import br.com.iteris.cinetoplus.domain.mapper.UsuarioMapper;
import br.com.iteris.cinetoplus.exception.BussinessRuleException;
import br.com.iteris.cinetoplus.repository.SiteRoleRepository;
import br.com.iteris.cinetoplus.repository.SiteUserRepository;
import br.com.iteris.cinetoplus.repository.SiteUserRoleRepository;
import br.com.iteris.cinetoplus.repository.UsuarioRepository;
import br.com.iteris.cinetoplus.utils.SiteRoles;

/**
 * SiteUserService
 */
@Service
public class SiteUserService {

  private final SiteUserRoleRepository siteUserRoleRepository;
  private final SiteRoleRepository siteRoleRepository;
  private final PasswordEncoder passEncoder;
  private final UsuarioMapper mapper;
  private final UsuarioRepository usuarioRepository;

  @Autowired
  public SiteUserService(SiteUserRepository siteUserRepository, //
      SiteUserRoleRepository siteUserRoleRepository, UsuarioMapper mapper, //
      SiteRoleRepository siteRoleRepository, PasswordEncoder passEncoder, //
      UsuarioRepository usuarioRepository) {
    this.siteUserRoleRepository = siteUserRoleRepository;
    
    this.siteRoleRepository = siteRoleRepository;
    this.passEncoder = passEncoder;
    this.mapper = mapper;
    this.usuarioRepository = usuarioRepository;
  }

  public Usuario createUser(UsuarioCreateRequest usuarioCreateRequest) {
    isNewUsuario(usuarioCreateRequest.getEmail());
    Usuario newUser = mapper.fromDto(usuarioCreateRequest);

    newUser.setImagem("Usuário novo");
    newUser.setAdministrador(false);
    newUser.setAtivo(true);
    newUser.setSenha(passEncoder.encode(newUser.getSenha()));
    usuarioRepository.save(newUser);

    Set<SiteUserRole> roles = new HashSet<SiteUserRole>();
    roles.add(getUserRole(newUser, SiteRoles.APP_USER));
    // if (isAdmin) {
    //   roles.add(getUserRole(newUser, SiteRoles.APP_ADMIN));
    // }
    siteUserRoleRepository.saveAll(roles);
    newUser.setRoles(roles);
    return newUser;
  }

  public SiteRole getRole(String role) {
    Optional<SiteRole> siteRole = siteRoleRepository.findById(role);
    if (siteRole.isPresent())
      return siteRole.get();
    else
      return siteRoleRepository.save(new SiteRole(role));
  }

  private SiteUserRole getUserRole(Usuario usr, String role) {
    SiteRole siteRole = getRole(role);
    return SiteUserRole.builder().siteUserId(usr.getIdUsuario()).siteRole(siteRole).build();
  }

  public List<SiteRole> rolesFrom(String usrName) {
    Usuario user = usuarioRepository.findByEmail(usrName);
    if (user == null)
      return new ArrayList<SiteRole>();
    else
      return user.getRoles().stream() //
      .map(x -> x.getSiteRole()).collect(Collectors.toList());
  }

  // public List<SiteRole> rolesFrom(SiteUser usr) {
  //   return usr.getRoles().stream() //
  //       .map(x -> x.getSiteRole()).collect(Collectors.toList());
  // }

  public Usuario ValidateUser(String email, String senha) {
    Usuario user = usuarioRepository.findByEmail(email);
    if (passEncoder.matches(senha, user.getSenha())) {
      return user;
    }
    else
      throw new BussinessRuleException("Usuário ou senha inexistentes");
  }

  public void isNewUsuario(String email) {
    Usuario usuario = usuarioRepository.findByEmail(email);
    if (usuario != null) {
      throw new BussinessRuleException("E-mail já cadastrado");
    }
  }

}