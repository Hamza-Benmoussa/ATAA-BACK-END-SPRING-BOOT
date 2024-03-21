package com.example.ataaspringbootangular.service;

import com.example.ataaspringbootangular.dto.BiensEssantielDto;
import com.example.ataaspringbootangular.dto.DowarDto;
import com.example.ataaspringbootangular.exception.except.DowarFoundException;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;

import java.util.List;

public interface IDowarService {
    DowarDto ajouterDowar(DowarDto dowarDto) throws DowarFoundException, VilleFoundException;


    List<DowarDto> getDowars();

    DowarDto getDowarsById(Long id) throws DowarFoundException;

    DowarDto updateDowar(DowarDto dowarDto, Long id) throws VilleFoundException;

    void deleteDowar(Long id);
    int calculateTotalArrivedKafilasForDowar(Long dowarId) throws DowarFoundException;
}
