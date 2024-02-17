package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Enum.RoleUser;
import com.example.ataaspringbootangular.entity.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Utilisateur}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto implements Serializable {
    @NotNull(message = "Name utilisateur cannot be null")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    String NomComplete;
    @NotNull(message = "Password cannot be null")
    String password;
    @NotNull(message = " Address cannot be null")
    @Size(min = 5)
    String address;
    @Pattern(regexp = "^\\+?[0-9. ()-]{10,}$", message = "invalid mobile number")
    String tele;
    @Email(message = "invalid email address")
    String email;
    @NotNull(message = "dateNaissance not be null")
    Date dateNaissance;
    @NotNull(message = "Role not be null")
    RoleUser roleUser;
    @NotNull(message = "Genre not be null")
    Genre genre;
    boolean deleted;
}