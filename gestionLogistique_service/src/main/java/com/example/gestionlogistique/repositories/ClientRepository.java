package com.example.gestionlogistique.repositories;

import com.example.gestionlogistique.entities.Client;
import com.example.gestionlogistique.entities.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    //List<CommandeClient> findCommandesClientById(Long idClinet);
}
