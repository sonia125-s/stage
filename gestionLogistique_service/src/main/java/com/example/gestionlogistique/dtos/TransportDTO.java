package com.example.gestionlogistique.dtos;

import com.example.gestionlogistique.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class TransportDTO {
    private Long id ;
    private String type;
    private Date dateSortie;
    private User transporteur;
    private VehiculeDTO vehiculeDTO;
}
