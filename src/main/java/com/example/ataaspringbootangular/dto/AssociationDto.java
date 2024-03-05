package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Association;
import com.example.ataaspringbootangular.entity.BiensEssantiel;
import com.example.ataaspringbootangular.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Association}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociationDto implements Serializable {
    Long id;
    private Long nomPresidantId;
    @NotBlank(message = "NbrSerie cannot be blank")
    String nbrSerie;
    @NotBlank(message = "Name association cannot be blank")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    String nomAssociation;
    List<Member> members;
    List<BiensEssantiel> biensEssantiels;
    Long villeId;
    boolean deleted;
}