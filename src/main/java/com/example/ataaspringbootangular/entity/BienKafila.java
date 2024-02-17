package com.example.ataaspringbootangular.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BienKafila {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bien_kafila")
    private Long idBienKafila;

    @Column(name = "quantity_bien_kafila")
    private int quantityBienKafila;

    @ManyToOne
    @JoinColumn(name = "biens_essentiels_id")
    private BiensEssantiel biensEssentiels;

    @ManyToOne
    @JoinColumn(name = "kafila_id")
    private Kafila kafila;
    @Column(name="is_deleted")
    private boolean deleted;
}

