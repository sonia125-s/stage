package com.example.gestionlogistique.services;

import com.example.gestionlogistique.dtos.CommandeClientDTO;
import com.example.gestionlogistique.entities.CommandeClient;
import com.example.gestionlogistique.enums.EnumEtat;
import com.example.gestionlogistique.mappers.ClientMapper;
import com.example.gestionlogistique.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivraisonServiceImpl  implements LivraisonService{
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<CommandeClientDTO> listCommandeByEtat(EnumEtat etat , Long id){
        List<CommandeClient> commandeClients = commandeRepository.findByEtat(etat);

        List<CommandeClientDTO> commandeClientDTOS = commandeClients.stream()
                .filter(commandeClient -> commandeClient.getTransport().getTransporteurId() == id)
                .map(commandeClient -> clientMapper.fromCommandeClient(commandeClient))
                .collect(Collectors.toList());

        return commandeClientDTOS;

    }


    @Override
    public void metreCommandeLivraison(Long id){
        CommandeClient commandeClient=commandeRepository.findById(id).orElse(null);
        commandeClient.setEtat(EnumEtat.LIVRAISON);
        commandeRepository.save(commandeClient);
    }

    @Override
    public void metreCommandeRetour(Long id){
        CommandeClient commandeClient=commandeRepository.findById(id).orElseThrow(null);
        commandeClient.setEtat(EnumEtat.RETOUR);
    }
    @Override
    public void metreCommandeAcceptation(Long id){
        CommandeClient commandeClient=commandeRepository.findById(id).orElseThrow(null);
        commandeClient.setEtat(EnumEtat.ACCEPTATION);
    }



}
