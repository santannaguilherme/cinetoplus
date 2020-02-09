package br.com.iteris.cinetoplus.configuration.security.filters;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.iteris.cinetoplus.configuration.security.service.TokenAuthenticationService;
import br.com.iteris.cinetoplus.domain.dto.request.LoginRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

  public JWTLoginFilter(String url, AuthenticationManager authManager) {
    super(new AntPathRequestMatcher(url));
    setAuthenticationManager(authManager);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {

    LoginRequest credentials = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

    return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(),
        credentials.getSenha(), Collections.emptyList()));
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain, Authentication auth) throws IOException, ServletException {

    TokenAuthenticationService.addAuthentication(response, auth.getName());
  }

}