package com.example.ataaspringbootangular.service;

import com.example.ataaspringbootangular.dto.AssociationDto;
import com.example.ataaspringbootangular.dto.BiensEssantielDto;

import java.util.List;

public interface IBiensEssantielService {
    BiensEssantielDto ajouterBiensEssantiel(BiensEssantielDto biensEssantielDto);


    List<BiensEssantielDto> getBiensEssantiels();

    BiensEssantielDto getBiensEssantielsById(Long id);

    BiensEssantielDto updateAssociation(BiensEssantielDto biensEssantielDto, Long id);

    void deleteBiensEssantiel(Long id);
}
