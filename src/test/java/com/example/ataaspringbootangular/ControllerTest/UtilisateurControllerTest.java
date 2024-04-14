package com.example.ataaspringbootangular.ControllerTest;

import com.example.ataaspringbootangular.controller.UtilisateurController;
import com.example.ataaspringbootangular.dto.UtilisateurDto;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;
import com.example.ataaspringbootangular.service.impl.UtilisateurServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UtilisateurControllerTest {

    @Mock
    private UtilisateurServiceImpl utilisateurService;

    @InjectMocks
    private UtilisateurController utilisateurController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUsersByRole() {
        String role = "AdminApp";
        List<UtilisateurDto> utilisateurDtos = Collections.singletonList(new UtilisateurDto());
        when(utilisateurService.getUsersWithRole(role)).thenReturn(utilisateurDtos);

        ResponseEntity<List<UtilisateurDto>> responseEntity = utilisateurController.getUsersByRole(role);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(utilisateurDtos, responseEntity.getBody());
        verify(utilisateurService, times(1)).getUsersWithRole(role);
    }

    @Test
    void ajouterUtilisateur() {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        when(utilisateurService.ajouterUtilisateur(utilisateurDto)).thenReturn(utilisateurDto);

        ResponseEntity<String> responseEntity = utilisateurController.ajouterUtilisateur(utilisateurDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("000", responseEntity.getBody());
        verify(utilisateurService, times(1)).ajouterUtilisateur(utilisateurDto);
    }

    @Test
    void updateUtilisateur() throws Exception {
        Long id = 1L;
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        when(utilisateurService.updateUtilisateur(utilisateurDto, id)).thenReturn(utilisateurDto);

        ResponseEntity<String> responseEntity = utilisateurController.updateUtilisateur(id, utilisateurDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("000", responseEntity.getBody());
        verify(utilisateurService, times(1)).updateUtilisateur(utilisateurDto, id);
    }

    @Test
    void getNumberOfUtilisateurs() {
        long count = 5L;
        when(utilisateurService.getNumberOfUtilisatuers()).thenReturn(count);

        ResponseEntity<Long> responseEntity = utilisateurController.getNumberOfUtilisateurs();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(count, responseEntity.getBody());
        verify(utilisateurService, times(1)).getNumberOfUtilisatuers();
    }

    @Test
    void getUtilisateurs() {
        List<UtilisateurDto> utilisateurDtos = Collections.singletonList(new UtilisateurDto());
        when(utilisateurService.getUtilisateurs()).thenReturn(utilisateurDtos);

        ResponseEntity<List<UtilisateurDto>> responseEntity = utilisateurController.getUtilisateurs();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(utilisateurDtos, responseEntity.getBody());
        verify(utilisateurService, times(1)).getUtilisateurs();
    }

    @Test
    void getUtilisateurById() throws UtilisateurFoundException {
        Long id = 1L;
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        when(utilisateurService.getUtilisateursById(id)).thenReturn(utilisateurDto);

        ResponseEntity<UtilisateurDto> responseEntity = utilisateurController.getUtilisateurById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(utilisateurDto, responseEntity.getBody());
        verify(utilisateurService, times(1)).getUtilisateursById(id);
    }

    @Test
    void deleteUtilisateur() {
        Long id = 1L;
        doNothing().when(utilisateurService).deleteUtilisateur(id);

        ResponseEntity<String> responseEntity = utilisateurController.deleteUtilisateur(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("000", responseEntity.getBody());
        verify(utilisateurService, times(1)).deleteUtilisateur(id);
    }
}