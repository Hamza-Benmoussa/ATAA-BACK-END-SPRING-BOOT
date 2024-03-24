package com.example.ataaspringbootangular.controller;

import com.example.ataaspringbootangular.dto.VilleDto;
import com.example.ataaspringbootangular.entity.Dowar;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;
import com.example.ataaspringbootangular.service.IVilleService;
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
    private IVilleService villeService;

    @PostMapping("/ajouterVille")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<VilleDto> ajouterVille(@RequestBody @Valid VilleDto villeDto) {
        VilleDto savedVille = villeService.ajouterVille(villeDto);
        return new ResponseEntity<>(savedVille, HttpStatus.CREATED);
    }
    @GetMapping("/getDowarsForVille/{nomVille}")
    @PreAuthorize("hasAuthority('PresidantAssociation')")
    public ResponseEntity<List<Dowar>> getDowarsForVille(@PathVariable String nomVille) {
        List<Dowar> dowars = villeService.getDowarsForVille(nomVille);
        return new ResponseEntity<>(dowars, HttpStatus.OK);
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('AdminApp','PresidantAssociation')")
    public ResponseEntity<List<VilleDto>> getVilles() {
        List<VilleDto> villes = villeService.getVilles();
        return new ResponseEntity<>(villes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<VilleDto> getVilleById(@PathVariable Long id) throws VilleFoundException, VilleFoundException {
        VilleDto ville = villeService.getVillesById(id);
        return new ResponseEntity<>(ville, HttpStatus.OK);
    }

    @PutMapping("/updateVille/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<VilleDto> updateVille(@PathVariable Long id, @Valid @RequestBody VilleDto villeDto) {
        VilleDto updatedVille = villeService.updateVille(villeDto, id);
        return new ResponseEntity<>(updatedVille, HttpStatus.OK);
    }

    @DeleteMapping("/deleteVille/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<String> deleteVille(@PathVariable("id") Long id) {
        villeService.deleteVille(id);
        return ResponseEntity.ok("Ville with id " +id+ " was deleted succes");
    }
}
