package com.example.javaproject.services;

import com.example.javaproject.entity.TacheEntity;
import com.example.javaproject.repositories.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacheService {

    @Autowired
    private TacheRepository tacheRepository;

    public List<TacheEntity> recupereToutesLesTaches(){
        return (List<TacheEntity>) tacheRepository.findAll();
    }

    public TacheEntity recupereTache(Long id){
        return tacheRepository.findById(id).orElse(null);
    }
    public void ajouterTache(TacheEntity tache){
        tacheRepository.save(tache);
    }

    public void supprimerTache(Long id){
        tacheRepository.deleteById(id);
    }
}
