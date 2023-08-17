package com.example.gestionlogistique.entities;

import jakarta.persistence.DiscriminatorValue;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("CM")
public class ClientMorale  extends  Client{
    private String statut;

}
