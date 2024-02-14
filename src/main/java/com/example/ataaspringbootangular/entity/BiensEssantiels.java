package com.example.ataaspringbootangular.entity;

import com.example.ataaspringbootangular.entity.Enum.CategorieBiens;
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
public class BiensEssantiels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBiens;
    private CategorieBiens nomBiens;
    private Double quantity;
    @ManyToOne
    private Associations associations;
    @ManyToMany(mappedBy = "biensEssentiels", fetch = FetchType.EAGER)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<Dowars> dowars;
}
