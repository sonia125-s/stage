package com.example.gestionlogistique.entities;

import com.example.gestionlogistique.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateSortie;
    //private String lieu;
    private Long transporteurId;
    @Transient
    private User transporteur;
    @OneToMany(mappedBy = "transport", fetch=FetchType.LAZY)
    private List<CommandeClient> commandeClients;
    @ManyToOne
    private Vehicule vehicule;

}
