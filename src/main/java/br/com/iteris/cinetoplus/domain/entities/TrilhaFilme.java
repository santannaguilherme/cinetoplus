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
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrilhaFilme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTrilhaFilme")
    private Integer idTrilhaFilme;

    @Column(name = "IdTrilha",nullable = false)
    private Integer idTrilha;
    
    @Column(name = "IdFilme",nullable = false)
    private Integer idFilme;

}
