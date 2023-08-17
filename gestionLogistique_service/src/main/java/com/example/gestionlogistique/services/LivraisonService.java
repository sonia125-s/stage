package com.example.gestionlogistique.services;

import com.example.gestionlogistique.dtos.CommandeClientDTO;
import com.example.gestionlogistique.enums.EnumEtat;

import java.util.List;

public interface LivraisonService {
    List<CommandeClientDTO> listCommandeByEtat(EnumEtat etat, Long id);

    void metreCommandeLivraison(Long id);

    void metreCommandeRetour(Long id);

    void metreCommandeAcceptation(Long id);
}
