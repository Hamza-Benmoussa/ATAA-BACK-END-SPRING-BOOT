package com.example.ataaspringbootangular.service;

import com.example.ataaspringbootangular.dto.DowarDto;
import com.example.ataaspringbootangular.dto.KafilaDto;
import com.example.ataaspringbootangular.exception.except.KafilaFoundException;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;

import java.text.ParseException;
import java.util.List;

public interface IKafilaService {
    KafilaDto ajouterKafila(KafilaDto kafilaDto);


    List<KafilaDto> getKafilas();

    KafilaDto getKafilasById(Long id) throws KafilaFoundException;

    KafilaDto updateKafila(KafilaDto kafilaDto, Long id) throws ParseException;

    void deleteKafila(Long id);
}
