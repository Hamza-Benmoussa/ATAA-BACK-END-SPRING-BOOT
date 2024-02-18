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
public class BiensEssantiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomBiens;

    private Double quantity;

    @ManyToOne
    private Association association;

    @OneToMany(mappedBy = "biensEssentiels", fetch = FetchType.EAGER)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<BienKafila> bienKafilas;
    @Column(name="is_deleted")
    private boolean deleted;
}
