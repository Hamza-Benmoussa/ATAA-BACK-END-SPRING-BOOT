package com.example.ataaspringbootangular.service;

import com.example.ataaspringbootangular.dto.UtilisateurDto;
import com.example.ataaspringbootangular.dto.VilleDto;
import com.example.ataaspringbootangular.entity.Dowar;
import com.example.ataaspringbootangular.entity.Ville;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;

import java.util.List;

public interface IVilleService {
    VilleDto ajouterVille(VilleDto villeDto);

    Ville getByNomVille(String nomVille);
    List<VilleDto> getVilles();
    List<Dowar> getDowarsForVille(String nomVille);
    VilleDto getVillesById(Long id) throws VilleFoundException;

    VilleDto updateVille(VilleDto villeDto, Long id);

    void deleteVille(Long id);

}
