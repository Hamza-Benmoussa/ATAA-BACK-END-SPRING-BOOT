package com.example.ataaspringbootangular.controller;

import com.example.ataaspringbootangular.dto.AssociationDto;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;
import com.example.ataaspringbootangular.service.IAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/associations")
public class AssociationController {

    @Autowired
    private IAssociationService associationService;

    @PostMapping("/ajouterAssociation")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<AssociationDto> ajouterAssociation(@RequestBody @Valid AssociationDto associationDto) throws UtilisateurFoundException, VilleFoundException {
        AssociationDto savedAssociation = associationService.ajouterAssociation(associationDto);
        return new ResponseEntity<>(savedAssociation, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<List<AssociationDto>> getAssociations() {
        List<AssociationDto> associations = associationService.getAssociations();
        return new ResponseEntity<>(associations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<AssociationDto> getAssociationById(@PathVariable Long id) throws AssociationFoundException, AssociationFoundException {
        AssociationDto association = associationService.getAssociationsById(id);
        return new ResponseEntity<>(association, HttpStatus.OK);
    }

    @PutMapping("/updateAssociation/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<AssociationDto> updateAssociation(@PathVariable Long id, @Valid @RequestBody AssociationDto associationDto) throws UtilisateurFoundException, VilleFoundException {
        AssociationDto updatedAssociation = associationService.updateAssociation(associationDto, id);
        return new ResponseEntity<>(updatedAssociation, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAssociation/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<String> deleteAssociation(@PathVariable("id") Long id) {
        associationService.deleteAssociation(id);
        return ResponseEntity.ok("Association with id " +id+ " was deleted succes");
    }
}
