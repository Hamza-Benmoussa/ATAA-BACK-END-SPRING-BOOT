package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Association;
import com.example.ataaspringbootangular.entity.Dowar;
import com.example.ataaspringbootangular.entity.Ville;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Ville}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VilleDto implements Serializable {
    Long id;
    @NotBlank(message = "Name ville cannot be blank")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    String nomVille;
    List<Dowar> dowars;
    List<Association> associations;
    boolean deleted;
}