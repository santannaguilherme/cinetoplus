package br.com.iteris.cinetoplus.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "Trilha")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trilhas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTrilha", nullable = false)
    private Integer idTrilha;

    @Column(name = "Nome", nullable = false, length = 500)
    private String nome;

    @Column(name = "Album", nullable = false, length = 500)
    private String album;

    @Column(name = "Compositor", nullable = false, length = 500)
    private String compositor;

    @Column(name = "Duracao", nullable = false, length = 1000)
    private String duracao;

    @Column(name = "Diretorio", nullable = false, length = 1000)
    private String diretorio;

    @Column(name = "Capa", nullable = false, length = 1000)
    private String capa;

}
