package com.example.javaproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "membre")
@Setter
@Getter
public class MembreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMembre;
    private String nom;
    private String prenom;
    private String equipe;

    @OneToMany(mappedBy = "membre")
    private List<TacheEntity> tasks;


    @Override
    public String toString() {
        return "MembreEntity{" +
                "idMembre=" + idMembre +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", equipe='" + equipe + '\'' +
                '}';
    }
}
