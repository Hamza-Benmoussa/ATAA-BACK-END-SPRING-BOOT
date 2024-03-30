package com.example.ataaspringbootangular.controller;

import com.example.ataaspringbootangular.dto.UtilisateurDto;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;
import com.example.ataaspringbootangular.service.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private IUtilisateurService utilisateurService;

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UtilisateurDto>> getUsersByRole(@PathVariable String role) {
        List<UtilisateurDto> users = utilisateurService.getUsersWithRole(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/ajouterUtilisateur")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<String> ajouterUtilisateur(@RequestBody @Valid UtilisateurDto utilisateurDto) {
        try {
            UtilisateurDto savedUtilisateur = utilisateurService.ajouterUtilisateur(utilisateurDto);
            return new ResponseEntity<>("000", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("001", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateUtilisateur/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<String> updateUtilisateur(@PathVariable Long id, @Valid @RequestBody UtilisateurDto utilisateurDto) throws ParseException {
        try {
            UtilisateurDto updatedUtilisateur = utilisateurService.updateUtilisateur(utilisateurDto, id);
            return new ResponseEntity<>("000", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("001", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/count")
    public ResponseEntity<Long> getNumberOfUtilisateurs() {
        long count = utilisateurService.getNumberOfUtilisatuers();
        return ResponseEntity.ok(count);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<List<UtilisateurDto>> getUtilisateurs() {
        List<UtilisateurDto> utilisateurs = utilisateurService.getUtilisateurs();
        return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable Long id) throws UtilisateurFoundException {
        UtilisateurDto utilisateur = utilisateurService.getUtilisateursById(id);
        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
    }



    @DeleteMapping("/deleteUtilisateur/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<String> deleteUtilisateur(@PathVariable("id") Long id) {
        try {
            utilisateurService.deleteUtilisateur(id);
            return new ResponseEntity<>("000", HttpStatus.OK); // Return 000 for success
        } catch (Exception e) {
            return new ResponseEntity<>("001", HttpStatus.INTERNAL_SERVER_ERROR); // Return 001 for failure
        }
    }
}
