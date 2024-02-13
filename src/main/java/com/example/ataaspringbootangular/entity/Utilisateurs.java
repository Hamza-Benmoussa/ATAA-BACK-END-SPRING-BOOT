package com.example.ataaspringbootangular.entity;

import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Enum.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateurs {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String NomComplete;
    private String adress;
    private int tele;
    private String email;
    private Date dateNaissance;
    private RoleUser roleUser;
    private Genre genre;

}
