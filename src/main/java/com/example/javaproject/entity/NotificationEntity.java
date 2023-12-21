package com.example.javaproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "notification")
@Setter
@Getter
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idNotif;

    @ManyToOne
    @JoinColumn( name="idMembre" )
    private MembreEntity membre;
    @ManyToOne
    @JoinColumn( name="idTache" )
    private TacheEntity tache;

    private String type;
    private String texte;
    private String vu;


    @Override
    public String toString() {
        return "MembreEntity{" +
                "idNotif=" + idNotif +
                ", type='" + type + '\'' +
                ", texte='" + texte + '\'' +
                ", vu='" + vu + '\'' +
                '}';
    }
}
