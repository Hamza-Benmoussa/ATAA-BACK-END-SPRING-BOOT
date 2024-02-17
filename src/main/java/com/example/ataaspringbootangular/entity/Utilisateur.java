package com.example.ataaspringbootangular.entity;

import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Enum.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "nom_complete")
    private String NomComplete;

    @Column(name = "password")
    private String password;

    @Column(name = "adress")
    private String address;

    @Column(name = "tele")
    private String tele;

    @Column(name = "email")
    private String email;

    @Column(name = "date_naissance")
    private Date dateNaissance;

    @Column(name = "role_user")
    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;

    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column(name="is_deleted")
    private boolean deleted;
}

