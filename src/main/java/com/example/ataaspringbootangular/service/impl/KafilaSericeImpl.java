package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.dto.KafilaDto;
import com.example.ataaspringbootangular.entity.Dowar;
import com.example.ataaspringbootangular.entity.Kafila;
import com.example.ataaspringbootangular.exception.except.KafilaFoundException;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;
import com.example.ataaspringbootangular.repository.IDowarsRepository;
import com.example.ataaspringbootangular.repository.IKafilaRepository;
import com.example.ataaspringbootangular.service.IBienKafilaService;
import com.example.ataaspringbootangular.service.IKafilaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class KafilaSericeImpl implements IKafilaService {
    @Autowired
    private IKafilaRepository iKafilaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IDowarsRepository iDowarsRepository;
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
    public KafilaDto getKafilasById(Long id) throws KafilaFoundException {
        return iKafilaRepository.findByIdAndDeletedFalse(id)
                .map(kafila -> modelMapper.map(kafila , KafilaDto.class))
                .orElseThrow(() -> new KafilaFoundException("Kafila Not found" + id));
    }

    @Override
    public KafilaDto updateKafila(KafilaDto kafilaDto, Long id){
        Kafila existingKafila = iKafilaRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (existingKafila!= null){
            existingKafila.setNomKfila(kafilaDto.getNomKfila());
            Dowar dowar = iDowarsRepository.findByNomDowars(kafilaDto.getDowarNomDowars());
            existingKafila.setDowar(dowar);
            existingKafila.setDateArrivee(kafilaDto.getDateArrivee());
            existingKafila.setDateDepart(kafilaDto.getDateDepart());
            Kafila updateKafila = iKafilaRepository.save(existingKafila);
            updateKafila.setId(id);
            return modelMapper.map(updateKafila , KafilaDto.class);
        }
        return null;
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
