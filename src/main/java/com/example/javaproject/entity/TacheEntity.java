package com.example.javaproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tache")
@Getter
@Setter
public class TacheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    private String description;
    private String importance;
    private String etat;
    private String date_debut;
    private String date_fin;

    @ManyToOne
    @JoinColumn( name="idMembre" )
    private MembreEntity membre;

    @Override
    public String toString() {
        return "TacheEntity{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", importance='" + importance + '\'' +
                ", etat='" + etat + '\'' +
                ", date debut='" + date_debut + '\'' +
                ", date fin='" + date_fin + '\'' +
                ", membre=" + membre +
                '}';
    }
}
