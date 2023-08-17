package com.example.gestionlogistique.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class DetailleCommandeDTO {
    private Long id;
    private Date date_creation;
    private Date date_envoi;
    private String lieu;
    private Long prixTotal;
    private List<LigneCommandeDTO> ligneCommandeDTOList;

}
