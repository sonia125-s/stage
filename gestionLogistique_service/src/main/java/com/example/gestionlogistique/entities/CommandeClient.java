package com.example.gestionlogistique.entities;

import com.example.gestionlogistique.enums.EnumEtat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeClient {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date_creation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date_envoi;
    private Long prixTotal;
    @Enumerated(EnumType.STRING)
    private EnumEtat etat;
    private String lieu;
    @ManyToOne
    private  Client client;
    @OneToMany(mappedBy = "commandeClient", fetch=FetchType.LAZY)
    private List<LigneCommandeClient> lignesCommandeClient;
    @ManyToOne
    private Transport transport;

}
