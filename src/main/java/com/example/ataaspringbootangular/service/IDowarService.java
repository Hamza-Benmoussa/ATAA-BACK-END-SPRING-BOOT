package com.example.ataaspringbootangular.service;

import com.example.ataaspringbootangular.dto.BiensEssantielDto;
import com.example.ataaspringbootangular.dto.DowarDto;

import java.util.List;

public interface IDowarService {
    DowarDto ajouterDowar(DowarDto dowarDto);


    List<DowarDto> getDowars();

    DowarDto getDowarsById(Long id);

    DowarDto updateDowar(DowarDto dowarDto, Long id);

    void deleteDowar(Long id);
}
