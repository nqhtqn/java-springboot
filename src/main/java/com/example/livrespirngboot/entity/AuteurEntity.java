package com.example.livrespirngboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "auteur")
@Setter
@Getter
public class AuteurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAuteur;

    private String nom;
    private String prenom;

    @OneToMany(mappedBy = "auteur")
    private List<LivreEntity> books;


    @Override
    public String toString() {
        return "AuteurEntity{" +
                "idAuteur=" + idAuteur +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
