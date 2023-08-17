package com.example.gestionlogistique.services;

import com.example.gestionlogistique.dtos.ProduitDTO;

import java.util.List;

public interface ProduitService {
    List<ProduitDTO> getAllProduits();

    ProduitDTO saveProduit(ProduitDTO produitDTO);

    ProduitDTO updateProduit(ProduitDTO produitDTO);

    void deleteProduit(Long produitId);

    ProduitDTO getProduitById(Long id);
}
