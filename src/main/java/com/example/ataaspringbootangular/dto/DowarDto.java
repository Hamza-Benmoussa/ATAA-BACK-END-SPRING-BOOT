package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Dowar;
import com.example.ataaspringbootangular.entity.Kafila;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Dowar}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DowarDto implements Serializable {
    @NotNull(message = "Name dowar cannot be null")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    String nomDowars;
    @Min(1)
    @Max(1000)
    int nmbrResidant;
    boolean deleted;

}