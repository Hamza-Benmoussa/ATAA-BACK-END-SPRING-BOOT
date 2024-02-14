package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Residants;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Residants}
 */
@Value
public class ResidantsDto implements Serializable {
    Long idResidant;
    String nomResidant;
    String adress;
    Genre genre;
    int tele;
    Date dateNaissance;
    Long dowarsIdDowars;
}