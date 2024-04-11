package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Dowar;
import com.example.ataaspringbootangular.entity.Kafila;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Dowar}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DowarDto implements Serializable {

    Long id;
    @NotBlank (message = "Name dowar cannot be blank")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    String nomDowars;
    @Min(1)
    @Max(1000)
    int nmbrResidant;
    Long villeId;
    List<Kafila> kafilas;
     int arrivedKafilaCount;


    boolean deleted;

}