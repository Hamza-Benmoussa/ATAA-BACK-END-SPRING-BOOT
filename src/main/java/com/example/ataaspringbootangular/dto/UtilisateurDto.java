package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Enum.RoleUser;
import com.example.ataaspringbootangular.entity.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Utilisateur}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto implements Serializable {
    Long idUser;
    String NomComplete;
    String adress;
    int tele;
    String email;
    Date dateNaissance;
    RoleUser roleUser;
    Genre genre;
}