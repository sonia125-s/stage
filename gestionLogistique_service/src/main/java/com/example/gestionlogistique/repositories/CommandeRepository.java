package com.example.gestionlogistique.repositories;

import com.example.gestionlogistique.entities.CommandeClient;
import com.example.gestionlogistique.enums.EnumEtat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<CommandeClient,Long> {
    List<CommandeClient> findByClient_Id(Long clientId);
    List<CommandeClient> findByEtat(EnumEtat etat);



}
