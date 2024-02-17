package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Ville;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Ville}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VilleDto implements Serializable {
    Long idRegionVille;
    String nomVille;
}