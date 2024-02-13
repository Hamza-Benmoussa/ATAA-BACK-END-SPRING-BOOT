package com.example.ataaspringbootangular.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionVilles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegionVille;
    private String nomVilleRegion;
}
