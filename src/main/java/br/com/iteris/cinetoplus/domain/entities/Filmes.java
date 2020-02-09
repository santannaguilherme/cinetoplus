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
public class Filmes {

    // , [IdTrilhaFilme] int not null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdFilme", nullable = false)
    private Integer idFilme;

    @Column(name = "Titulo", nullable = false, length = 500)
    private String titulo;

    @Column(name = "Ano", nullable = false)
    private Integer ano;

    @Column(name = "Censura", nullable = false, length = 500)
    private String censura;

    @Column(name = "Generoa", nullable = false, length = 500)
    private String genero;
    
    @Column(name = "Elenco", nullable = false, length = 1000)
    private String elenco;
        
    @Column(name = "Diretor", nullable = false, length = 1000)
    private String diretor;

    @Column(name = "Duracao", nullable = false)
    private Integer duracao;

    @Column(name = "Ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "Descricao", nullable = false, length = 1000)
    private String sinopse;
    
    @Column(name = "Imagem", nullable = false, length = 1000)
    private String imagem;

    @ManyToOne
    @JoinColumn(name = "IdTrilhaFilme", nullable = true)
    private TrilhaFilme trilhafilme;




}
