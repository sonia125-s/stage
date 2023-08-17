package com.example.gestionutilisateur_service.dto;

import com.example.gestionutilisateur_service.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id ;
    private  String nom ;
    private  String email ;
    private Role role;
}
