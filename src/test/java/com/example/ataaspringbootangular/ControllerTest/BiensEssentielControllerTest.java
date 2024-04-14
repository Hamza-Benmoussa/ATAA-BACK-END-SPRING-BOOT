package com.example.ataaspringbootangular.ControllerTest;

import com.example.ataaspringbootangular.controller.BiensEssentielController;
import com.example.ataaspringbootangular.dto.BiensEssantielDto;
import com.example.ataaspringbootangular.exception.except.BiensEssentielFoundException;
import com.example.ataaspringbootangular.service.impl.BiensEssantielImpl;
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
    private BiensEssantielImpl biensEssantielService;

    @InjectMocks
    private BiensEssentielController biensEssentielController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void ajouterBiensEssentiel() throws Exception {
        BiensEssantielDto biensEssantielDto = new BiensEssantielDto();
        when(biensEssantielService.ajouterBiensEssantiel(biensEssantielDto)).thenReturn(biensEssantielDto);

        ResponseEntity<String> responseEntity = biensEssentielController.ajouterBiensEssentiel(biensEssantielDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("000", responseEntity.getBody());
        verify(biensEssantielService, times(1)).ajouterBiensEssantiel(biensEssantielDto);
    }

    @Test
    void updateBiensEssentiel() throws Exception {
        Long id = 1L;
        BiensEssantielDto biensEssantielDto = new BiensEssantielDto();
        when(biensEssantielService.updateBiensEssentiel(biensEssantielDto, id)).thenReturn(biensEssantielDto);

        ResponseEntity<String> responseEntity = biensEssentielController.updateBiensEssentiel(id, biensEssantielDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("000", responseEntity.getBody());
        verify(biensEssantielService, times(1)).updateBiensEssentiel(biensEssantielDto, id);
    }

    @Test
    void deleteBiensEssentiel() {
        Long id = 1L;
        doNothing().when(biensEssantielService).deleteBiensEssantiel(id);

        ResponseEntity<String> responseEntity = biensEssentielController.deleteBiensEssentiel(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("000", responseEntity.getBody());
        verify(biensEssantielService, times(1)).deleteBiensEssantiel(id);
    }

    @Test
    void getNumberOfbiens() {
        long count = 5L;
        when(biensEssantielService.getNumberOfBiensEssentislsForCurrentUser()).thenReturn(count);

        ResponseEntity<Long> responseEntity = biensEssentielController.getNumberOfbiens();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(count, responseEntity.getBody());
        verify(biensEssantielService, times(1)).getNumberOfBiensEssentislsForCurrentUser();
    }

    @Test
    void getBiensEssentielById() throws BiensEssentielFoundException {
        Long id = 1L;
        BiensEssantielDto biensEssantielDto = new BiensEssantielDto();
        when(biensEssantielService.getBiensEssantielsById(id)).thenReturn(biensEssantielDto);

        ResponseEntity<BiensEssantielDto> responseEntity = biensEssentielController.getBiensEssentielById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(biensEssantielDto, responseEntity.getBody());
        verify(biensEssantielService, times(1)).getBiensEssantielsById(id);
    }
}