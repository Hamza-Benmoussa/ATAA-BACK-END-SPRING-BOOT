package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.BiensEssantiels;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link BiensEssantiels}
 */
@Value
public class BiensEssantielsDto implements Serializable {
    Long idBiens;
    String nomBiens;
    Double quantity;
}