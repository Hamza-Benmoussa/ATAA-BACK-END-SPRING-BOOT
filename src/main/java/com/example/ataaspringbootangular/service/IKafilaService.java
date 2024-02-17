package com.example.ataaspringbootangular.service;

import com.example.ataaspringbootangular.dto.DowarDto;
import com.example.ataaspringbootangular.dto.KafilaDto;

import java.util.List;

public interface IKafilaService {
    KafilaDto ajouterKafila(KafilaDto kafilaDto);


    List<KafilaDto> getKafilas();

    KafilaDto getKafilasById(Long id);

    KafilaDto updateKafila(KafilaDto kafilaDto, Long id);

    void deleteKafila(Long id);
}
