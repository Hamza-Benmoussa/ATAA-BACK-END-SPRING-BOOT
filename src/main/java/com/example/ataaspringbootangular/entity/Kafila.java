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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kafila {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idKafila;
    private String nomKfila;
    @ManyToOne
    private Dowars dowars;

    @OneToMany(mappedBy = "kafila", fetch = FetchType.EAGER)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<BienKafila> bienKafilas;
}
