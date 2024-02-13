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
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMembers;
    private RoleMembers roleMembers;
    private String nomMembres;
    private Date dateNaissance;
    private Genre genre;
    private String email;
    private int tele;
    private String adress;
    @ManyToOne
    private Associations associations;
}
