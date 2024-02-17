package com.example.ataaspringbootangular.service;

import com.example.ataaspringbootangular.dto.MemberDto;
import com.example.ataaspringbootangular.dto.UtilisateurDto;

import java.util.List;

public interface IUtilisateurService {
    UtilisateurDto ajouterUtilisateur(UtilisateurDto utilisateurDto);


    List<UtilisateurDto> getUtilisateurs();

    UtilisateurDto getUtilisateursById(Long id);

    UtilisateurDto updateUtilisateur(UtilisateurDto utilisateurDto, Long id);

    void deleteUtilisateur(Long id);

}
