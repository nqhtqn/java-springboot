package com.example.livrespirngboot.services;

import com.example.livrespirngboot.entity.LivreEntity;
import com.example.livrespirngboot.repositories.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    public List<LivreEntity> recupereTousLesLivres(){
        return (List<LivreEntity>) livreRepository.findAll();
    }

    public LivreEntity recupereLivre(Long id){
        return livreRepository.findById(id).orElse(null);
    }
    public void ajouterLivre(LivreEntity livre){
        livreRepository.save(livre);
    }

    public void supprimerLivre(Long id){
        livreRepository.deleteById(id);
    }
}
