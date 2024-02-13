package com.example.ataaspringbootangular.entity;

import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @ManyToOne
    private Dowars dowars;
}
