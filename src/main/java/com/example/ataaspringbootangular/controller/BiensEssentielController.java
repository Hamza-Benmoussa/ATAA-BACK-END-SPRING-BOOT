package com.example.ataaspringbootangular.controller;

import com.example.ataaspringbootangular.dto.BiensEssantielDto;
import com.example.ataaspringbootangular.dto.KafilaDto;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;
import com.example.ataaspringbootangular.exception.except.BiensEssentielFoundException;
import com.example.ataaspringbootangular.service.IBiensEssantielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/biensEssentiels")
@CrossOrigin("*")
public class BiensEssentielController {
    @Autowired
    private IBiensEssantielService biensEssantielService;

    @PostMapping("/ajouterBiensEssentiel")
    @PreAuthorize("hasAuthority('PresidantAssociation')")
    public ResponseEntity<BiensEssantielDto> ajouterBiensEssentiel(@RequestBody @Valid BiensEssantielDto biensEssantielDto) throws AssociationFoundException, AssociationFoundException {
        BiensEssantielDto savedBiensEssentiel = biensEssantielService.ajouterBiensEssantiel(biensEssantielDto);
        return new ResponseEntity<>(savedBiensEssentiel, HttpStatus.CREATED);
    }

    @GetMapping("/bienByPres/{presidentAssociationId}")
    @PreAuthorize("hasAuthority('PresidantAssociation')")
    public ResponseEntity<List<BiensEssantielDto>> getAllMembersByPresidentAssociationId(@PathVariable Long presidentAssociationId) {
        List<BiensEssantielDto> members = biensEssantielService.getAllBiensEssentilesByPresidentAssociationId(presidentAssociationId);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('PresidantAssociation')")
    public ResponseEntity<List<BiensEssantielDto>> getBiensEssentiels() {
        List<BiensEssantielDto> biensEssentiels = biensEssantielService.getBiensEssantiels();
        return new ResponseEntity<>(biensEssentiels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PresidantAssociation')")
    public ResponseEntity<BiensEssantielDto> getBiensEssentielById(@PathVariable Long id) throws BiensEssentielFoundException, BiensEssentielFoundException {
        BiensEssantielDto biensEssentiel = biensEssantielService.getBiensEssantielsById(id);
        return new ResponseEntity<>(biensEssentiel, HttpStatus.OK);
    }

    @PutMapping("/updateBiensEssentiel/{id}")
    @PreAuthorize("hasAuthority('PresidantAssociation')")
    public ResponseEntity<BiensEssantielDto> updateBiensEssentiel(@PathVariable Long id, @Valid @RequestBody BiensEssantielDto biensEssantielDto) {
        BiensEssantielDto updatedBiensEssentiel = biensEssantielService.updateBiensEssentiel(biensEssantielDto, id);
        return new ResponseEntity<>(updatedBiensEssentiel, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBiensEssentiel/{id}")
    @PreAuthorize("hasAuthority('PresidantAssociation')")
    public ResponseEntity<String> deleteBiensEssentiel(@PathVariable("id") Long id) {
        biensEssantielService.deleteBiensEssantiel(id);
        return ResponseEntity.ok("BiensEssentiel with id " +id+ " was deleted succes");
    }
}
