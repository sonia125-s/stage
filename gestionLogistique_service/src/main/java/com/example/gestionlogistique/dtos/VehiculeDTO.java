package com.example.gestionlogistique.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class VehiculeDTO {
    private Long id ;
    private String marque;
    private String model;
    private long capacite;
}
