package com.example.ataaspringbootangular.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.ataaspringbootangular.entity.RegionVilles}
 */
@Value
public class RegionVillesDto implements Serializable {
    Long idRegionVille;
    String nomVilleRegion;
    Long associationsIdAssociation;
    List<Long> dowarIdDowars;
}