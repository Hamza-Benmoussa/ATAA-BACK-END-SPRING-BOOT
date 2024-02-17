package com.example.ataaspringbootangular.service;


import com.example.ataaspringbootangular.dto.AssociationDto;
import com.example.ataaspringbootangular.entity.Association;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;

import java.util.List;

public interface IAssociationService {
    AssociationDto ajouterAssociation(AssociationDto associationDto);


    List<AssociationDto> getAssociations();

    AssociationDto getAssociationsById(Long id) throws AssociationFoundException;

    AssociationDto updateAssociation(AssociationDto associationDto, Long id);

    void deleteAssociation(Long id);
}
