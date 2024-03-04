package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.BienKafila;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * DTO for {@link BienKafila}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BienKafilaDto implements Serializable {
    int quantityBienKafila;
    Long biensEssentielsId;
    private Long kafilaId;
    boolean deleted;
}