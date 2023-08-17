package com.example.gestionlogistique.mappers;

import com.example.gestionlogistique.dtos.*;
import com.example.gestionlogistique.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientMapper {


    public ClientMorale fromClientMoreleDTO(ClientMoraleDTO clientMoraleDTO){
        ClientMorale clientMorale=new ClientMorale();
        BeanUtils.copyProperties(clientMoraleDTO,clientMorale);
        return clientMorale;
    }

    public ClientMoraleDTO fromClientMorele(ClientMorale clientMorale){
        ClientMoraleDTO clientMoraleDTO=new ClientMoraleDTO();
        BeanUtils.copyProperties(clientMorale , clientMoraleDTO);
        return clientMoraleDTO;
    }

    public ClientPhysique fromClientPhysiqueDTO(ClientPhysiqueDTO clientPhysiqueDTO){
        ClientPhysique clientPhysique= new ClientPhysique();
        BeanUtils.copyProperties(clientPhysiqueDTO, clientPhysique);
        return clientPhysique;
    }


    public ClientPhysiqueDTO fromClientPhysique(ClientPhysique clientPhysique){
        ClientPhysiqueDTO clientPhysiqueDTO=new ClientPhysiqueDTO();
        BeanUtils.copyProperties(clientPhysique , clientPhysiqueDTO);
        return clientPhysiqueDTO;
    }



public ClientDTO fromClient (Client client){
        ClientDTO clientDTO=new ClientDTO();
        if(client instanceof ClientMorale){
            ClientMorale clientMorale=(ClientMorale) client;
            ClientMoraleDTO clientMoraleDTO=new ClientMoraleDTO();
            BeanUtils.copyProperties(clientMorale,clientMoraleDTO);
            clientDTO=clientMoraleDTO;
        }else{
            ClientPhysique clientPhysique=(ClientPhysique) client;
            ClientPhysiqueDTO clientPhysiqueDTO=new ClientPhysiqueDTO();
            BeanUtils.copyProperties(clientPhysique,clientPhysiqueDTO);
            clientDTO=clientPhysiqueDTO;
        }

        return clientDTO;
}


public  Client fromClietDTO (ClientDTO clientDTO){
        Client client=new Client();
        BeanUtils.copyProperties(clientDTO,client);
        return  client;
}

public  CommandeClientDTO fromCommandeClient(CommandeClient commandeClient){

        CommandeClientDTO commandeClientDTO=new CommandeClientDTO();
        BeanUtils.copyProperties(commandeClient,commandeClientDTO);
        //Client client=commandeClient.getClient();
        //commandeClientDTO.setClientDTO(fromClient(client));
        return  commandeClientDTO;
}



public DetailleCommandeDTO froCommandeClient(CommandeClient commandeClient ){
        DetailleCommandeDTO detailleCommandeDTO= new DetailleCommandeDTO();
        detailleCommandeDTO.setId(commandeClient.getId());
        detailleCommandeDTO.setDate_creation(commandeClient.getDate_creation());
        detailleCommandeDTO.setDate_envoi(commandeClient.getDate_envoi());
        detailleCommandeDTO.setPrixTotal(commandeClient.getPrixTotal());
        detailleCommandeDTO.setLieu(commandeClient.getLieu());
        List<LigneCommandeClient> listLigne= commandeClient.getLignesCommandeClient();
        detailleCommandeDTO.setLigneCommandeDTOList(listLigne.stream().map(ligne-> {
                    LigneCommandeDTO ligneCommandeDTO= new LigneCommandeDTO();
                    ligneCommandeDTO.setId(ligne.getId());
                    ligneCommandeDTO.setQuantite(ligne.getQuantite());
                    ligneCommandeDTO.setProduitDTO( fromProduit(ligne.getProduit()))  ;
                    return  ligneCommandeDTO;
                }).collect(Collectors.toList())
        );
 return  detailleCommandeDTO;

}



public ProduitDTO fromProduit (Produit produit) {
        ProduitDTO produitDTO=new ProduitDTO();
        BeanUtils.copyProperties(produit,produitDTO);
        return produitDTO;
}
    public Produit fromProduitDTO (ProduitDTO produitDTO) {
        Produit produit=new Produit();
        BeanUtils.copyProperties(produitDTO,produit);
        return produit;
    }

    public VehiculeDTO fromVehicule (Vehicule vehicule){
        VehiculeDTO vehiculeDTO=new VehiculeDTO();
        BeanUtils.copyProperties(vehicule,vehiculeDTO);
        return vehiculeDTO;
    }
    public Vehicule fromVehiculeDTO (VehiculeDTO vehiculeDTO){
        Vehicule vehicule=new Vehicule();
        BeanUtils.copyProperties(vehiculeDTO,vehicule);
        return vehicule;
    }
    public TransportDTO fromTransport(Transport transport){
        TransportDTO transportDTO=new TransportDTO();
        transportDTO.setId(transport.getId());
        transportDTO.setType(transport.getType());
        transportDTO.setDateSortie(transport.getDateSortie());
        transportDTO.setTransporteur(transport.getTransporteur());
        Vehicule vehicule=transport.getVehicule();
        VehiculeDTO vehiculeDTO=fromVehicule(vehicule);
        transportDTO.setVehiculeDTO( vehiculeDTO );
        return transportDTO;
    }
//***********************************
public ProduitDTO fromStockeProduit (StockeProduit produit) {
    ProduitDTO produitDTO=new ProduitDTO();
    BeanUtils.copyProperties(produit,produitDTO);
    return produitDTO;
}
    public StockeProduit fromStockeProduitDTO (ProduitDTO produitDTO) {
        StockeProduit produit=new StockeProduit();
        BeanUtils.copyProperties(produitDTO,produit);
        return produit;
    }


}
