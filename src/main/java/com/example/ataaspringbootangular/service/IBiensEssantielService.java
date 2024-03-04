package com.example.ataaspringbootangular.service;

import com.example.ataaspringbootangular.dto.AssociationDto;
import com.example.ataaspringbootangular.dto.BiensEssantielDto;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;
import com.example.ataaspringbootangular.exception.except.BiensEssentielFoundException;

import java.util.List;

public interface IBiensEssantielService {
    BiensEssantielDto ajouterBiensEssantiel(BiensEssantielDto biensEssantielDto) throws AssociationFoundException;


    List<BiensEssantielDto> getBiensEssantiels();

    BiensEssantielDto getBiensEssantielsById(Long id) throws BiensEssentielFoundException;

    BiensEssantielDto updateBiensEssentiel(BiensEssantielDto biensEssantielDto, Long id);

    void deleteBiensEssantiel(Long id);
    List<BiensEssantielDto> getAllBiensEssentilesByPresidentAssociationId(Long presidentAssociationId);
}
