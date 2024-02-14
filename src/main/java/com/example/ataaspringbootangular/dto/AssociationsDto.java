package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Associations;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Associations}
 */
@Value
public class AssociationsDto implements Serializable {
    Long idAssociation;
    Long nomPresidantIdUser;
    String NbrSerie;
    String NomAssociation;
    List<Long> biensEssantielIdBiens;
    List<Long> memberIdMembers;
    List<Long> regionVilleIdRegionVilles;
}