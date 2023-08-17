package com.example.gestionlogistique.repositories;

import com.example.gestionlogistique.entities.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LigneCommandeRepository extends JpaRepository<LigneCommandeClient,Long> {
    @Modifying
    @Query("DELETE FROM LigneCommandeClient lcc WHERE lcc.commandeClient.id = :idCommande")
    void deleteLignesCommandeByCommandeId(Long idCommande);
}
