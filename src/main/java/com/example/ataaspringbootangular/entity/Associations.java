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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Associations {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idAssociation;
    @ManyToOne
    private Utilisateurs nomPresidant;
    private String NbrSerie;
    private String NomAssociation;

    @OneToMany(mappedBy = "associations" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<BiensEssantiels> biensEssantiels;

    @OneToMany(mappedBy = "associations" ,fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<Members> members;

    @OneToMany(mappedBy = "associations" ,fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<RegionVilles> regionVilles;


}
