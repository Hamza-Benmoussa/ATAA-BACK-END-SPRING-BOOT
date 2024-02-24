package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.BiensEssantiel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * DTO for {@link BiensEssantiel}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiensEssantielDto implements Serializable {
    Long id;
    @NotBlank(message = "Name biens cannot be blank")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    String nomBiens;
    @NotNull(message = "quantity not be null")
    int quantity;
    Long associationId;

    boolean deleted;
}