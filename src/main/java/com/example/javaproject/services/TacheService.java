package com.example.javaproject.services;

import com.example.javaproject.entity.MembreEntity;
import com.example.javaproject.entity.TacheEntity;
import com.example.javaproject.repositories.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public List<TacheEntity> recupereTachesParMembre(MembreEntity membre) {

        List<TacheEntity> listeTaches = recupereToutesLesTaches();
        List<TacheEntity> listeTachesDuMembre = new ArrayList<>();
        for (TacheEntity tache : listeTaches) {
            if (tache.getMembre().equals(membre)) {
                listeTachesDuMembre.add(tache);
            }
        }
        return listeTachesDuMembre;
    }

    public void ajouterTache(TacheEntity tache){
        tacheRepository.save(tache);
    }

    public void supprimerTache(Long id){
        tacheRepository.deleteById(id);
    }

    public void sort(List<TacheEntity> taches) {
        taches.sort(new Comparator<TacheEntity>() {
            @Override
            public int compare(TacheEntity o1, TacheEntity o2) {
                return o2.getImportance().compareTo(o1.getImportance());
            }
        });
    }

    public void sortDate(List<TacheEntity> taches) {
        taches.sort(new Comparator<TacheEntity>() {
            @Override
            public int compare(TacheEntity o1, TacheEntity o2) {
                return o1.getDate_debut().compareTo(o2.getDate_debut());
            }
        });
    }
}
