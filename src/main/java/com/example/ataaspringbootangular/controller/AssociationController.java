package com.example.ataaspringbootangular.controller;

import com.example.ataaspringbootangular.dto.AssociationDto;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;
import com.example.ataaspringbootangular.service.IAssociationService;
import com.example.ataaspringbootangular.service.IUtilisateurService;
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
    @Autowired
    private IUtilisateurService utilisateurService;

    @PostMapping("/ajouterAssociation")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<String> ajouterAssociation(@RequestBody @Valid AssociationDto associationDto) throws UtilisateurFoundException, VilleFoundException {
        try {
            AssociationDto savedAssociation = associationService.ajouterAssociation(associationDto);
            return new ResponseEntity<>("000", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("001", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateAssociation/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<String> updateAssociation(@PathVariable Long id, @Valid @RequestBody AssociationDto associationDto) throws UtilisateurFoundException, VilleFoundException {
        try {
            AssociationDto updatedAssociation = associationService.updateAssociation(associationDto, id);
            return new ResponseEntity<>("000", HttpStatus.OK); // Return 000 for success
        } catch (Exception e) {
            return new ResponseEntity<>("001", HttpStatus.INTERNAL_SERVER_ERROR); // Return 001 for failure
        }
    }

    @DeleteMapping("/deleteAssociation/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<String> deleteAssociation(@PathVariable("id") Long id) {
        try {
            associationService.deleteAssociation(id);
            return new ResponseEntity<>("000", HttpStatus.OK); // Return 000 for success
        } catch (Exception e) {
            return new ResponseEntity<>("001", HttpStatus.INTERNAL_SERVER_ERROR); // Return 001 for failure
        }
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
    @GetMapping("/count")
    public ResponseEntity<Long> getNumberOfAssociations() {
        long count = associationService.getNumberOfAssociations();
        return ResponseEntity.ok(count);
    }
}
