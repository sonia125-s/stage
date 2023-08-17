package com.example.gestionlogistique.dtos;

import lombok.Data;

@Data
public class LigneCommandeDTO {
    private Long id ;
    private int quantite;
    private ProduitDTO produitDTO;
}
