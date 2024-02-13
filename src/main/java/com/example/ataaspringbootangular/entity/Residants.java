package com.example.ataaspringbootangular.entity;

import com.example.ataaspringbootangular.entity.Enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Residants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResidant;
    private String nomResidant;
    private String adress;
    private Genre genre;
    private int tele;
    private Date dateNaissance;
}
