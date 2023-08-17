package com.example.gestionlogistique.repositories;

import com.example.gestionlogistique.entities.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<Transport,Long> {
}
