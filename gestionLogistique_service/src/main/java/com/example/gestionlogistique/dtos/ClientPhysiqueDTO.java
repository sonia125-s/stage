package com.example.gestionlogistique.dtos;

import lombok.Data;

@Data
public class ClientPhysiqueDTO extends  ClientDTO {
    private Long id ;
    private  String email;
    private String adresse;
    private String nom;
    private int age;
}
