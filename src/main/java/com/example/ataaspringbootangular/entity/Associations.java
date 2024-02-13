package com.example.ataaspringbootangular.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Associations {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idAssociation;
    @ManyToOne
    private Utilisateurs nomPresidant;
    private String NbrSerie;
    private String NomAssociation;
}
