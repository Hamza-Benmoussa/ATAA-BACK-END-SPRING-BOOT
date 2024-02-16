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
    private Long idBienKafila;
    private int quantityBienKafila;
    @ManyToOne
    private BiensEssantiels biensEssentiels;
    @ManyToOne
    private Kafila kafila;
}
