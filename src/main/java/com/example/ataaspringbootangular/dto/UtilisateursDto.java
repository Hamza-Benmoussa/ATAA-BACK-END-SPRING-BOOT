package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Enum.RoleUser;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.example.ataaspringbootangular.entity.Utilisateurs}
 */
@Value
public class UtilisateursDto implements Serializable {
    Long idUser;
    String NomComplete;
    String adress;
    int tele;
    String email;
    Date dateNaissance;
    RoleUser roleUser;
    Genre genre;
}