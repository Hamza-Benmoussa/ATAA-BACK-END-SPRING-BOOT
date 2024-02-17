package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Villes;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Villes}
 */
@Value
public class VillesDto implements Serializable {
    Long idRegionVille;
    String nomVille;
}