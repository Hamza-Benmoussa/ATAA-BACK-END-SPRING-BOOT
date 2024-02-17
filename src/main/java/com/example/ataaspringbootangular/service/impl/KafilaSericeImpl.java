package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.dto.KafilaDto;
import com.example.ataaspringbootangular.entity.Kafila;
import com.example.ataaspringbootangular.repository.IKafilaRepository;
import com.example.ataaspringbootangular.service.IBienKafilaService;
import com.example.ataaspringbootangular.service.IKafilaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class KafilaSericeImpl implements IKafilaService {
    @Autowired
    private IKafilaRepository iKafilaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public KafilaDto ajouterKafila(KafilaDto kafilaDto) {
        Kafila kafila = modelMapper.map(kafilaDto , Kafila.class);
        Kafila saveKafila = iKafilaRepository.save(kafila);
        return modelMapper.map(saveKafila ,KafilaDto.class);
    }

    @Override
    public List<KafilaDto> getKafilas() {
        List<Kafila> kafilas = iKafilaRepository.findByDeletedFalse();
        return kafilas.stream()
                .map(kafila -> modelMapper.map(kafila , KafilaDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public KafilaDto getKafilasById(Long id) {
        return iKafilaRepository.findByIdAndDeletedFalse(id)
                .map(kafila -> modelMapper.map(kafila , KafilaDto.class))
                .orElse(null);
    }

    @Override
    public KafilaDto updateKafila(KafilaDto kafilaDto, Long id) {
        Kafila existingKafila = iKafilaRepository.findByIdAndDeletedFalse(id).orElse(null);
        existingKafila.setNomKfila(kafilaDto.getNomKfila());
        Kafila updateKafila = iKafilaRepository.save(existingKafila);
        updateKafila.setIdKafila(id);
        return modelMapper.map(updateKafila , KafilaDto.class);
    }

    @Override
    public void deleteKafila(Long id) {
        Kafila kafila = iKafilaRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (kafila != null){
            kafila.setDeleted(true);
            iKafilaRepository.save(kafila);
        }
    }
}
