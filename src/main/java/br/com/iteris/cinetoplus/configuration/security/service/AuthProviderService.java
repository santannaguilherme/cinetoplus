package br.com.iteris.cinetoplus.configuration.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.iteris.cinetoplus.domain.entities.SiteRole;
import br.com.iteris.cinetoplus.domain.entities.Usuario;
import br.com.iteris.cinetoplus.service.SiteUserService;

@Component
public class AuthProviderService implements AuthenticationProvider {

  @Autowired
  private SiteUserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    String name = authentication.getName();
    String password = authentication.getCredentials().toString();

    Usuario validUser = userService.ValidateUser(name, password);

    if (validUser != null) {
      List<SiteRole> roles = userService.rolesFrom(validUser.getEmail());
      return new UsernamePasswordAuthenticationToken(validUser.getEmail(), //
          validUser.getSenha(), roles);
    } else {
      throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
    }
  }

  @Override
  public boolean supports(Class<?> auth) {
    return auth.equals(UsernamePasswordAuthenticationToken.class);
  }
}