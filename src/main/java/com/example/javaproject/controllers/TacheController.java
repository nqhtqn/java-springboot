package com.example.javaproject.controllers;

import com.example.javaproject.entity.MembreEntity;
import com.example.javaproject.entity.NotificationEntity;
import com.example.javaproject.entity.TacheEntity;
import com.example.javaproject.services.MembreService;
import com.example.javaproject.services.NotificationService;
import com.example.javaproject.services.TacheService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@Log
public class TacheController {

    @Autowired
    private TacheService tacheService;

    @Autowired
    private MembreService membreService;

    @Autowired
    private NotificationService notificationService;

    // Liste de toutes les taches
    @RequestMapping(value = "/taches", method = RequestMethod.GET)
    public String listeTaches(Model model){
        List<TacheEntity> list = tacheService.recupereToutesLesTaches(); //taches dans la base de données
        tacheService.sort(list);
        model.addAttribute("taches", list); //envoie vers le fichier jsp
        return "listeTaches"; //appel du fichier jsp
    }

    // Affiche le formulaire d'ajout de tache
    @GetMapping("/ajouttache")
    public String ajoutTacheFormulaire(Model model){
        List<MembreEntity> membres = membreService.recupererLesMembres();  //recupère tous les membres
        model.addAttribute("membres", membres); //envoie vers le jsp
        model.addAttribute("nouvelleTache", new TacheEntity()); //envoie vers le jsp
        return "ajouterTache"; //appel du fichier jsp
    }

    // Ajouter une tache dans la base
    @PostMapping("/ajouttache")
    public String ajouterTache(@ModelAttribute TacheEntity nouvelleTache,
                               @RequestParam(value = "nom", required = false) String nom,
                               @RequestParam(value = "prenom", required = false) String prenom,
                               @RequestParam(value = "equipe", required = false) String equipe){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateDebut = LocalDate.now();
        String dateDebutString = dateDebut.format(formatter);
        NotificationEntity nouvelleNotif = new NotificationEntity();

        if (!nom.isEmpty()) { //test si nouveau membre
            MembreEntity nouveauMembre = new MembreEntity(); // crée un nouveau membre
            nouveauMembre.setNom(nom);
            nouveauMembre.setPrenom(prenom);
            nouveauMembre.setEquipe(equipe);
            membreService.ajouterMembre(nouveauMembre); //ajout membre dans la base
            nouvelleTache.setMembre(nouveauMembre);
            nouvelleNotif.setMembre(nouveauMembre); // lie le membre et la tache
        }else{
            Long idMembre = nouvelleTache.getMembre().getIdMembre(); // recupère le membre
            MembreEntity membre = membreService.recupereMembre(idMembre);
            nouvelleTache.setMembre(membre);
            nouvelleNotif.setMembre(membre); //associe membre à la tache
        }
        nouvelleTache.setEtat("En cours");
        nouvelleTache.setDate_debut(dateDebutString);
        tacheService.ajouterTache(nouvelleTache); //ajoute tache dans la base de données
        nouvelleNotif.setType("Nouvelle Tache");
        nouvelleNotif.setTexte("Nouvelle tache mise à jour dans le gestionnaire");
        nouvelleNotif.setVu("Non");
        nouvelleNotif.setTache(nouvelleTache);
        notificationService.ajouterNotification(nouvelleNotif);
        return "redirect:/taches"; //redirige vers les taches
    }

    // Affiche les détails d'une tache
    @GetMapping("/detail/{id}")
    public String detailTache(@PathVariable("id") Long id, Model model){
        TacheEntity tache = tacheService.recupereTache(id); // recupère l'id de la tache
        model.addAttribute("tache", tache); //envoie vers le jsp
        return "detailTache"; //appel du fichier jsp
    }

    //Supprime une tache selon son id
    @GetMapping("/supptache/{id}")
    public String supprimerTache(@PathVariable("id") Long id){
        tacheService.supprimerTache(id); //supprime la tache
        return "redirect:/taches"; //redirige vers le fichier jsp
    }

    // Afficher le formulaire pour modifier une tache
    @GetMapping("/modiftache/{id}")
    public String modifierTacheFormulaire(@PathVariable("id") Long id, Model model) {
        TacheEntity tache = tacheService.recupereTache(id); //récupère la tache
        model.addAttribute("tache", tache); //envoie vers le jsp
        model.addAttribute("membres", membreService.recupererLesMembres()); //envoie vers le jsp
        return "/modifierTache";
    }

    @PostMapping("/modiftaches/{id}")
    public String editTache(@PathVariable("id") Long id,
                           @ModelAttribute TacheEntity tache,
                           @RequestParam(value = "nom", required = false) String nom,
                           @RequestParam(value = "prenom", required = false) String prenom,
                           @RequestParam(value = "equipe", required = false) String equipe) {
        tache.setId(id);

        if (!nom.isEmpty()) {

            MembreEntity nouveauMembre = new MembreEntity();
            nouveauMembre.setNom(nom);
            nouveauMembre.setPrenom(prenom);
            nouveauMembre.setEquipe(equipe);
            membreService.ajouterMembre(nouveauMembre);
            tache.setMembre(nouveauMembre);
        }
        tacheService.ajouterTache(tache);
        return "redirect:/taches";
    }

}
