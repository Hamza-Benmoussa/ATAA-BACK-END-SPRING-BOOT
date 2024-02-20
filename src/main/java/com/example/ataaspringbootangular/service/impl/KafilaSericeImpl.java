package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.dto.AssociationDto;
import com.example.ataaspringbootangular.dto.BiensEssantielDto;
import com.example.ataaspringbootangular.dto.DowarDto;
import com.example.ataaspringbootangular.dto.KafilaDto;
import com.example.ataaspringbootangular.entity.*;
import com.example.ataaspringbootangular.exception.except.*;
import com.example.ataaspringbootangular.repository.IBiensEssantielsRepository;
import com.example.ataaspringbootangular.repository.IDowarsRepository;
import com.example.ataaspringbootangular.repository.IKafilaRepository;
import com.example.ataaspringbootangular.service.*;
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
    @Autowired
    private IDowarService iDowarService;
    @Autowired
    private IAssociationService iAssociationService;
    @Autowired
    private IBienKafilaService iBienKafilaService;
    @Autowired
    private IBiensEssantielsRepository iBiensEssantielsRepository;
    @Autowired
    private IBiensEssantielService iBiensEssantielService;
    @Override
    public KafilaDto ajouterKafila(KafilaDto kafilaDto) throws DowarFoundException, AssociationFoundException, BiensEssentielFoundException {
        Kafila kafila = modelMapper.map(kafilaDto, Kafila.class);
        DowarDto dowarDto = iDowarService.getDowarsById(kafilaDto.getDowarId());
        Dowar dowar = modelMapper.map(dowarDto, Dowar.class);
        AssociationDto associationDto = iAssociationService.getAssociationsById(kafilaDto.getAssociationId());
        Association association = modelMapper.map(associationDto , Association.class);
        kafila.setAssociation(association);
        kafila.setDowar(dowar);
        Kafila saveKafila = iKafilaRepository.save(kafila);
        for (BienKafila bienKafila : kafilaDto.getBienKafilas()) {
            BiensEssantiel biensEssantiel = modelMapper.map(iBiensEssantielService.getBiensEssantielsById(bienKafila.getBiensEssentiels().getId()), BiensEssantiel.class);

            BiensEssantiel existingBiensEssentiel = getBiensEssentielById(biensEssantiel.getId());

            if (existingBiensEssentiel != null && existingBiensEssentiel.getQuantity() >= biensEssantiel.getQuantity()) {
                existingBiensEssentiel.setQuantity(existingBiensEssentiel.getQuantity() - biensEssantiel.getQuantity());

               iBiensEssantielService.updateBiensEssentiel(modelMapper.map(biensEssantiel , BiensEssantielDto.class), biensEssantiel.getId());
            }
        }

        Kafila finalSavedKafila = iKafilaRepository.save(saveKafila);

        return modelMapper.map(finalSavedKafila, KafilaDto.class);
    }

    private BiensEssantiel getBiensEssentielById(Long id) {
        return iBiensEssantielsRepository.findByIdAndDeletedFalse(id).orElse(null);
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
            Dowar dowar = iDowarsRepository.findById(kafilaDto.getDowarId()).orElse(null);
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
