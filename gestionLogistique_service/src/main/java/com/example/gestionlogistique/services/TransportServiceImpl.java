package com.example.gestionlogistique.services;

import com.example.gestionlogistique.dtos.TransportDTO;
import com.example.gestionlogistique.dtos.VehiculeDTO;
import com.example.gestionlogistique.entities.CommandeClient;
import com.example.gestionlogistique.entities.Transport;
import com.example.gestionlogistique.entities.Vehicule;
import com.example.gestionlogistique.mappers.ClientMapper;
import com.example.gestionlogistique.repositories.CommandeRepository;
import com.example.gestionlogistique.repositories.TransportRepository;
import com.example.gestionlogistique.repositories.VehiculeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransportServiceImpl implements TransportService{
    @Autowired
    VehiculeRepository vehiculeRepository;
    @Autowired
    ClientMapper clientMapper;
    @Autowired
    TransportRepository transportRepository;
    @Autowired
    TransporteurRestClient transporteurRestClient;
    @Autowired
    CommandeRepository commandeRepository;



    @Override
    public List<VehiculeDTO> getAllVehicule(){
        List<Vehicule> vehiculeList= vehiculeRepository.findAll();
        return  vehiculeList.stream().map(vehicule ->clientMapper.fromVehicule(vehicule) ).collect(Collectors.toList());
    }
    @Override
    public void attribuerCommandeTransport(Long idCommande, Long idTransport){
        CommandeClient commandeClient= commandeRepository.getReferenceById(idCommande);
        Transport transport=transportRepository.getReferenceById(idTransport);
        commandeClient.setTransport(transport);

    }
    @Override
    public TransportDTO saveTransport(Long idCommande , String type, Date dateSortie, Long idTransporteur, Long idVehicule){
       Transport transport= new Transport();
       CommandeClient commandeClient=commandeRepository.getById(idCommande);
       transport.setType(type);
        transport.setDateSortie(dateSortie);
        transport.setTransporteurId(idTransporteur);
        Vehicule vehicule=vehiculeRepository.getById(idVehicule);
        transport.setVehicule(vehicule);
        transportRepository.save(transport);
        commandeClient.setTransport(transport);
        commandeRepository.save(commandeClient);
        TransportDTO transportDTO=clientMapper.fromTransport(transport);
        return transportDTO;

    }

}
