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
@AllArgsConstructor
@NoArgsConstructor
public class Villes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegionVille;
    private String nomVille;
    @OneToMany(mappedBy = "villes",fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<Associations> associations;

    @OneToMany(mappedBy = "villes",fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<Dowars> dowars;
}
