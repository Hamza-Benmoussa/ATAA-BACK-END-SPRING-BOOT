package com.example.ataaspringbootangular.ControllerTest;

import com.example.ataaspringbootangular.controller.DowarController;
import com.example.ataaspringbootangular.dto.DowarDto;
import com.example.ataaspringbootangular.exception.except.DowarFoundException;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;
import com.example.ataaspringbootangular.service.IDowarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class DowarControllerTest {

    @Mock
    private IDowarService dowarService;

    @InjectMocks
    private DowarController dowarController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void ajouterDowar() throws VilleFoundException, DowarFoundException, VilleFoundException {
        DowarDto dowarDto = new DowarDto();
        when(dowarService.ajouterDowar(dowarDto)).thenReturn(dowarDto);

        ResponseEntity<DowarDto> responseEntity = dowarController.ajouterDowar(dowarDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(dowarDto, responseEntity.getBody());
        verify(dowarService, times(1)).ajouterDowar(dowarDto);
    }

    @Test
    void getDowars() {
        List<DowarDto> dowarDtos = Collections.singletonList(new DowarDto());
        when(dowarService.getDowars()).thenReturn(dowarDtos);

        ResponseEntity<List<DowarDto>> responseEntity = dowarController.getDowars();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dowarDtos, responseEntity.getBody());
        verify(dowarService, times(1)).getDowars();
    }

    @Test
    void getDowarById() throws DowarFoundException {
        Long id = 1L;
        DowarDto dowarDto = new DowarDto();
        when(dowarService.getDowarsById(id)).thenReturn(dowarDto);

        ResponseEntity<DowarDto> responseEntity = dowarController.getDowarById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dowarDto, responseEntity.getBody());
        verify(dowarService, times(1)).getDowarsById(id);
    }

    @Test
    void updateDowar() throws VilleFoundException {
        Long id = 1L;
        DowarDto dowarDto = new DowarDto();
        when(dowarService.updateDowar(dowarDto, id)).thenReturn(dowarDto);

        ResponseEntity<DowarDto> responseEntity = dowarController.updateDowar(id, dowarDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dowarDto, responseEntity.getBody());
        verify(dowarService, times(1)).updateDowar(dowarDto, id);
    }

    @Test
    void deleteDowar() {
        Long id = 1L;
        doNothing().when(dowarService).deleteDowar(id);

        ResponseEntity<String> responseEntity = dowarController.deleteDowar(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Dowar with id 1 was deleted succes", responseEntity.getBody());
        verify(dowarService, times(1)).deleteDowar(id);
    }

    @Test
    void getTotalKafilasForDowar() throws DowarFoundException {
        Long dowarId = 1L;
        int totalKafilas = 5;
        when(dowarService.calculateTotalArrivedKafilasForDowar(dowarId)).thenReturn(totalKafilas);

        ResponseEntity<Integer> responseEntity = dowarController.getTotalKafilasForDowar(dowarId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(totalKafilas, responseEntity.getBody());
        verify(dowarService, times(1)).calculateTotalArrivedKafilasForDowar(dowarId);
    }
}