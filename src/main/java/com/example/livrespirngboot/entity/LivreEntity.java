package com.example.livrespirngboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "livre")
@Getter
@Setter
public class LivreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String genre;
    private String annee;
    private String titre;

    @ManyToOne
    @JoinColumn( name="idAuteur" )
    private AuteurEntity auteur;




    @Override
    public String toString() {
        return "LivreEntity{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                ", annee='" + annee + '\'' +
                ", titre='" + titre + '\'' +
                ", auteur=" + auteur +
                '}';
    }
}
