package com.example.ataaspringbootangular.ControllerTest;

import com.example.ataaspringbootangular.controller.UtilisateurController;
import com.example.ataaspringbootangular.dto.UtilisateurDto;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;
import com.example.ataaspringbootangular.service.IUtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

class UtilisateurControllerTest {

    @InjectMocks
    private UtilisateurController utilisateurController;

    @Mock
    private IUtilisateurService utilisateurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void ajouterUtilisateur() throws ParseException {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        when(utilisateurService.ajouterUtilisateur(any())).thenReturn(utilisateurDto);

        ResponseEntity<UtilisateurDto> responseEntity = utilisateurController.ajouterUtilisateur(utilisateurDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(utilisateurDto, responseEntity.getBody());
    }

    @Test
    void getUtilisateurs() {
        List<UtilisateurDto> utilisateurDtos = Arrays.asList(new UtilisateurDto(), new UtilisateurDto());
        when(utilisateurService.getUtilisateurs()).thenReturn(utilisateurDtos);

        ResponseEntity<List<UtilisateurDto>> responseEntity = utilisateurController.getUtilisateurs();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(utilisateurDtos, responseEntity.getBody());
    }

    @Test
    void getUtilisateurById() throws UtilisateurFoundException {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        when(utilisateurService.getUtilisateursById(any())).thenReturn(utilisateurDto);

        ResponseEntity<UtilisateurDto> responseEntity = utilisateurController.getUtilisateurById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(utilisateurDto, responseEntity.getBody());
    }

    @Test
    void updateUtilisateur() throws ParseException {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        when(utilisateurService.updateUtilisateur(any(), any())).thenReturn(utilisateurDto);

        ResponseEntity<UtilisateurDto> responseEntity = utilisateurController.updateUtilisateur(1L, utilisateurDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(utilisateurDto, responseEntity.getBody());
    }

    @Test
    void deleteUtilisateur() throws UtilisateurFoundException {
        doNothing().when(utilisateurService).deleteUtilisateur(any());

        ResponseEntity<String> responseEntity = utilisateurController.deleteUtilisateur(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Utilisateur with id 1 was deleted succes", responseEntity.getBody());
    }
}
