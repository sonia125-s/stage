package com.example.gestionlogistique.web;

import com.example.gestionlogistique.dtos.CommandeClientDTO;
import com.example.gestionlogistique.enums.EnumEtat;
import com.example.gestionlogistique.services.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class LivraisonAPI {


    @Autowired
    private LivraisonService livraisonService;

    @GetMapping("/logistique/livraisonCommande")
    List<CommandeClientDTO> getCommandePreparation(@RequestParam("etat") EnumEtat etat ,@RequestParam("id") Long id){
        return  livraisonService.listCommandeByEtat(etat,id);
    }

    @GetMapping("/logistique/mettreLivraison/{id}")
    void metreLivraison(@PathVariable Long id ){
        livraisonService.metreCommandeLivraison(id);
    }
    @GetMapping("/logistique/mettreRetour/{id}")
    void metreRetour(@PathVariable Long id ){
        livraisonService.metreCommandeRetour(id);
    }
    @GetMapping("/logistique/mettreAcceptation/{id}")
    void metreAccepte(@PathVariable Long id ){
        livraisonService.metreCommandeAcceptation(id);
    }
}
