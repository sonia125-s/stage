package com.example.gestionlogistique.repositories;

import com.example.gestionlogistique.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
}
