package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Association;
import com.example.ataaspringbootangular.entity.BiensEssantiel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Association}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociationDto implements Serializable {
    private String nomPresidantEmail;
    @NotNull(message = "NbrSerie cannot be null")
    String nbrSerie;
    @NotNull(message = "Name association cannot be null")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    String nomAssociation;
    List<Long> memberIdMembers;
    List<Long> biensEssentielIdBiens;
    String villeNomVille;
    boolean deleted;
}