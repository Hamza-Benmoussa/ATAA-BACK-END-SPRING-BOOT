package com.example.ataaspringbootangular.service;

import com.example.ataaspringbootangular.dto.AssociationDto;
import com.example.ataaspringbootangular.dto.BienKafilaDto;
import com.example.ataaspringbootangular.entity.BienKafila;

import java.util.List;

public interface IBienKafilaService {
    BienKafilaDto ajouterBienKafila(BienKafilaDto bienKafilaDto);
}
