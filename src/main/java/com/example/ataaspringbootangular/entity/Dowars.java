package com.example.ataaspringbootangular.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dowars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDowars;
    private String nomDowars;
    @ManyToOne
    private Villes villes;
    @OneToMany(mappedBy = "dowars", fetch = FetchType.EAGER)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<Kafila> kafilas;



}
