package com.example.livrespirngboot.controllers;

import com.example.livrespirngboot.entity.AuteurEntity;
import com.example.livrespirngboot.entity.LivreEntity;
import com.example.livrespirngboot.services.AuteurService;
import com.example.livrespirngboot.services.LivreService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Controller
@Log
public class LivreController {

    @Autowired
    private LivreService livreService;

    @Autowired
    private AuteurService auteurService;

    // Lister tous les livres dans la base de données
    @RequestMapping(value = "/livres", method = RequestMethod.GET)
    public String listeLivres(Model model){
        // Récupérer les livres dans la base de données
        List<LivreEntity> list = livreService.recupereTousLesLivres();

        // Envoyer la liste de livres vers le fichier jsp
        model.addAttribute("livres", list);

        // Appel du fichier listeLivres.jsp
        return "listeLivres";
    }


    // Afficher le formulaire permettant d'ajouter un livre
    @GetMapping("/ajoutlivre")
    public String ajoutLivreFormulaire(Model model){
        // Récupérer tous les auteurs dans la base de données pour créer le menu select dans le jsp
        List<AuteurEntity> auteurs = auteurService.recupererLesAuteurs();

        //Envoi de la liste des auteurs vers le jsp
        model.addAttribute("auteurs", auteurs);

        //J'envoie un livreEntity vide vers le jsp qui sera utilisé automatiquement par le formulaire pour créer un livre
        model.addAttribute("nouveauLivre", new LivreEntity());

        // Appel du fichier ajouterLivre.jsp
        return "ajouterLivre";
    }

    // Ajouter un livre dans la base
    /*
    * Il y a 3 paramètres:
    * nouveauLivre : c'est un livreEntity (qui a été envoyé précédement) qui contient les informations
    * du livre rempli par le formulaire
    *
    * nomAuteur: pour récupérer le nom de l'auteur si l'utilisateur veut créer un nouveau auteur.
    * required false veut dire que ce n'est pas obligatoire.
    *
    * prenomAuteur: pour récupérer le prénom de l'auteur si l'utilisateur veut créer un nouveau auteur
    * */
    @PostMapping("/ajoutlivre")
    public String ajouterLivre(@ModelAttribute LivreEntity nouveauLivre,
                             @RequestParam(value = "nomAuteur", required = false) String nomAuteur,
                             @RequestParam(value = "prenomAuteur", required = false) String prenomAuteur){

        // Permet de tester si l'utilisateur créer un nouveau auteur qui n'est pas dans la base
        if (!nomAuteur.isEmpty()) {
            // Créer un nouvel auteur
            // Ce n'est pas la peine de donner l'id puisque c'est générer
            AuteurEntity nouvelAuteur = new AuteurEntity();
            nouvelAuteur.setNom(nomAuteur);
            nouvelAuteur.setPrenom(prenomAuteur);

            //Ajouter l'auteur dans la base
            auteurService.ajouterAuteur(nouvelAuteur);

            // Associer l'auteur au livre qui sera ajouter dans la base après
            nouveauLivre.setAuteur(nouvelAuteur);
        }else{
            // Récupérer l'auteur depuis la base pour l'associer au livre
            Long idAuteur = nouveauLivre.getAuteur().getIdAuteur();
            AuteurEntity auteur = auteurService.recupereAuteur(idAuteur);

            // Associer l'auteur au livre qui sera ajouter dans la base après
            nouveauLivre.setAuteur(auteur);
        }

        // Ajouter le livre dans la base de données
        livreService.ajouterLivre(nouveauLivre);

        // Rediriger vers la liste de livres
        return "redirect:/livres";

    }

    // Affiche les détails d'un livre
    @GetMapping("/detail/{id}")
    public String detailLivre(@PathVariable("id") Long id, Model model){

        // Récuprérer le livre selon son id
        LivreEntity livre = livreService.recupereLivre(id);

        // Envoyer le livre récupéré vers le jsp
        model.addAttribute("livre", livre);

        // Appel du fichier detailLivre.jsp
        return "detailLivre";
    }

    // Supprimer un livre selon son id
    @GetMapping("/supplivres/{id}")
    public String supprimerLivre(@PathVariable("id") Long id){
        // Supprimer le livre dans la base de données
        livreService.supprimerLivre(id);

        // Rediriger vers la liste de livres
        return "redirect:/livres";
    }

    // Afficher le formulaire permettant de modifier un livre selon son id
    @GetMapping("/modiflivres/{id}")
    public String modifierLivreFormulaire(@PathVariable("id") Long id, Model model) {
        // récupére le livre à modifier
        LivreEntity livre = livreService.recupereLivre(id);

        // envoyer les détails du livre à modifier vers le jsp
        model.addAttribute("livreAModifier", livre);

        // Envoyer tous les auteurs de la base de données vers le jsp
        model.addAttribute("auteurs", auteurService.recupererLesAuteurs());
        return "/modifierLivre";
    }

    @PostMapping("/modiflivres/{id}")
    public String editBook(@PathVariable("id") Long id,
                           @ModelAttribute LivreEntity livreAModifier,
                           @RequestParam(value = "nomAuteur", required = false) String nomAuteur,
                           @RequestParam(value = "prenomAuteur", required = false) String prenomAuteur) {
        /** il n'y a pas de fonction update dans crudrepository donc on va utiliser
         * le save mais écraser un livre qui existe déjà dans la base en lui
         * donnant un id qui existe déjà dans la base
         * */

        livreAModifier.setId(id);


        // Permet de tester si l'utilisateur créer un nouveau auteur qui n'est pas dans la base
        if (!nomAuteur.isEmpty()) {
            // Créer un nouvel auteur
            AuteurEntity nouvelAuteur = new AuteurEntity();
            nouvelAuteur.setNom(nomAuteur);
            nouvelAuteur.setPrenom(prenomAuteur);

            //Ajouter l'auteur dans la base
            auteurService.ajouterAuteur(nouvelAuteur);

            // Associer l'auteur au livre qui sera modifier dans la base après
            livreAModifier.setAuteur(nouvelAuteur);
        }

        livreService.ajouterLivre(livreAModifier);

        return "redirect:/livres";
    }

}
