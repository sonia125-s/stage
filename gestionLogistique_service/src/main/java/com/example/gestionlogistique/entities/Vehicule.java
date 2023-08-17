package com.example.gestionlogistique.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String marque;
    private String model;
    private long capacite;
    @OneToMany(mappedBy = "vehicule", fetch=FetchType.LAZY)
    private List<Transport> transportList;
}
