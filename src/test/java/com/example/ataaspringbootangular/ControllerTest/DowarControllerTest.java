package com.example.ataaspringbootangular.ControllerTest;

import com.example.ataaspringbootangular.controller.DowarController;
import com.example.ataaspringbootangular.dto.DowarDto;
import com.example.ataaspringbootangular.exception.except.DowarFoundException;
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
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private DowarController dowarController;

    @Test
    void ajouterDowar() throws Exception {
        // Mocking the behavior of dowarService.ajouterDowar()
        DowarDto dowarDto = new DowarDto();
        when(dowarService.ajouterDowar(any(DowarDto.class))).thenReturn(dowarDto);

        // Testing the DowarController method
        ResponseEntity<DowarDto> responseEntity = dowarController.ajouterDowar(dowarDto);

        // Assertions
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(dowarDto, responseEntity.getBody());
    }

    @Test
    void getDowars() {
        // Mocking the behavior of dowarService.getDowars()
        List<DowarDto> dowarList = Collections.singletonList(new DowarDto());
        when(dowarService.getDowars()).thenReturn(dowarList);

        // Testing the DowarController method
        ResponseEntity<List<DowarDto>> responseEntity = dowarController.getDowars();

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dowarList, responseEntity.getBody());
    }

    @Test
    void getDowarById() throws DowarFoundException {
        // Mocking the behavior of dowarService.getDowarsById()
        DowarDto dowarDto = new DowarDto();
        when(dowarService.getDowarsById(1L)).thenReturn(dowarDto);

        // Testing the DowarController method
        ResponseEntity<DowarDto> responseEntity = dowarController.getDowarById(1L);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dowarDto, responseEntity.getBody());
    }



    @Test
    void updateDowar() {
        // Mocking the behavior of dowarService.updateDowar()
        DowarDto dowarDto = new DowarDto();
        when(dowarService.updateDowar(any(DowarDto.class), anyLong())).thenReturn(dowarDto);

        // Testing the DowarController method
        ResponseEntity<DowarDto> responseEntity = dowarController.updateDowar(1L, dowarDto);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dowarDto, responseEntity.getBody());
    }

    @Test
    void deleteDowar() {
        // Testing the DowarController method
        ResponseEntity<String> responseEntity = dowarController.deleteDowar(1L);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Dowar with id 1 was deleted succes", responseEntity.getBody());

        // Verify that dowarService.deleteDowar() was called once with the correct argument
        verify(dowarService, times(1)).deleteDowar(1L);
    }
}
