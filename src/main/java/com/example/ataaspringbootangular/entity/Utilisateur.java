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
    private Long id;

    private String nomComplete;

    private String password;

    private String address;

    private String tele;

    private String email;

    private Date dateNaissance;

    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column(name="is_deleted")
    private boolean deleted;
}

