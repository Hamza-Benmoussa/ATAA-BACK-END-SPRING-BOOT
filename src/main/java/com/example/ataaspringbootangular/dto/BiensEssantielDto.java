package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.BiensEssantiel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link BiensEssantiel}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiensEssantielDto implements Serializable {
    Long idBiens;
    String nomBiens;
    Double quantity;
}