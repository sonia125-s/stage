package com.example.gestionlogistique.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Long prix;
    private int quantiteStocke;
    @OneToMany(mappedBy = "produit")
    private List<LigneCommandeClient> lignesCommandeClient;



}
