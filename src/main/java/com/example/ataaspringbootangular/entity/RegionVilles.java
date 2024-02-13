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
public class RegionVilles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegionVille;
    private String nomVilleRegion;
    @ManyToOne

    private Associations associations;

    @OneToMany(mappedBy = "regionVilles" ,fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<Dowars> dowars;
}
