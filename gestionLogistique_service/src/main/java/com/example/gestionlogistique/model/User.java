package com.example.gestionlogistique.model;

import com.example.gestionlogistique.enums.Role;
import lombok.Data;

@Data
public class User {
    private  Long id;
    private String nom ;
    private String email;

    private Role role;
}
