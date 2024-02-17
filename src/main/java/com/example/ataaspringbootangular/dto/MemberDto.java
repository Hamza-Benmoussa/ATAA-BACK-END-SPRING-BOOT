package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Enum.RoleMembers;
import com.example.ataaspringbootangular.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto implements Serializable {
    @NotNull(message = "Role cannot be null")
    RoleMembers roleMembers;
    @NotNull(message = "Name member cannot be null")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    String nomMembres;
    @NotNull(message = "Date of birth cannot be null")
    Date dateNaissance;
    @NotNull(message = "Genre cannot be null")
    Genre genre;
    @Email(message = "invalid email address")
    String email;
    @Pattern(regexp = "^\\+?[0-9. ()-]{10,}$", message = "invalid mobile number")
    String tele;
    @NotNull(message = "Address cannot be null")
    @Size(min = 5)
    String adress;
    String associationNomAssociation;

    boolean deleted;
}
