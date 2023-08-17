package com.example.gestionlogistique.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name= "TYPE",length=4, discriminatorType = DiscriminatorType.STRING)

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id ;
    private  String email;
    private String adresse;
    @OneToMany(mappedBy = "client")
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private List<CommandeClient> commandesClient;
}
