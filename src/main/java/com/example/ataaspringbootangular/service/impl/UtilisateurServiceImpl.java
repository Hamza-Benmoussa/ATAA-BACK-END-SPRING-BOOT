package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.dto.UtilisateurDto;
import com.example.ataaspringbootangular.entity.Utilisateur;
import com.example.ataaspringbootangular.exception.except.EmailDejaExisteException;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;
import com.example.ataaspringbootangular.repository.IUtilisateurRepository;
import com.example.ataaspringbootangular.service.IUtilisateurService;
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
public class UtilisateurServiceImpl implements IUtilisateurService {
    @Autowired
    private IUtilisateurRepository iUtilisateurRepository;
    @Autowired
    private ModelMapper modelMapper;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public UtilisateurDto ajouterUtilisateur(UtilisateurDto utilisateurDto) {
        checkExistEmail(utilisateurDto);
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
    public UtilisateurDto getUtilisateursById(Long id) throws UtilisateurFoundException {
        return iUtilisateurRepository.findByIdAndDeletedFalse(id)
        .map(utilisateur -> modelMapper.map(utilisateur , UtilisateurDto.class))
        .orElseThrow(() -> new UtilisateurFoundException("Utilisateur Not found" + id));
    }

    @Override
    public UtilisateurDto updateUtilisateur(UtilisateurDto utilisateurDto, Long id) throws ParseException {
        Utilisateur existingUtilisateur = iUtilisateurRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (existingUtilisateur != null){
            checkExistEmail(utilisateurDto);
            existingUtilisateur.setEmail(utilisateurDto.getEmail());
            existingUtilisateur.setGenre(utilisateurDto.getGenre());
            existingUtilisateur.setPassword(utilisateurDto.getPassword());
            existingUtilisateur.setTele(utilisateurDto.getTele());
            existingUtilisateur.setAddress(utilisateurDto.getAddress());
            existingUtilisateur.setNomComplete(utilisateurDto.getNomComplete());
            existingUtilisateur.setDateNaissance(dateFormat.parse(String.valueOf(utilisateurDto.getDateNaissance())));
        }

        return null;
    }

    private UtilisateurDto checkExistEmail (UtilisateurDto utilisateurDto){
        if (utilisateurDto.getEmail().equals(getByEmail(utilisateurDto.getEmail()))){
            throw new EmailDejaExisteException("deja exist cette email");
        }
        return utilisateurDto;
    }
    public String getByEmail(String email)
    {
        Utilisateur utilisateur = iUtilisateurRepository.findByEmailAndDeletedFalse(email);
        if(utilisateur != null)
        {
            return utilisateur.getEmail();
        }
        return null;
    }
    @Override
    public void deleteUtilisateur(Long id) {
        Utilisateur utilisateur = iUtilisateurRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (utilisateur !=null){
            utilisateur.setDeleted(true);
            iUtilisateurRepository.save(utilisateur);
        }
    }
}
