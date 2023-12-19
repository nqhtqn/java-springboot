package com.example.javaproject.services;

import com.example.javaproject.entity.AuteurEntity;
import com.example.javaproject.repositories.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuteurService {

    @Autowired
    private AuteurRepository auteurRepository;

    public AuteurEntity recupereAuteur(Long id){
        return auteurRepository.findById(id).orElse(null);
    }
    public List<AuteurEntity> recupererLesAuteurs(){
        return (List<AuteurEntity>) auteurRepository.findAll();
    }
    public void ajouterAuteur(AuteurEntity auteur){
        auteurRepository.save(auteur);
    }
}
