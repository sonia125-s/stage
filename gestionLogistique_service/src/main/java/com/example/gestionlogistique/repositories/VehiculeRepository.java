package com.example.gestionlogistique.repositories;

import com.example.gestionlogistique.entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeRepository extends JpaRepository<Vehicule,Long> {
}
