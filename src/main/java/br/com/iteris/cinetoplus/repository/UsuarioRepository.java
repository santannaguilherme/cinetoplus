package br.com.iteris.cinetoplus.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.iteris.cinetoplus.domain.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  Usuario findByEmail(String email);
} 