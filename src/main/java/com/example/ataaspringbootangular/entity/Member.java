package com.example.ataaspringbootangular.entity;

import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Enum.RoleMembers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_members")
    private Long idMembers;

    @Column(name = "role_members")
    @Enumerated(EnumType.STRING)
    private RoleMembers roleMembers;

    @Column(name = "nom_membres")
    private String nomMembres;

    @Column(name = "date_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "email")
    private String email;

    @Column(name = "tele")
    private int tele;

    @Column(name = "address")
    private String adress;

    @ManyToOne
    @JoinColumn(name = "associations_id")
    private Association association;
    @Column(name="is_deleted")
    private boolean deleted;
}

