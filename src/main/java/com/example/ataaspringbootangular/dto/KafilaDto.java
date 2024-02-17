package com.example.ataaspringbootangular.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.ataaspringbootangular.entity.Kafila}
 */
@Value
public class KafilaDto implements Serializable {
    Long idKafila;
    String nomKfila;
    Long dowarsIdDowars;
    List<BienKafilaDto> bienKafilas;

    /**
     * DTO for {@link com.example.ataaspringbootangular.entity.BienKafila}
     */
    @Value
    public static class BienKafilaDto implements Serializable {
        Long idBienKafila;
        int quantityBienKafila;
    }
}