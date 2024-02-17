package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.entity.BienKafila;
import com.example.ataaspringbootangular.repository.IBienKafilaRepository;
import com.example.ataaspringbootangular.service.IBienKafilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BienKafilaServiceImpl implements IBienKafilaService {
    @Autowired
    private IBienKafilaRepository iBienKafilaRepository;

    @Override
    public BienKafila ajouterBienKafila(BienKafila bienKafila) {
        return iBienKafilaRepository.save(bienKafila);
    }
}
