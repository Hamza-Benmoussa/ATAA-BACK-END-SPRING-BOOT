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
    private Long id;

    private int quantityBienKafila;

    @ManyToOne
    private BiensEssantiel biensEssentiels;

    @ManyToOne
    private Kafila kafila;
    @Column(name="is_deleted")
    private boolean deleted;
}

