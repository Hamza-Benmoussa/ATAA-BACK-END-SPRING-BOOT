package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.dto.UtilisateurDto;
import com.example.ataaspringbootangular.entity.Utilisateur;
import com.example.ataaspringbootangular.exception.except.EmailDejaExisteException;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;
import com.example.ataaspringbootangular.repository.IUtilisateurRepository;
import com.example.ataaspringbootangular.service.IUtilisateurService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UtilisateurDto ajouterUtilisateur(UtilisateurDto utilisateurDto) {
        String encodedPassword = passwordEncoder.encode(utilisateurDto.getPassword());
        utilisateurDto.setPassword(encodedPassword);
        checkExistEmail(utilisateurDto.getEmail());
        Utilisateur utilisateur = modelMapper.map(utilisateurDto , Utilisateur.class);
        Utilisateur savedUtilisateur = iUtilisateurRepository.save(utilisateur);
        return maskPasswordInDto(modelMapper.map(savedUtilisateur, UtilisateurDto.class));
    }

    @Override
    public List<UtilisateurDto> getUtilisateurs() {
        List<Utilisateur> utilisateurs = iUtilisateurRepository.findByDeletedFalse();
        return utilisateurs.stream()
                .map(utilisateur -> modelMapper.map(utilisateur , UtilisateurDto.class))
                .collect(Collectors.toList());
    }

    private UtilisateurDto maskPasswordInDto(UtilisateurDto utilisateurDto) {
        if (utilisateurDto != null) {
            utilisateurDto.setPassword("****");
        }
        return utilisateurDto;
    }
    @Override
    public UtilisateurDto getUtilisateursById(Long id) throws UtilisateurFoundException {
        return iUtilisateurRepository.findByIdAndDeletedFalse(id)
        .map(utilisateur -> modelMapper.map(utilisateur , UtilisateurDto.class))
        .orElseThrow(() -> new UtilisateurFoundException("Utilisateur Not found with id = " + id));
    }

    @Override
    public UtilisateurDto updateUtilisateur(UtilisateurDto utilisateurDto, Long id) {
        Utilisateur existingUtilisateur = iUtilisateurRepository.findByIdAndDeletedFalse(id).orElse(null);

        if (!existingUtilisateur.getEmail().equals(utilisateurDto.getEmail())) {
            checkExistEmail(utilisateurDto.getEmail());
        }
        if (utilisateurDto.getPassword() != null && !utilisateurDto.getPassword().isEmpty() &&
                !passwordEncoder.matches(utilisateurDto.getPassword(), existingUtilisateur.getPassword())) {
            // Encode the new password
            existingUtilisateur.setPassword(passwordEncoder.encode(utilisateurDto.getPassword()));
        }
        existingUtilisateur.setEmail(utilisateurDto.getEmail());
        existingUtilisateur.setGenre(utilisateurDto.getGenre());
        existingUtilisateur.setTele(utilisateurDto.getTele());
        existingUtilisateur.setAddress(utilisateurDto.getAddress());
        existingUtilisateur.setNomComplete(utilisateurDto.getNomComplete());
        existingUtilisateur.setDateNaissance(utilisateurDto.getDateNaissance());

        try {
            // Save the updated user
            Utilisateur updatedUtilisateur = iUtilisateurRepository.save(existingUtilisateur);

            // Map and return the updated user DTO
            return modelMapper.map(updatedUtilisateur, UtilisateurDto.class);
        } catch (EmailDejaExisteException ex) {
            throw new EmailDejaExisteException("Email déjà existant dans la base de données.");

        }
    }

    @Override
    public Utilisateur loadUserByEmail(String email) {
        return iUtilisateurRepository.findByEmailAndDeletedFalse(email);
    }


    private void checkExistEmail(String email) {
        if (getByEmail(email) != null) {
            throw new EmailDejaExisteException("Email déjà existant dans la base de données.");
        }
    }

    public String getByEmail(String email) {
        Utilisateur utilisateur = iUtilisateurRepository.findByEmailAndDeletedFalse(email);
        if (utilisateur != null) {
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
