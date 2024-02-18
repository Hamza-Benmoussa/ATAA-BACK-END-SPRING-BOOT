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
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomVille;

    @OneToMany(mappedBy = "ville", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<Association> associations;

    @OneToMany(mappedBy = "ville", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<Dowar> dowars;
    @Column(name="is_deleted")
    private boolean deleted;
}

