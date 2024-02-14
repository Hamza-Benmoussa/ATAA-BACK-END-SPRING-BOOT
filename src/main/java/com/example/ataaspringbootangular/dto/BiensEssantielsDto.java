package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.BiensEssantiels;
import com.example.ataaspringbootangular.entity.Enum.CategorieBiens;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link BiensEssantiels}
 */
@Value
public class BiensEssantielsDto implements Serializable {
    Long idBiens;
    CategorieBiens nomBiens;
    Double quantity;
    Long associationsIdAssociation;
    List<Long> dowarIdDowars;
}