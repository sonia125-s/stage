package com.example.gestionlogistique.repositories;

import com.example.gestionlogistique.entities.StockeProduit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockeProduitRepository extends JpaRepository<StockeProduit,Long> {
}
