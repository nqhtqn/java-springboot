package com.example.livrespirngboot.services;

import com.example.livrespirngboot.entity.AuteurEntity;
import com.example.livrespirngboot.repositories.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
