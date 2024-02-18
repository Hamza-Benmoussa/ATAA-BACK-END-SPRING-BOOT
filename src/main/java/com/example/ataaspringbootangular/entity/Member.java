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
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleMembers roleMembers;

    private String nomMembres;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String email;

    private String tele;

    private String address;

    @ManyToOne
    private Association association;
    @Column(name="is_deleted")
    private boolean deleted;
}

