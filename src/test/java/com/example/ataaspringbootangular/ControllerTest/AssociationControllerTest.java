package com.example.ataaspringbootangular.ControllerTest;

import com.example.ataaspringbootangular.controller.AssociationController;
import com.example.ataaspringbootangular.dto.AssociationDto;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;
import com.example.ataaspringbootangular.service.IAssociationService;
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

public class AssociationControllerTest {

    @Mock
    private IAssociationService associationService;

    @InjectMocks
    private AssociationController associationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void ajouterAssociation() throws UtilisateurFoundException, VilleFoundException {
        AssociationDto associationDto = new AssociationDto();
        when(associationService.ajouterAssociation(associationDto)).thenReturn(associationDto);

        ResponseEntity<String> responseEntity = associationController.ajouterAssociation(associationDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(associationDto, responseEntity.getBody());
        verify(associationService, times(1)).ajouterAssociation(associationDto);
    }

    @Test
    void getAssociations() {
        List<AssociationDto> associationDtos = Collections.singletonList(new AssociationDto());
        when(associationService.getAssociations()).thenReturn(associationDtos);

        ResponseEntity<List<AssociationDto>> responseEntity = associationController.getAssociations();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(associationDtos, responseEntity.getBody());
        verify(associationService, times(1)).getAssociations();
    }

    @Test
    void getAssociationById() throws AssociationFoundException {
        Long id = 1L;
        AssociationDto associationDto = new AssociationDto();
        when(associationService.getAssociationsById(id)).thenReturn(associationDto);

        ResponseEntity<AssociationDto> responseEntity = associationController.getAssociationById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(associationDto, responseEntity.getBody());
        verify(associationService, times(1)).getAssociationsById(id);
    }

    @Test
    void updateAssociation() throws UtilisateurFoundException, VilleFoundException {
        Long id = 1L;
        AssociationDto associationDto = new AssociationDto();
        when(associationService.updateAssociation(associationDto, id)).thenReturn(associationDto);

        ResponseEntity<String> responseEntity = associationController.updateAssociation(id, associationDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(associationDto, responseEntity.getBody());
        verify(associationService, times(1)).updateAssociation(associationDto, id);
    }

    @Test
    void deleteAssociation() {
        Long id = 1L;
        doNothing().when(associationService).deleteAssociation(id);

        ResponseEntity<String> responseEntity = associationController.deleteAssociation(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Association with id 1 was deleted succes", responseEntity.getBody());
        verify(associationService, times(1)).deleteAssociation(id);
    }
}
