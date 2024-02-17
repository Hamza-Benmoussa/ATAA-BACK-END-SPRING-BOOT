package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.dto.UtilisateurDto;
import com.example.ataaspringbootangular.entity.Utilisateur;
import com.example.ataaspringbootangular.repository.IUtilisateurRepository;
import com.example.ataaspringbootangular.service.IUtilisateurService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UtilisateurServiceImpl implements IUtilisateurService {
    @Autowired
    private IUtilisateurRepository iUtilisateurRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UtilisateurDto ajouterUtilisateur(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = modelMapper.map(utilisateurDto , Utilisateur.class);
        Utilisateur saveUtilisateur = iUtilisateurRepository.save(utilisateur);
        return modelMapper.map(saveUtilisateur, UtilisateurDto.class);
    }

    @Override
    public List<UtilisateurDto> getUtilisateurs() {
        List<Utilisateur> utilisateurs = iUtilisateurRepository.findByDeletedFalse();
        return utilisateurs.stream()
                .map(utilisateur -> modelMapper.map(utilisateur , UtilisateurDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UtilisateurDto getUtilisateursById(Long id) {
        return iUtilisateurRepository.findByIdAndDeletedFalse(id)
        .map(utilisateur -> modelMapper.map(utilisateur , UtilisateurDto.class))
        .orElse(null);
    }

    @Override
    public UtilisateurDto updateUtilisateur(UtilisateurDto utilisateurDto, Long id) {
        Utilisateur existingUtilisateur = iUtilisateurRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (existingUtilisateur != null){
            existingUtilisateur.setEmail(utilisateurDto.getEmail());
            existingUtilisateur.se
        }

        return null;
    }

    @Override
    public void deleteUtilisateur(Long id) {

    }
}
