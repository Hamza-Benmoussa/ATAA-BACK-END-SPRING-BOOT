package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Associations;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Associations}
 */
@Value
public class AssociationsDto implements Serializable {
    Long idAssociation;
    Long nomPresidantIdUser;
    String NbrSerie;
    String NomAssociation;
    Long villesIdRegionVille;
}