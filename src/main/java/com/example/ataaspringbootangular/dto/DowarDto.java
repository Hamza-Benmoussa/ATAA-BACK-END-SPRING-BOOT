package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Dowar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Dowar}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DowarDto implements Serializable {
    Long idDowars;
    String nomDowars;
    int nmbrResidant;
    Long villesIdRegionVille;
    List<Long> kafilaIdKafilas;
}