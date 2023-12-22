package com.example.javaproject.controllers;

import com.example.javaproject.entity.TacheEntity;
import com.example.javaproject.services.MembreService;
import com.example.javaproject.services.NotificationService;
import com.example.javaproject.services.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private TacheService tacheService;

    @Autowired
    private MembreService membreService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping(value = "/")
    public String listeTachesParDate(Model model){
        List<TacheEntity> list = tacheService.recupereToutesLesTaches(); //taches dans la base de données
        list = list.stream().filter(tache -> !Objects.equals(tache.getEtat(), "Archivée")).collect(Collectors.toList());
        tacheService.sortDate(list);
        model.addAttribute("taches", list); //envoie vers le fichier jsp
        return "home"; //appel du fichier jsp
    }
}
