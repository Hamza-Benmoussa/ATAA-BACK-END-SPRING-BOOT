package com.example.ataaspringbootangular.controller;

import com.example.ataaspringbootangular.dto.DowarDto;
import com.example.ataaspringbootangular.entity.Dowar;
import com.example.ataaspringbootangular.exception.except.DowarFoundException;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;
import com.example.ataaspringbootangular.service.IDowarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/dowars")
@CrossOrigin("*")
public class DowarController {

    @Autowired
    private IDowarService dowarService;

    @PostMapping("/ajouterDowar")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<String> ajouterDowar(@RequestBody @Valid DowarDto dowarDto) throws VilleFoundException, DowarFoundException {
        try {
            DowarDto savedDowar = dowarService.ajouterDowar(dowarDto);
            return new ResponseEntity<>("000", HttpStatus.CREATED); // Return 000 for success
        } catch (Exception e) {
            return new ResponseEntity<>("001", HttpStatus.INTERNAL_SERVER_ERROR); // Return 001 for failure
        }
    }

    @PutMapping("/updateDowar/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<String> updateDowar(@PathVariable Long id, @Valid @RequestBody DowarDto dowarDto) throws VilleFoundException {
        try {
            DowarDto updatedDowar = dowarService.updateDowar(dowarDto, id);
            return new ResponseEntity<>("000", HttpStatus.OK); // Return 000 for success
        } catch (Exception e) {
            return new ResponseEntity<>("001", HttpStatus.INTERNAL_SERVER_ERROR); // Return 001 for failure
        }
    }

    @DeleteMapping("/deleteDowar/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<String> deleteDowar(@PathVariable("id") Long id) {
        try {
            dowarService.deleteDowar(id);
            return new ResponseEntity<>("000", HttpStatus.OK); // Return 000 for success
        } catch (Exception e) {
            return new ResponseEntity<>("001", HttpStatus.INTERNAL_SERVER_ERROR); // Return 001 for failure
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('AdminApp','PresidantAssociation')")
    public ResponseEntity<List<DowarDto>> getDowars() {
        List<DowarDto> dowars = dowarService.getDowars();
        return new ResponseEntity<>(dowars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('AdminApp')")
    public ResponseEntity<DowarDto> getDowarById(@PathVariable Long id) throws DowarFoundException {
        DowarDto dowar = dowarService.getDowarsById(id);
        return new ResponseEntity<>(dowar, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getNumberOfDowars() {
        long count = dowarService.getNumberOfDowars();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/totalKafilas/{dowarId}")
    @PreAuthorize("hasAuthority('PresidantAssociation')")
    public ResponseEntity<Integer> getTotalKafilasForDowar(@PathVariable Long dowarId) throws DowarFoundException {
        int totalKafilas = dowarService.calculateTotalArrivedKafilasForDowar(dowarId);
        return new ResponseEntity<>(totalKafilas, HttpStatus.OK);
    }
    @GetMapping("/villes/{villeId}/dowars")
    public ResponseEntity<List<Dowar>> getDowarsByVille(@PathVariable Long villeId) {
        List<Dowar> dowars = dowarService.getDowarsByVille(villeId);
        return new ResponseEntity<>(dowars, HttpStatus.OK);
    }

}
