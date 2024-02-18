package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.dto.BiensEssantielDto;
import com.example.ataaspringbootangular.entity.BiensEssantiel;
import com.example.ataaspringbootangular.exception.except.BiensEssentielFoundException;
import com.example.ataaspringbootangular.exception.except.DowarFoundException;
import com.example.ataaspringbootangular.repository.IBiensEssantielsRepository;
import com.example.ataaspringbootangular.service.IBienKafilaService;
import com.example.ataaspringbootangular.service.IBiensEssantielService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BiensEssantielImpl implements IBiensEssantielService {
    @Autowired
    private IBiensEssantielsRepository iBiensEssantielsRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public BiensEssantielDto ajouterBiensEssantiel(BiensEssantielDto biensEssantielDto) {
        BiensEssantiel biensEssantiel = modelMapper.map(biensEssantielDto , BiensEssantiel.class);
        BiensEssantiel saveBiensEssantiel = iBiensEssantielsRepository.save(biensEssantiel);
        return modelMapper.map(saveBiensEssantiel, BiensEssantielDto.class);
    }

    @Override
    public List<BiensEssantielDto> getBiensEssantiels() {
        List<BiensEssantiel> biensEssantiels = iBiensEssantielsRepository.findByDeletedFalse();

        return biensEssantiels.stream()
                .map(biensEssantiel -> modelMapper.map(biensEssantiel , BiensEssantielDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BiensEssantielDto getBiensEssantielsById(Long id) throws BiensEssentielFoundException {
        return iBiensEssantielsRepository.findByIdAndDeletedFalse(id)
                .map(biensEssantiel -> modelMapper.map(biensEssantiel , BiensEssantielDto.class))
                .orElseThrow(() -> new BiensEssentielFoundException("BiensEssentiel Not found" + id));

    }

    @Override
    public BiensEssantielDto updateAssociation(BiensEssantielDto biensEssantielDto, Long id) {
        BiensEssantiel existingBiensEssentienl = iBiensEssantielsRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (existingBiensEssentienl != null) {
            existingBiensEssentienl.setNomBiens(biensEssantielDto.getNomBiens());
            existingBiensEssentienl.setQuantity(biensEssantielDto.getQuantity());
            BiensEssantiel updateBiensEssantiel = iBiensEssantielsRepository.save(existingBiensEssentienl);
            updateBiensEssantiel.setId(id);
            return modelMapper.map(updateBiensEssantiel, BiensEssantielDto.class);
        }
        return null;
        }

    @Override
    public void deleteBiensEssantiel(Long id) {
        BiensEssantiel biensEssantiel = iBiensEssantielsRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (biensEssantiel !=null){
            biensEssantiel.setDeleted(true);
            iBiensEssantielsRepository.save(biensEssantiel);
        }
    }
}
