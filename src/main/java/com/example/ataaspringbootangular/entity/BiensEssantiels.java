package com.example.ataaspringbootangular.entity;

import com.example.ataaspringbootangular.entity.Enum.CategorieBiens;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
