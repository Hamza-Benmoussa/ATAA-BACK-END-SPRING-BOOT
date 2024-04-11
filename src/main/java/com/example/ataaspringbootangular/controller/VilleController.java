package com.example.ataaspringbootangular.controller;

import com.example.ataaspringbootangular.dto.VilleDto;
import com.example.ataaspringbootangular.entity.Dowar;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;
import com.example.ataaspringbootangular.service.IVilleService;
import com.example.ataaspringbootangular.service.impl.VilleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/villes")
@CrossOrigin("*")
public class VilleController {
    @Autowired
    private VilleServiceImpl villeService;

    @PostMapping("/ajouterVille")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<String> ajouterVille(@RequestBody @Valid VilleDto villeDto) {
        try {
            VilleDto savedVille = villeService.ajouterVille(villeDto);
            return new ResponseEntity<>("000", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("001", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateVille/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<String> updateVille(@PathVariable Long id, @Valid @RequestBody VilleDto villeDto) {
        try {
            VilleDto updatedVille = villeService.updateVille(villeDto, id);
            return new ResponseEntity<>("000", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("001", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteVille/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<String> deleteVille(@PathVariable("id") Long id) {
        try {
            villeService.deleteVille(id);
            return new ResponseEntity<>("000", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("001", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('AdminApp','PresidantAssociation')")
    public ResponseEntity<List<VilleDto>> getVilles() {
        List<VilleDto> villes = villeService.getVilles();
        return new ResponseEntity<>(villes, HttpStatus.OK);
    }
    @GetMapping("/villesWithDowarsAndArrivedKafilas")
    public ResponseEntity<List<VilleDto>> getVillesWithDowarsAndArrivedKafilas() {
        List<VilleDto> villesWithDowarsAndArrivedKafilas = villeService.getVillesWithDowarsAndArrivedKafilas();
        return new ResponseEntity<>(villesWithDowarsAndArrivedKafilas, HttpStatus.OK);
    }
    @GetMapping("/count")
    public ResponseEntity<Long> getNumberOfVilles() {
        long count = villeService.getNumberOfVilles();
        return ResponseEntity.ok(count);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<VilleDto> getVilleById(@PathVariable Long id) throws VilleFoundException, VilleFoundException {
        VilleDto ville = villeService.getVillesById(id);
        return new ResponseEntity<>(ville, HttpStatus.OK);
    }
}
