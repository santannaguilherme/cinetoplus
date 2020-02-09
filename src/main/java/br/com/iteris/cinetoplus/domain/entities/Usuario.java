package br.com.iteris.cinetoplus.domain.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario")
    private Integer idUsuario;

    @Column(name = "Nome", nullable = false, length = 500)
    private String nome;

    @Column(name = "Sobrenome", nullable = false, length = 500)
    private String sobrenome;

    @Column(name = "Email", nullable = false, length = 500)
    private String email;

    @Column(name = "Senha", nullable = false, length = 500)
    private String senha;

    @Column(name = "Administrador", nullable = false)
    private Boolean administrador;

    @Column(name = "Ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "Foto", nullable = false, length = 1000)
    private String imagem;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "siteUserId")
    private Set<SiteUserRole> roles;
}