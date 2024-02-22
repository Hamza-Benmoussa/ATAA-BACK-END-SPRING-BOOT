package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.dto.BienKafilaDto;
import com.example.ataaspringbootangular.entity.BienKafila;
import com.example.ataaspringbootangular.repository.IBienKafilaRepository;
import com.example.ataaspringbootangular.service.IBienKafilaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BienKafilaServiceImpl implements IBienKafilaService {
    @Autowired
    private IBienKafilaRepository iBienKafilaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BienKafilaDto ajouterBienKafila(BienKafilaDto bienKafilaDto) {
        BienKafila bienKafila = modelMapper.map(bienKafilaDto , BienKafila.class);
        BienKafila saveBienKafila = iBienKafilaRepository.save(bienKafila);
        return modelMapper.map(saveBienKafila , BienKafilaDto.class);
    }
}
