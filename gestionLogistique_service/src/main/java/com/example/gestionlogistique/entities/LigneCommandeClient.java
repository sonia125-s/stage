package com.example.gestionlogistique.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommandeClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private int quantite;
    @ManyToOne
    private CommandeClient commandeClient;
    @ManyToOne
    private Produit produit;

}
