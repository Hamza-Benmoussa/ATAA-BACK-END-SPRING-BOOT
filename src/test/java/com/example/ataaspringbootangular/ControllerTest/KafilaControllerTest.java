package com.example.ataaspringbootangular.ControllerTest;

import com.example.ataaspringbootangular.controller.KafilaController;
import com.example.ataaspringbootangular.dto.KafilaDto;
import com.example.ataaspringbootangular.dto.MemberDto;
import com.example.ataaspringbootangular.exception.except.*;
import com.example.ataaspringbootangular.service.IKafilaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class KafilaControllerTest {

    @Mock
    private IKafilaService kafilaService;

    @InjectMocks
    private KafilaController kafilaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void ajouterKafila() throws DowarFoundException, AssociationFoundException, BiensEssentielFoundException, KafilaFoundException, ParseException {
        KafilaDto kafilaDto = new KafilaDto();
        when(kafilaService.ajouterKafila(kafilaDto)).thenReturn(kafilaDto);

        ResponseEntity<KafilaDto> responseEntity = kafilaController.ajouterKafila(kafilaDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(kafilaDto, responseEntity.getBody());
        verify(kafilaService, times(1)).ajouterKafila(kafilaDto);
    }

    @Test
    void getKafilas() {
        List<KafilaDto> kafilaDtos = Collections.singletonList(new KafilaDto());
        when(kafilaService.getKafilas()).thenReturn(kafilaDtos);

        ResponseEntity<List<KafilaDto>> responseEntity = kafilaController.getKafilas();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(kafilaDtos, responseEntity.getBody());
        verify(kafilaService, times(1)).getKafilas();
    }

    @Test
    void getAllKafilasByPresidentAssociationId() throws MemberFoundException {
        // Assuming you have a known president association ID for testing purposes
        Long presidentAssociationId = 123L;

        // Mock the behavior of your service method
        List<KafilaDto> mockKafilas = Collections.singletonList(new KafilaDto());
        when(kafilaService.getAllKafilasByPresidentAssociationId(presidentAssociationId)).thenReturn(mockKafilas);

        // Call the controller method
        ResponseEntity<List<KafilaDto>> responseEntity = kafilaController.getAllMembersByPresidentAssociationId(presidentAssociationId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockKafilas, responseEntity.getBody());
        verify(kafilaService, times(1)).getAllKafilasByPresidentAssociationId(presidentAssociationId);
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

    @Test
    void updateKafila() throws ParseException {
        Long id = 1L;
        KafilaDto kafilaDto = new KafilaDto();
        when(kafilaService.updateKafila(kafilaDto, id)).thenReturn(kafilaDto);

        ResponseEntity<KafilaDto> responseEntity = kafilaController.deletkafila(id, kafilaDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(kafilaDto, responseEntity.getBody());
        verify(kafilaService, times(1)).updateKafila(kafilaDto, id);
    }

    @Test
    void deleteKafila() {
        Long id = 1L;
        doNothing().when(kafilaService).deleteKafila(id);

        ResponseEntity<String> responseEntity = kafilaController.deleteKafila(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Kafila with id 1 was deleted succes", responseEntity.getBody());
        verify(kafilaService, times(1)).deleteKafila(id);
    }
}
