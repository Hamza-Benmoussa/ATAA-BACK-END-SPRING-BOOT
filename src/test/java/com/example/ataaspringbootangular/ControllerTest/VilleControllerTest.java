package com.example.ataaspringbootangular.ControllerTest;

import com.example.ataaspringbootangular.controller.VilleController;
import com.example.ataaspringbootangular.dto.VilleDto;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;
import com.example.ataaspringbootangular.service.impl.VilleServiceImpl;
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
public class VilleControllerTest {

    @Mock
    private VilleServiceImpl villeService;

    @InjectMocks
    private VilleController villeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void ajouterVille() {
        VilleDto villeDto = new VilleDto();
        when(villeService.ajouterVille(villeDto)).thenReturn(villeDto);

        ResponseEntity<String> responseEntity = villeController.ajouterVille(villeDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("000", responseEntity.getBody());
        verify(villeService, times(1)).ajouterVille(villeDto);
    }

    @Test
    void updateVille() throws Exception {
        Long id = 1L;
        VilleDto villeDto = new VilleDto();
        when(villeService.updateVille(villeDto, id)).thenReturn(villeDto);

        ResponseEntity<String> responseEntity = villeController.updateVille(id, villeDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("000", responseEntity.getBody());
        verify(villeService, times(1)).updateVille(villeDto, id);
    }

    @Test
    void deleteVille() {
        Long id = 1L;
        doNothing().when(villeService).deleteVille(id);

        ResponseEntity<String> responseEntity = villeController.deleteVille(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("000", responseEntity.getBody());
        verify(villeService, times(1)).deleteVille(id);
    }

    @Test
    void getVilles() {
        List<VilleDto> villeDtos = Collections.singletonList(new VilleDto());
        when(villeService.getVilles()).thenReturn(villeDtos);

        ResponseEntity<List<VilleDto>> responseEntity = villeController.getVilles();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(villeDtos, responseEntity.getBody());
        verify(villeService, times(1)).getVilles();
    }

    @Test
    void getVillesWithDowarsAndArrivedKafilas() {
        List<VilleDto> villeDtos = Collections.singletonList(new VilleDto());
        when(villeService.getVillesWithDowarsAndArrivedKafilas()).thenReturn(villeDtos);

        ResponseEntity<List<VilleDto>> responseEntity = villeController.getVillesWithDowarsAndArrivedKafilas();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(villeDtos, responseEntity.getBody());
        verify(villeService, times(1)).getVillesWithDowarsAndArrivedKafilas();
    }

    @Test
    void getNumberOfVilles() {
        long count = 5L;
        when(villeService.getNumberOfVilles()).thenReturn(count);

        ResponseEntity<Long> responseEntity = villeController.getNumberOfVilles();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(count, responseEntity.getBody());
        verify(villeService, times(1)).getNumberOfVilles();
    }

    @Test
    void getVilleById() throws VilleFoundException {
        Long id = 1L;
        VilleDto villeDto = new VilleDto();
        when(villeService.getVillesById(id)).thenReturn(villeDto);

        ResponseEntity<VilleDto> responseEntity = villeController.getVilleById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(villeDto, responseEntity.getBody());
        verify(villeService, times(1)).getVillesById(id);
    }
}