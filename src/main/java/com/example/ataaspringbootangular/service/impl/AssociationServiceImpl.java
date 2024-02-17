package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.dto.AssociationDto;
import com.example.ataaspringbootangular.entity.Association;
import com.example.ataaspringbootangular.repository.IAssociationsRepository;
import com.example.ataaspringbootangular.service.IAssociationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssociationServiceImpl implements IAssociationService {
    @Autowired
    private IAssociationsRepository iAssociationsRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AssociationDto ajouterAssociation(AssociationDto associationDto) {
        Association association = modelMapper.map(associationDto , Association.class);
        Association saveAssociation = iAssociationsRepository.save(association);
        return modelMapper.map(saveAssociation, AssociationDto.class);
    }

    @Override
    public List<AssociationDto> getAssociations() {
        List<Association> associations = iAssociationsRepository.findByDeletedFalse();
        return associations.stream()
                .map(association -> modelMapper.map(association , AssociationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AssociationDto getAssociationsById(Long id) {
        return iAssociationsRepository.findByIdAndDeletedFalse(id)
                .map(association -> modelMapper.map(association , AssociationDto.class))
                .orElse(null);
    }

    @Override
    public AssociationDto updateAssociation(AssociationDto associationDto, Long id) {
        Association existingAssociation = iAssociationsRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (existingAssociation != null) {
            existingAssociation.setNomAssociation(associationDto.getNomAssociation());
            existingAssociation.setNbrSerie(associationDto.getNbrSerie());
            Association updateAssociation = iAssociationsRepository.save(existingAssociation);
            updateAssociation.setIdAssociation(id);
            return modelMapper.map(updateAssociation, AssociationDto.class);
        }
        return null;
        }

    @Override
    public void deleteAssociation(Long id) {
        Association association = iAssociationsRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (association != null){
            association.setDeleted(true);
            iAssociationsRepository.save(association);
        }

    }
}
