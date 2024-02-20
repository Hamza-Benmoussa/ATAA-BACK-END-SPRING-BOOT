package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.dto.AssociationDto;
import com.example.ataaspringbootangular.dto.UtilisateurDto;
import com.example.ataaspringbootangular.dto.VilleDto;
import com.example.ataaspringbootangular.entity.Association;
import com.example.ataaspringbootangular.entity.Utilisateur;
import com.example.ataaspringbootangular.entity.Ville;
import com.example.ataaspringbootangular.exception.except.*;
import com.example.ataaspringbootangular.repository.IAssociationsRepository;
import com.example.ataaspringbootangular.service.IAssociationService;
import com.example.ataaspringbootangular.service.IUtilisateurService;
import com.example.ataaspringbootangular.service.IVilleService;
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
    @Autowired
    private IUtilisateurService iUtilisateurService;
    @Autowired
    private IVilleService iVilleService;

    @Override
    public AssociationDto ajouterAssociation(AssociationDto associationDto) throws UtilisateurFoundException, VilleFoundException {
        checkNbrSerieExist(associationDto);
        VilleDto villeDto = iVilleService.getVillesById(associationDto.getVilleId());
        Ville ville = modelMapper.map(villeDto , Ville.class);
        UtilisateurDto utilisateurDto = iUtilisateurService.getUtilisateursById(associationDto.getNomPresidantId());
        Utilisateur utilisateur = modelMapper.map(utilisateurDto, Utilisateur.class);
        Association association = modelMapper.map(associationDto , Association.class);
        association.setNomPresidant(utilisateur);
        association.setVille(ville);
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
    public Association getAssociationByName(String associationName) {
        return iAssociationsRepository.findByNomAssociationAndDeletedFalse(associationName);
    }

    @Override
    public AssociationDto getAssociationsById(Long id) throws AssociationFoundException {
        return iAssociationsRepository.findByIdAndDeletedFalse(id)
                .map(association -> modelMapper.map(association , AssociationDto.class))
                .orElseThrow(() -> new AssociationFoundException("Association Not found" + id));
    }

    private AssociationDto checkNbrSerieExist (AssociationDto associationDto){
        if (associationDto.getNbrSerie().equals(getByNbrSerie(associationDto.getNbrSerie()))){
            throw new NbrSerieDejaExisteException("deja exist cette nbrSerie");
        }
        return associationDto;
    }
    public String getByNbrSerie(String nbrSerie)
    {
        Association association = iAssociationsRepository.findByNbrSerieAndDeletedFalse(nbrSerie);
        if(association != null)
        {
            return association.getNbrSerie();
        }
        return null;
    }
    @Override
    public AssociationDto updateAssociation(AssociationDto associationDto, Long id) {
        Association existingAssociation = iAssociationsRepository.findByIdAndDeletedFalse(id).orElse(null);
            checkNbrSerieExist(associationDto);
            existingAssociation.setNomAssociation(associationDto.getNomAssociation());
            existingAssociation.setNbrSerie(associationDto.getNbrSerie());
            Association updateAssociation = iAssociationsRepository.save(existingAssociation);
            updateAssociation.setId(id);
            return modelMapper.map(updateAssociation, AssociationDto.class);
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
