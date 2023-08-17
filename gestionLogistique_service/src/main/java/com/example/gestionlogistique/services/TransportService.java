package com.example.gestionlogistique.services;

import com.example.gestionlogistique.dtos.TransportDTO;
import com.example.gestionlogistique.dtos.VehiculeDTO;

import java.util.Date;
import java.util.List;

public interface TransportService {
    List<VehiculeDTO> getAllVehicule();

    void attribuerCommandeTransport(Long idCommande, Long idTransport);

    TransportDTO saveTransport(Long idCommande, String type, Date dateSortie, Long idTransporteur, Long idVehicule);

    //List<TransportDTO> getAllTransport();

    //List<TransportDTO> getAllTransport();
}
