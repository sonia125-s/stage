package com.example.gestionlogistique.web;

import com.example.gestionlogistique.dtos.TransportDTO;
import com.example.gestionlogistique.dtos.VehiculeDTO;
import com.example.gestionlogistique.entities.Transport;
import com.example.gestionlogistique.mappers.ClientMapper;
import com.example.gestionlogistique.repositories.TransportRepository;
import com.example.gestionlogistique.services.TransportService;
import com.example.gestionlogistique.services.TransporteurRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class TransportAPI {
    @Autowired
    TransportService transportService;
    @Autowired
    TransporteurRestClient transporteurRestClient;
    @Autowired
    ClientMapper clientMapper;
    @Autowired
    TransportRepository transportRepository;

    @GetMapping("logistique/vehicule")
    public List<VehiculeDTO> getAllVehicule() {
        return transportService.getAllVehicule();
    }

    @GetMapping("logistique/transport")
    public List<TransportDTO> getAllTransport() {
        List<Transport> transportList = transportRepository.findAll();

        List<Transport> transportList1 = transportList.stream()
                .map(transport -> {
                    transport.setTransporteur(transporteurRestClient.findTransporteurById(transport.getTransporteurId()));
                    return transport;
                }).collect(Collectors.toList());
        return transportList1.stream().map(transport -> clientMapper.fromTransport(transport)).collect(Collectors.toList());
    }
    @GetMapping("logistique/attributTransportExiste")
    public void attributTransportExiste(@RequestParam("idCommande") Long idCommnade , @RequestParam("idTransport") Long idTransport ){
        transportService.attribuerCommandeTransport(idCommnade,idTransport);
    }
    @GetMapping("logistique/addTransport")
    public  TransportDTO addTransport(@RequestParam("idCommande") Long idCommande,
                                      @RequestParam("type") String type,
                                      @RequestParam("dateSortie") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateSortie,
                                      @RequestParam("idTransporteur") Long idTransporteur,
                                      @RequestParam("idVehicule") Long idVehicule){
       return transportService.saveTransport(idCommande,type,dateSortie,idTransporteur,idVehicule);
    }

}
