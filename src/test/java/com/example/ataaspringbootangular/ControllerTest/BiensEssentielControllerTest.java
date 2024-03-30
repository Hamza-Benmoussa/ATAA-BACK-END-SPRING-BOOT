package com.example.ataaspringbootangular.ControllerTest;

import com.example.ataaspringbootangular.controller.BiensEssentielController;
import com.example.ataaspringbootangular.dto.BiensEssantielDto;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;
import com.example.ataaspringbootangular.exception.except.BiensEssentielFoundException;
import com.example.ataaspringbootangular.service.IBiensEssantielService;
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
public class BiensEssentielControllerTest {

    @Mock
    private IBiensEssantielService biensEssantielService;

    @InjectMocks
    private BiensEssentielController biensEssentielController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void ajouterBiensEssentiel() throws BiensEssentielFoundException, AssociationFoundException {
        BiensEssantielDto biensEssantielDto = new BiensEssantielDto();
        when(biensEssantielService.ajouterBiensEssantiel(biensEssantielDto)).thenReturn(biensEssantielDto);

        ResponseEntity<String> responseEntity = biensEssentielController.ajouterBiensEssentiel(biensEssantielDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(biensEssantielDto, responseEntity.getBody());
        verify(biensEssantielService, times(1)).ajouterBiensEssantiel(biensEssantielDto);
    }

    @Test
    void getBiensEssentielById() throws BiensEssentielFoundException {
        Long id = 1L;
        BiensEssantielDto biensEssentielDto = new BiensEssantielDto();
        when(biensEssantielService.getBiensEssantielsById(id)).thenReturn(biensEssentielDto);

        ResponseEntity<BiensEssantielDto> responseEntity = biensEssentielController.getBiensEssentielById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(biensEssentielDto, responseEntity.getBody());
        verify(biensEssantielService, times(1)).getBiensEssantielsById(id);
    }

    @Test
    void updateBiensEssentiel() {
        Long id = 1L;
        BiensEssantielDto biensEssantielDto = new BiensEssantielDto();
        when(biensEssantielService.updateBiensEssentiel(biensEssantielDto, id)).thenReturn(biensEssantielDto);

        ResponseEntity<String> responseEntity = biensEssentielController.updateBiensEssentiel(id, biensEssantielDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(biensEssantielDto, responseEntity.getBody());
        verify(biensEssantielService, times(1)).updateBiensEssentiel(biensEssantielDto, id);
    }

    @Test
    void deleteBiensEssentiel() {
        Long id = 1L;
        doNothing().when(biensEssantielService).deleteBiensEssantiel(id);

        ResponseEntity<String> responseEntity = biensEssentielController.deleteBiensEssentiel(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("BiensEssentiel with id 1 was deleted succes", responseEntity.getBody());
        verify(biensEssantielService, times(1)).deleteBiensEssantiel(id);
    }
}