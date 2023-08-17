package com.example.gestionlogistique.services;

import com.example.gestionlogistique.dtos.*;
import com.example.gestionlogistique.entities.*;
import com.example.gestionlogistique.enums.EnumEtat;
import com.example.gestionlogistique.mappers.ClientMapper;
import com.example.gestionlogistique.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{
    @Autowired
    ClientMoraleRepository clientMoraleRepository;
    @Autowired
    ClientPhysiqueRepository clientPhysiqueRepository;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CommandeRepository commandeRepository;
   @Autowired
   private ProduitRepository produitRepository;
   @Autowired
   private LigneCommandeRepository ligneCommandeRepository;
   @Autowired
   private StockeProduitRepository stockeProduitRepository;

@Override
    public List<ClientMoraleDTO> listClientMorale(){
        List<ClientMorale> clientMorales = clientMoraleRepository.findAll();
        List<ClientMoraleDTO> collect=clientMorales.stream().map(clientMorale -> clientMapper.fromClientMorele(clientMorale)).collect(Collectors.toList());
        return collect;
    }
    @Override
  public List<ClientPhysiqueDTO> listClientPhysique(){
        List<ClientPhysique> clientPhysiques = clientPhysiqueRepository.findAll();
      List<ClientPhysiqueDTO> collect=clientPhysiques.stream().map(clientPhysique -> clientMapper.fromClientPhysique(clientPhysique)).collect(Collectors.toList());
        return collect;
    }


@Override
public CommandeClientDTO saveCommandeClient(Date dateEnvoi,String lieu, Long idClient){

    Client client=clientRepository.findById(idClient).orElse(null);
    CommandeClient commandeClient=new CommandeClient();
    commandeClient.setClient(client);
    commandeClient.setDate_creation(new Date());
    commandeClient.setDate_envoi(dateEnvoi);
    commandeClient.setPrixTotal(0l);
    commandeClient.setLieu(lieu);
    commandeClient.setEtat(EnumEtat.PREPARATION);
    commandeRepository.save(commandeClient);
return  clientMapper.fromCommandeClient(commandeClient);


}











@Override
  public List<ClientMoraleDTO> searchClientMorale(String keyword){
    List<ClientMorale> clients = clientMoraleRepository.SearchClient(keyword);
    List<ClientMoraleDTO> clientMoraleDTOS = clients.stream().map(client -> {
        if(client instanceof  ClientMorale){
            ClientMorale clientMorale=(ClientMorale) client;
            return clientMapper.fromClientMorele(clientMorale);

        }else{
             return null;
        }

    }).filter(Objects::nonNull)
            .collect(Collectors.toList());
      return clientMoraleDTOS;
    }

    @Override
    public List<ClientPhysiqueDTO> searchClientPhysique(String keyword){
        List<ClientPhysique> clients = clientPhysiqueRepository.SearchClient(keyword);
        List<ClientPhysiqueDTO> clientPhysiqueDTOS = clients.stream().map(client -> {
                    if(client instanceof  ClientPhysique){
                        ClientPhysique clientPhysique=(ClientPhysique) client;
                        return clientMapper.fromClientPhysique(clientPhysique);

                    }else{
                        return null;
                    }

                }).filter(Objects::nonNull)
                .collect(Collectors.toList());
        return clientPhysiqueDTOS;
    }

/*@Override
    public void ajouterLigneCommande(Long idCommande, Long idProduit, int quantite){
     Produit produit=produitRepository.getById(idProduit);
    CommandeClient commandeClient= commandeRepository.getReferenceById(idCommande);
    LigneCommandeClient ligneCommandeClient=new LigneCommandeClient();
    ligneCommandeClient.setQuantite(quantite);
    ligneCommandeClient.setProduit(produit);
    produit.setQuantiteStocke(produit.getQuantiteStocke()-quantite);
    ligneCommandeClient.setCommandeClient(commandeClient);
    produitRepository.save(produit);
    ligneCommandeRepository.save(ligneCommandeClient);
    commandeClient.setPrixTotal(commandeClient.getPrixTotal()+produit.getPrix()*quantite);


}*/
@Override
    public DetailleCommandeDTO afficherDetailCommande(Long idCommande){
         CommandeClient commandeClient=commandeRepository.getById(idCommande);
         DetailleCommandeDTO detailleCommandeDTO=clientMapper.froCommandeClient(commandeClient);
         return detailleCommandeDTO;
}

@Override
    public List<CommandeClientDTO> affichLisCommandeClient(Long idClient){
     List<CommandeClient> list = commandeRepository.findByClient_Id(idClient);
     return list.stream().map(commandeClient -> {
         return clientMapper.fromCommandeClient(commandeClient);
     }).collect(Collectors.toList());
}
@Override
 public  void deleteCommande(Long idCommande){

    ligneCommandeRepository.deleteLignesCommandeByCommandeId(idCommande);
    commandeRepository.deleteById(idCommande);
 }
 @Override
 public List<CommandeClientDTO> afficheCommandes(){
     List<CommandeClient> list = commandeRepository.findAll();
     return list.stream().map(commandeClient -> {
         return clientMapper.fromCommandeClient(commandeClient);
     }).collect(Collectors.toList());
 }
 @Override
    public List<CommandeClientDTO> getCommandeByID (Long idCommande){
     List<CommandeClient> list = commandeRepository.findAllById(Collections.singleton(idCommande));
     return list.stream().map(commandeClient -> {
         return clientMapper.fromCommandeClient(commandeClient);
     }).collect(Collectors.toList());

 }


//**************************************************************

    @Override
    public void ajouterLigneCommande(Long idCommande, Long idProduit, int quantite){
        StockeProduit stockeproduit=stockeProduitRepository.getById(idProduit);
        if(stockeproduit.getQuantiteStocke()<quantite){
            throw  new RuntimeException("quantite insuffisante");
        }else {
            CommandeClient commandeClient = commandeRepository.getReferenceById(idCommande);

            Produit produit = new Produit();
            //produit.setId(stockeproduit.getId());
            produit.setPrix(stockeproduit.getPrix());
            produit.setDescription(stockeproduit.getDescription());
            produit.setQuantiteStocke(quantite);
            produitRepository.save(produit);
            LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
            ligneCommandeClient.setQuantite(quantite);
            ligneCommandeClient.setProduit(produit);
            stockeproduit.setQuantiteStocke(stockeproduit.getQuantiteStocke() - quantite);
            ligneCommandeClient.setCommandeClient(commandeClient);

            stockeProduitRepository.save(stockeproduit);
            ligneCommandeRepository.save(ligneCommandeClient);
            commandeClient.setPrixTotal(commandeClient.getPrixTotal() + produit.getPrix() * quantite);

        }
    }


}
