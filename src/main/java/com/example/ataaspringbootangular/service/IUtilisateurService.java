package com.example.ataaspringbootangular.service;

import com.example.ataaspringbootangular.dto.MemberDto;
import com.example.ataaspringbootangular.dto.UtilisateurDto;
import com.example.ataaspringbootangular.entity.Utilisateur;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;

import java.text.ParseException;
import java.util.List;

public interface IUtilisateurService {
    UtilisateurDto ajouterUtilisateur(UtilisateurDto utilisateurDto);

    List<UtilisateurDto> getUtilisateurs();
    Utilisateur loadUserByEmail(String email);

    UtilisateurDto getUtilisateursById(Long id) throws UtilisateurFoundException;

    UtilisateurDto updateUtilisateur(UtilisateurDto utilisateurDto, Long id) throws ParseException;

    void deleteUtilisateur(Long id);

}
