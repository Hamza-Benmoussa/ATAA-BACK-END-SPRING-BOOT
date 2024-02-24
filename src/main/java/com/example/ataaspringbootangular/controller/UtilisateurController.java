package com.example.ataaspringbootangular.controller;

import com.example.ataaspringbootangular.dto.UtilisateurDto;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;
import com.example.ataaspringbootangular.service.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private IUtilisateurService utilisateurService;

    @PostMapping("/ajouterUtilisateur")
    public ResponseEntity<UtilisateurDto> ajouterUtilisateur(@RequestBody @Valid UtilisateurDto utilisateurDto) {
        UtilisateurDto savedUtilisateur = utilisateurService.ajouterUtilisateur(utilisateurDto);
        return new ResponseEntity<>(savedUtilisateur, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurDto>> getUtilisateurs() {
        List<UtilisateurDto> utilisateurs = utilisateurService.getUtilisateurs();
        return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable Long id) throws UtilisateurFoundException {
        UtilisateurDto utilisateur = utilisateurService.getUtilisateursById(id);
        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
    }

    @PutMapping("/updateUtilisateur/{id}")
    public ResponseEntity<UtilisateurDto> updateUtilisateur(@PathVariable Long id, @Valid @RequestBody UtilisateurDto utilisateurDto) throws ParseException {
        UtilisateurDto updatedUtilisateur = utilisateurService.updateUtilisateur(utilisateurDto, id);
        return new ResponseEntity<>(updatedUtilisateur, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUtilisateur/{id}")
    public ResponseEntity<String> deleteUtilisateur(@PathVariable("id") Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.ok("Utilisateur with id " +id+ " was deleted succes");
    }
}
