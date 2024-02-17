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
public class Association {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_association")
    private Long idAssociation;

    @ManyToOne
    @JoinColumn(name = "nom_president")
    private Utilisateur nomPresidant;

    @Column(name = "nombre_serie")
    private String nbrSerie;

    @Column(name = "nom_association")
    private String nomAssociation;

    @ManyToOne
    @JoinColumn(name = "ville_id")
    private Ville ville;

    @OneToMany(mappedBy = "association", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<BiensEssantiel> biensEssentiels;

    @OneToMany(mappedBy = "association", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<Member> members;

    @Column(name="is_deleted")
    private boolean deleted;
}