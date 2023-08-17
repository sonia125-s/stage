package com.example.gestionlogistique.services;

import com.example.gestionlogistique.dtos.*;

import java.util.Date;
import java.util.List;

public interface ClientService {
    List<ClientMoraleDTO> listClientMorale();

    List<ClientPhysiqueDTO> listClientPhysique();

    CommandeClientDTO saveCommandeClient(Date dateEnvoi, String lieu, Long idClient);

    List<ClientMoraleDTO> searchClientMorale(String keyword);

   // List<CommandeClientDTO> searchCommande(String id);

    List<ClientPhysiqueDTO> searchClientPhysique(String keyword);

   void ajouterLigneCommande(Long idCommande, Long idProduit, int quantite);

    DetailleCommandeDTO afficherDetailCommande(Long idCommande);

    List<CommandeClientDTO> affichLisCommandeClient(Long idClient);

    void deleteCommande(Long idCommande);

    List<CommandeClientDTO> afficheCommandes();

    List<CommandeClientDTO> getCommandeByID(Long idCommande);


    //List<DetailleCommandeDTO> getAllCommande();
}
