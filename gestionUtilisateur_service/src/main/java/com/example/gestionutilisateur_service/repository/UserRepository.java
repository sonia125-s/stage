package com.example.gestionutilisateur_service.repository;

import com.example.gestionutilisateur_service.entities.UserCredential;
import com.example.gestionutilisateur_service.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserCredential,Long> {
    public List<UserCredential> findByRole(Role role);
    public UserCredential findByUsername(String username);
}
