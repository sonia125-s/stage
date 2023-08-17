package com.example.gestionlogistique.web;

import com.example.gestionlogistique.dtos.ClientMoraleDTO;
import com.example.gestionlogistique.dtos.ClientPhysiqueDTO;
import com.example.gestionlogistique.dtos.CommandeClientDTO;
import com.example.gestionlogistique.dtos.DetailleCommandeDTO;
import com.example.gestionlogistique.repositories.ClientPhysiqueRepository;
import com.example.gestionlogistique.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ClientAPI {

    @Autowired
    private ClientService clientService;


    @GetMapping("logistique/clientsMorale")
    public List<ClientMoraleDTO> clientMoraleDTOList(){
       return clientService.listClientMorale();
    }
   @GetMapping("logistique/clientsPhysique")
   public List<ClientPhysiqueDTO> clientPhysiqueDTOList(){
        return clientService.listClientPhysique();
  }

@GetMapping("logistique/clientMorale/search")
    public  List<ClientMoraleDTO>serchClientMorale(@RequestParam(name="keyword",defaultValue = "") String keyword){
       return  clientService.searchClientMorale("%" + keyword + "%");
}


    @GetMapping("logistique/clientPhysique/search")
    public  List<ClientPhysiqueDTO>serchClientPhysique(@RequestParam(name="keyword",defaultValue = "") String keyword){
        return  clientService.searchClientPhysique("%" + keyword + "%");
    }




    @GetMapping("logistique/savecommandeclient")
    public CommandeClientDTO saveCommandeClient(@RequestParam("dateEnvoi") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateEnvoi,
                                                                @RequestParam("lieu") String lieu,
                                                                @RequestParam("idClient") Long idClient) {
        CommandeClientDTO commandeClientDTO =clientService.saveCommandeClient(dateEnvoi,lieu, idClient);
        return  commandeClientDTO;
    }
  @GetMapping("logistique/ligneCommande")
    public void saveLigneCommande(@RequestParam("idCommande") Long idCommande,@RequestParam("idProduit") Long idProduit , @RequestParam("quantite") int quantite ){
        clientService.ajouterLigneCommande(idCommande , idProduit , quantite);
  }
  @GetMapping("logistique/detailleCommande/{id}")
    public DetailleCommandeDTO affichDetailleCommande(@PathVariable(name = "id") Long idCommande){
        return clientService.afficherDetailCommande(idCommande);
  }
  @GetMapping("logistique/commandesClient/{idClient}")
    public  List<CommandeClientDTO> affichComandeClient(@PathVariable(name = "idClient") Long idClient){
        return clientService.affichLisCommandeClient(idClient);
  }
@DeleteMapping("logistique/commandeClient/{id}")
    public void deleteCommande(@PathVariable Long id){
        clientService.deleteCommande(id);
}
@GetMapping("logistique/allCommande")
    public List<CommandeClientDTO> getAllCommande(){
        return  clientService.afficheCommandes();
}
@GetMapping("logistique/commandeById/{id}")
    public List<CommandeClientDTO> getCommandeById(@PathVariable Long id){
     return  clientService.getCommandeByID(id)  ;
}
}
