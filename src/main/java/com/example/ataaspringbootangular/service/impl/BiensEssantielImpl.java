package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.dto.AssociationDto;
import com.example.ataaspringbootangular.dto.BiensEssantielDto;
import com.example.ataaspringbootangular.dto.KafilaDto;
import com.example.ataaspringbootangular.dto.MemberDto;
import com.example.ataaspringbootangular.entity.Association;
import com.example.ataaspringbootangular.entity.BiensEssantiel;
import com.example.ataaspringbootangular.entity.Kafila;
import com.example.ataaspringbootangular.entity.Member;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;
import com.example.ataaspringbootangular.exception.except.BiensEssentielFoundException;
import com.example.ataaspringbootangular.exception.except.DowarFoundException;
import com.example.ataaspringbootangular.repository.IBiensEssantielsRepository;
import com.example.ataaspringbootangular.service.IAssociationService;
import com.example.ataaspringbootangular.service.IBienKafilaService;
import com.example.ataaspringbootangular.service.IBiensEssantielService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private IAssociationService iAssociationService;
    @Override
    public BiensEssantielDto ajouterBiensEssantiel(BiensEssantielDto biensEssantielDto) throws AssociationFoundException {
        String createdByEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        Association association = iAssociationService.getAssociationByPresidentEmail(createdByEmail);

        if (association == null) {
            throw new AssociationFoundException("Association not found for president email: " + createdByEmail);
        }

        biensEssantielDto.setCreatedBy(createdByEmail);
        biensEssantielDto.setAssociationId(association.getId());

        BiensEssantiel biensEssantiel = modelMapper.map(biensEssantielDto, BiensEssantiel.class);
        biensEssantiel.setAssociation(association);

        BiensEssantiel saveBiensEssantiel = iBiensEssantielsRepository.save(biensEssantiel);

        return modelMapper.map(saveBiensEssantiel, BiensEssantielDto.class);
    }

    @Override
    public BiensEssantielDto getBiensEssantielsById(Long id) throws BiensEssentielFoundException {
        return iBiensEssantielsRepository.findByIdAndDeletedFalse(id)
                .map(biensEssantiel -> modelMapper.map(biensEssantiel , BiensEssantielDto.class))
                .orElseThrow(() -> new BiensEssentielFoundException("BiensEssentiel Not found with id = " + id));

    }

    @Override
    public List<BiensEssantielDto> getBiensEssentielsCreatedByUser(String createdByEmail) {
        List<BiensEssantiel> members = iBiensEssantielsRepository.findByCreatedByAndDeletedFalse(createdByEmail);
        return members.stream()
                .map(biensEssantiel -> modelMapper.map(biensEssantiel, BiensEssantielDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BiensEssantielDto updateBiensEssentiel(BiensEssantielDto biensEssantielDto, Long id) {
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
