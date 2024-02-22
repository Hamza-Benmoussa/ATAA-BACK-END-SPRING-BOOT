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
    Long id;
    int quantityBienKafila;
    Long biensEssentielsId;
    Long kafilaId;
    boolean deleted;
}