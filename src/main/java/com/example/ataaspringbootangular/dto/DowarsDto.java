package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Dowars;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Dowars}
 */
@Value
public class DowarsDto implements Serializable {
    Long idDowars;
    String nomDowars;
    Long villesIdRegionVille;
    List<Long> kafilaIdKafilas;
}