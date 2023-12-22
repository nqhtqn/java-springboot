package com.example.javaproject.services;

import com.example.javaproject.entity.MembreEntity;
import com.example.javaproject.entity.NotificationEntity;
import com.example.javaproject.repositories.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MembreService {

    @Autowired
    private MembreRepository membreRepository;

    public MembreEntity recupereMembre(Long id){
        return membreRepository.findById(id).orElse(null);
    }
    public List<MembreEntity> recupererLesMembres(){
        return (List<MembreEntity>) membreRepository.findAll();
    }
    public void ajouterMembre(MembreEntity membre){
        membreRepository.save(membre);
    }

    public void sort(List<MembreEntity> membres) {
        membres.sort(new Comparator<MembreEntity>() {
            @Override
            public int compare(MembreEntity o1, MembreEntity o2) {
                return o2.getNom().compareTo(o1.getNom());
            }
        });
    }
}
