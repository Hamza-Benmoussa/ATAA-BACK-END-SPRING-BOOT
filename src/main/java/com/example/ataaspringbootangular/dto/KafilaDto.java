package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.BienKafila;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafilaDto implements Serializable {
    @NotNull(message = "Name kafila cannot be null")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    String nomKfila;
    String dowarNomDowars;
    List<Long> bienKafilaIdBienKafilas;
    boolean deleted;
}
