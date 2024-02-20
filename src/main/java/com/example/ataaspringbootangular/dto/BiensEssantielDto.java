package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.BiensEssantiel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link BiensEssantiel}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiensEssantielDto implements Serializable {
    Long id;
    @NotNull(message = "Name biens cannot be null")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    String nomBiens;
    @Min(5)
    @Max(5000)
    Double quantity;
    Long associationId;

    boolean deleted;
}