package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Association;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Association}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociationDto implements Serializable {
    Long idAssociation;
    Long nomPresidantIdUser;
    String NbrSerie;
    String NomAssociation;
    Long villesIdRegionVille;
}