package com.example.ataaspringbootangular.ControllerTest;

import com.example.ataaspringbootangular.controller.KafilaController;
import com.example.ataaspringbootangular.dto.KafilaDto;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;
import com.example.ataaspringbootangular.exception.except.BiensEssentielFoundException;
import com.example.ataaspringbootangular.exception.except.DowarFoundException;
import com.example.ataaspringbootangular.exception.except.KafilaFoundException;
import com.example.ataaspringbootangular.service.impl.KafilaSericeImpl;
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
public class KafilaControllerTest {

    @Mock
    private KafilaSericeImpl kafilaService;

    @InjectMocks
    private KafilaController kafilaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void ajouterKafila() throws DowarFoundException, AssociationFoundException, BiensEssentielFoundException {
        KafilaDto kafilaDto = new KafilaDto();
        when(kafilaService.ajouterKafila(kafilaDto)).thenReturn(kafilaDto);

        ResponseEntity<String> responseEntity = kafilaController.ajouterKafila(kafilaDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("000", responseEntity.getBody());
        verify(kafilaService, times(1)).ajouterKafila(kafilaDto);
    }

    @Test
    void updateKafila() throws Exception {
        Long id = 1L;
        KafilaDto kafilaDto = new KafilaDto();
        when(kafilaService.updateKafila(kafilaDto, id)).thenReturn(kafilaDto);

        ResponseEntity<String> responseEntity = kafilaController.updateKafila(id, kafilaDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("000", responseEntity.getBody());
        verify(kafilaService, times(1)).updateKafila(kafilaDto, id);
    }

    @Test
    void deleteKafila() {
        Long id = 1L;
        doNothing().when(kafilaService).deleteKafila(id);

        ResponseEntity<String> responseEntity = kafilaController.deleteKafila(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("000", responseEntity.getBody());
        verify(kafilaService, times(1)).deleteKafila(id);
    }

    @Test
    void getNumberOfKafilas() {
        long count = 5L;
        when(kafilaService.getNumberOfKafilasForCurrentUser()).thenReturn(count);

        ResponseEntity<Long> responseEntity = kafilaController.getNumberOfKafilas();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(count, responseEntity.getBody());
        verify(kafilaService, times(1)).getNumberOfKafilasForCurrentUser();
    }


    @Test
    void getKafilaById() throws KafilaFoundException {
        Long id = 1L;
        KafilaDto kafilaDto = new KafilaDto();
        when(kafilaService.getKafilasById(id)).thenReturn(kafilaDto);

        ResponseEntity<KafilaDto> responseEntity = kafilaController.getKafilaById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(kafilaDto, responseEntity.getBody());
        verify(kafilaService, times(1)).getKafilasById(id);
    }
}