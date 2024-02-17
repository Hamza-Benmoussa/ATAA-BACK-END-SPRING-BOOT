package com.example.ataaspringbootangular.service;

import com.example.ataaspringbootangular.dto.UtilisateurDto;
import com.example.ataaspringbootangular.dto.VilleDto;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;

import java.util.List;

public interface IVilleService {
    VilleDto ajouterVille(VilleDto villeDto);


    List<VilleDto> getVilles();

    VilleDto getVillesById(Long id) throws VilleFoundException;

    VilleDto updateVille(VilleDto villeDto, Long id);

    void deleteVille(Long id);

}
