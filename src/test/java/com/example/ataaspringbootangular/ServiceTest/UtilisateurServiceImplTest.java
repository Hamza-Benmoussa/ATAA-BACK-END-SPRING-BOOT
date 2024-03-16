package com.example.ataaspringbootangular.ServiceTest;

import com.example.ataaspringbootangular.dto.UtilisateurDto;
import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Enum.RoleUser;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;
import com.example.ataaspringbootangular.service.IUtilisateurService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")

class UtilisateurServiceImplTest {
    @Autowired
    private IUtilisateurService iUtilisateurService;
    @Autowired
    private ModelMapper modelMapper;
    private UtilisateurDto utilisateurDto;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() throws ParseException {
        utilisateurDto = new UtilisateurDto();
        utilisateurDto.setEmail("ham-"+ System.currentTimeMillis() +"@gmail.com");
        utilisateurDto.setGenre(Genre.Male);
        utilisateurDto.setTele("+212 646210352");
        utilisateurDto.setAddress("Res essafa gh 13 imm 3");
        utilisateurDto.setNomComplete("Hamza Benmoussa");
        utilisateurDto.setDateNaissance(LocalDate.now());
        utilisateurDto.setRoleUser(RoleUser.AdminApp);
        utilisateurDto.setPassword("123");
        utilisateurDto = iUtilisateurService.ajouterUtilisateur(utilisateurDto);
    }
    @AfterEach
    void down(){
        iUtilisateurService.deleteUtilisateur(utilisateurDto.getId());
    }


    @Test
    void ajouterUtilisateur() throws UtilisateurFoundException {
        assertNotNull(utilisateurDto,"Utilisateur not inserted");
        UtilisateurDto retrievedUtilisateurDto = iUtilisateurService.getUtilisateursById(utilisateurDto.getId());
        assertNotNull(retrievedUtilisateurDto, "Utilisateur not found in the database");

    }

    @Test
    void getUtilisateurs() {
        List<UtilisateurDto> utilisateurDtos = iUtilisateurService.getUtilisateurs();
        assertNotNull(utilisateurDtos,"List is empty");
    }


    @Test
    void getUtilisateursById() throws UtilisateurFoundException {
        UtilisateurDto retrivedUtilisateurDto = iUtilisateurService.getUtilisateursById(utilisateurDto.getId());
        assertNotNull(retrivedUtilisateurDto , "Utilisateur Not found");
    }


    @Test
    void updateUtilisateur() throws ParseException {
        utilisateurDto.setEmail("beousaa@gmail.com");
        UtilisateurDto updateUtilisateurDto = iUtilisateurService.updateUtilisateur(utilisateurDto , utilisateurDto.getId());
        assertEquals("beousaa@gmail.com", updateUtilisateurDto.getEmail(), "Username should be updated");

    }


    @Test
    void deleteUtilisateur() throws UtilisateurFoundException {

        Long utilisateurId = utilisateurDto.getId();

        assertNotNull(iUtilisateurService.getUtilisateursById(utilisateurId), "Utilisateur not found before deletion");
        iUtilisateurService.deleteUtilisateur(utilisateurId);
    }
}