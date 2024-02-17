package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.BienKafila;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.ataaspringbootangular.entity.Kafila}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafilaDto implements Serializable {
    Long idKafila;
    String nomKfila;
    String dowarNomDowars;
    List<BienKafila> bienKafilas;

}