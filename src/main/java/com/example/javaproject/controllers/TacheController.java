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
import java.util.Objects;
import java.util.stream.Collectors;

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
        list = list.stream().filter(tache -> !Objects.equals(tache.getEtat(), "Archivée")).collect(Collectors.toList());
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
            nouvelleTache.setMembre(nouveauMembre); // lie le membre et la tache
        }else{
            Long idMembre = nouvelleTache.getMembre().getIdMembre(); // recupère le membre
            MembreEntity membre = membreService.recupereMembre(idMembre);
            nouvelleTache.setMembre(membre); //associe membre à la tache
        }
        nouvelleTache.setEtat("En cours");
        nouvelleTache.setDate_debut(dateDebutString);
        tacheService.ajouterTache(nouvelleTache); //ajoute tache dans la base de données
        nouvelleNotif.setType("Nouvelle Tache");
        nouvelleNotif.setTexte("Nouvelle tache mise à jour dans le gestionnaire");
        nouvelleNotif.setVu("Non");
        nouvelleNotif.setTache(nouvelleTache.getTitre());
        notificationService.ajouterNotification(nouvelleNotif);
        return "redirect:/taches"; //redirige vers les taches
    }

    // Affiche le formulaire d'ajout d'un membre
    @GetMapping("/ajoutmembre")
    public String ajoutMembreFormulaire(Model model){
        model.addAttribute("nouveauMembre", new MembreEntity()); //envoie vers le jsp
        return "ajouterMembre"; //appel du fichier jsp
    }

    // Ajouter un membre dans la base
    @PostMapping("/ajoutmembre")
    public String ajouterMembre(@ModelAttribute MembreEntity nouveauMembre){
        membreService.ajouterMembre(nouveauMembre); //ajoute tache dans la base de données
        return "redirect:/"; //redirige vers les taches
    }

    // Affiche les détails d'une tache
    @GetMapping("/detail/{id}")
    public String detailTache(@PathVariable("id") Long id, Model model){
        TacheEntity tache = tacheService.recupereTache(id); // recupère l'id de la tache
        model.addAttribute("tache", tache); //envoie vers le jsp
        return "detailTache"; //appel du fichier jsp
    }

    @GetMapping("/cours/{id}")
    public String enCoursTache(@PathVariable("id") Long id){
        TacheEntity tache = tacheService.recupereTache(id);
        tache.setEtat("En cours");
        tacheService.ajouterTache(tache);
        NotificationEntity nouvelleNotif = new NotificationEntity();
        nouvelleNotif.setType("Statut");
        nouvelleNotif.setTexte("Passage du statut en 'En cours' pour la tâche n°"+id);
        nouvelleNotif.setVu("Non");
        nouvelleNotif.setTache(tache.getTitre());
        nouvelleNotif.setMembre(tache.getMembre().getIdMembre());
        notificationService.ajouterNotification(nouvelleNotif);
        return "redirect:/";
    }

    @GetMapping("/pause/{id}")
    public String pauseTache(@PathVariable("id") Long id){
        TacheEntity tache = tacheService.recupereTache(id);
        tache.setEtat("En pause");
        tacheService.ajouterTache(tache);
        NotificationEntity nouvelleNotif = new NotificationEntity();
        nouvelleNotif.setType("Statut");
        nouvelleNotif.setTexte("Passage du statut en 'En pause' pour la tâche n°"+id);
        nouvelleNotif.setVu("Non");
        nouvelleNotif.setTache(tache.getTitre());
        nouvelleNotif.setMembre(tache.getMembre().getIdMembre());
        notificationService.ajouterNotification(nouvelleNotif);
        return "redirect:/";
    }

    @GetMapping("/reprendre/{id}")
    public String reprendreTache(@PathVariable("id") Long id){
        TacheEntity tache = tacheService.recupereTache(id);
        tache.setEtat("En cours");
        tacheService.ajouterTache(tache);
        NotificationEntity nouvelleNotif = new NotificationEntity();
        nouvelleNotif.setType("Statut");
        nouvelleNotif.setTexte("Passage du statut en 'En cours' pour la tâche n°"+id);
        nouvelleNotif.setVu("Non");
        nouvelleNotif.setTache(tache.getTitre());
        nouvelleNotif.setMembre(tache.getMembre().getIdMembre());
        notificationService.ajouterNotification(nouvelleNotif);
        return "redirect:/";
    }

    //Terminer une tache selon son id
    @GetMapping("/terminer/{id}")
    public String terminerTache(@PathVariable("id") Long id){
        TacheEntity tache = tacheService.recupereTache(id);
        tache.setEtat("Terminée");
        tacheService.ajouterTache(tache);
        NotificationEntity nouvelleNotif = new NotificationEntity();
        nouvelleNotif.setType("Statut");
        nouvelleNotif.setTexte("Passage du statut en 'Terminée' pour la tâche n°"+id);
        nouvelleNotif.setVu("Non");
        nouvelleNotif.setTache(tache.getTitre());
        nouvelleNotif.setMembre(tache.getMembre().getIdMembre());
        notificationService.ajouterNotification(nouvelleNotif);
        return "redirect:/";
    }

    @GetMapping("/archiver/{id}")
    public String archiverTache(@PathVariable("id") Long id){
        TacheEntity tache = tacheService.recupereTache(id);
        tache.setEtat("Archivée");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateFin = LocalDate.now();
        String dateFinString = dateFin.format(formatter);
        tache.setDate_fin(dateFinString);
        tacheService.ajouterTache(tache);
        NotificationEntity nouvelleNotif = new NotificationEntity();
        nouvelleNotif.setType("Statut");
        nouvelleNotif.setTexte("Passage du statut en 'Archivée' pour la tâche n°"+id);
        nouvelleNotif.setVu("Non");
        nouvelleNotif.setTache(tache.getTitre());
        nouvelleNotif.setMembre(tache.getMembre().getIdMembre());
        notificationService.ajouterNotification(nouvelleNotif);
        return "redirect:/";
    }

    //Supprime une tache selon son id
    @GetMapping("/supptache/{id}")
    public String supprimerTache(@PathVariable("id") Long id){
        NotificationEntity nouvelleNotif = new NotificationEntity();
        nouvelleNotif.setType("Statut");
        nouvelleNotif.setTexte("Suppression de la tâche "+id);
        nouvelleNotif.setVu("Non");
        notificationService.ajouterNotification(nouvelleNotif);
        tacheService.supprimerTache(id); //supprime la tache
        return "redirect:/"; //redirige vers le fichier jsp
    }

    // Afficher le formulaire pour modifier une tache
    @GetMapping("/modiftache/{id}")
    public String modifierTacheFormulaire(@PathVariable("id") Long id, Model model) {
        TacheEntity tache = tacheService.recupereTache(id); //récupère la tache
        model.addAttribute("tache", tache); //envoie vers le jsp
        model.addAttribute("membres", membreService.recupererLesMembres()); //envoie vers le jsp
        return "/modifierTache";
    }

    @PostMapping("/modiftache/{id}")
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

    // Liste de toutes les notifs
    @RequestMapping(value = "/notifs", method = RequestMethod.GET)
    public String listeNotifs(Model model){
        List<NotificationEntity> list = notificationService.recupereToutesLesNotifications(); //notifs dans la base de données
        notificationService.sort(list);
        model.addAttribute("notifs", list); //envoie vers le fichier jsp
        return "listeNotifs"; //appel du fichier jsp
    }

    //Terminer une notif selon son id
    @GetMapping("/vu/{id}")
    public String vuNotif(@PathVariable("id") Long id){
        NotificationEntity notif = notificationService.recupereNotification(id);
        notif.setVu("Oui");
        notificationService.ajouterNotification(notif);
        return "redirect:/notifs";
    }

    // Rapport
    @RequestMapping(value = "/rapportList", method = RequestMethod.GET)
    public String rapport(Model model){
        List<MembreEntity> list = membreService.recupererLesMembres();
        membreService.sort(list);
        model.addAttribute("membres", list); //envoie vers le fichier jsp
        return "rapport"; //appel du fichier jsp
    }

    @GetMapping("/rapport/{id}")
    public String rapportMembre(@PathVariable("id") Long id, Model model){
        MembreEntity membre = membreService.recupereMembre(id);
        List<TacheEntity> listTaches = tacheService.recupereTachesParMembre(membre);
        int nombre = listTaches.size();
        model.addAttribute("taches", listTaches); //envoie vers le fichier jsp
        model.addAttribute("membre", membre); //envoie vers le fichier jsp
        model.addAttribute("nombre", nombre); //envoie vers le fichier jsp
        return "/rapportMembre";
    }

    @RequestMapping(value = "/archive", method = RequestMethod.GET)
    public String listeArchive(Model model){
        List<TacheEntity> list = tacheService.recupereToutesLesTaches(); //taches dans la base de données
        list = list.stream().filter(tache -> Objects.equals(tache.getEtat(), "Archivée")).collect(Collectors.toList());
        tacheService.sort(list);
        model.addAttribute("taches", list); //envoie vers le fichier jsp
        return "listeArchives"; //appel du fichier jsp
    }
}
