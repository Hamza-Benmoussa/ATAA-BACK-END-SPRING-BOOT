package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Enum.RoleMembers;
import com.example.ataaspringbootangular.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto implements Serializable {
    Long id;
    @NotNull(message = "Role cannot be null")
    RoleMembers roleMembers;


    @NotBlank(message = "Name member cannot be blank")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    String nomMembres;

    @NotNull(message = "Date of birth cannot be null")
    Date dateNaissance;

    @NotNull(message = "Genre cannot be null")
    Genre genre;

    @Email(message = "invalid email address")
    @NotBlank(message = "not blank")
    String email;

    @Pattern(regexp = "^\\+?[0-9. ()-]{10,}$", message = "invalid mobile number")
    @NotBlank(message = "not blank tele ")
    String tele;

    @NotBlank(message = "Address cannot be blank")
    @Size(min = 5)
    String address;

    Long associationId;


    boolean deleted;
}
