package com.example.gestionlogistique.repositories;

import com.example.gestionlogistique.entities.Client;
import com.example.gestionlogistique.entities.ClientPhysique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientPhysiqueRepository extends JpaRepository<ClientPhysique,Long> {
    @Query("select cp from ClientPhysique cp where cp.nom like :kw AND cp.nom is not null")
    List<ClientPhysique> SearchClient(@Param("kw") String keyword);

}
