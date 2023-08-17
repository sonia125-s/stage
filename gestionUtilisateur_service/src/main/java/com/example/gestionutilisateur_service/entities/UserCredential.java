package com.example.gestionutilisateur_service.entities;

import com.example.gestionutilisateur_service.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private  String nom ;

    private  String email ;
    private String username;
    private  String password ;

    @Enumerated(EnumType.STRING)
    private Role role;
}
