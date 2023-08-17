package com.example.gestionlogistique.dtos;

import com.example.gestionlogistique.entities.Client;
import com.example.gestionlogistique.enums.EnumEtat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class CommandeClientDTO {
    private Long id;
    private Date date_creation;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date_envoi;
    private Long prixTotal;
    private EnumEtat etat;
    private String lieu;
    //private ClientDTO clientDTO;
}
