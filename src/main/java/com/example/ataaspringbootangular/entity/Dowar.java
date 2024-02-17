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
public class Dowar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dowars_id")
    private Long idDowars;

    @Column(name = "nom_dowars")
    private String nomDowars;
    @Column(name = "nombre_residant")
    private int nmbrResidant;

    @ManyToOne
    @JoinColumn(name = "villes_id")
    private Ville ville;

    @OneToMany(mappedBy = "dowar", fetch = FetchType.EAGER)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<Kafila> kafilas;
    @Column(name="is_deleted")
    private boolean deleted;


}

