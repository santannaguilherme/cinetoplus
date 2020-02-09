package br.com.iteris.cinetoplus.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Favoritos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdFavorito")
    private Integer idFavorito;


    @ManyToOne
    @JoinColumn(name = "IdFilme", nullable = false)
    private Filmes filme;

    @ManyToOne
    @JoinColumn(name = "IdUsuaro", nullable = false)
    private Usuario usuario;

}
