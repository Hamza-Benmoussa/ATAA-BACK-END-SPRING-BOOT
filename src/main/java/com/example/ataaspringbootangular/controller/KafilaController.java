package com.example.ataaspringbootangular.controller;

import com.example.ataaspringbootangular.dto.KafilaDto;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;
import com.example.ataaspringbootangular.exception.except.BiensEssentielFoundException;
import com.example.ataaspringbootangular.exception.except.DowarFoundException;
import com.example.ataaspringbootangular.exception.except.KafilaFoundException;
import com.example.ataaspringbootangular.service.IKafilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/kafilas")
public class KafilaController {

    @Autowired
    private IKafilaService kafilaService;

    @PostMapping("/ajouterKafila")
    public ResponseEntity<KafilaDto> ajouterKafila(@RequestBody @Valid KafilaDto kafilaDto) throws DowarFoundException, AssociationFoundException, BiensEssentielFoundException, AssociationFoundException, BiensEssentielFoundException, DowarFoundException {
        KafilaDto savedKafila = kafilaService.ajouterKafila(kafilaDto);
        return new ResponseEntity<>(savedKafila, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<KafilaDto>> getKafilas() {
        List<KafilaDto> kafilas = kafilaService.getKafilas();
        return new ResponseEntity<>(kafilas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KafilaDto> getKafilaById(@PathVariable Long id) throws KafilaFoundException, KafilaFoundException {
        KafilaDto kafila = kafilaService.getKafilasById(id);
        return new ResponseEntity<>(kafila, HttpStatus.OK);
    }

    @PutMapping("/updateKafila{id}")
    public ResponseEntity<String> deletkafila(@PathVariable("id") Long id) {
        kafilaService.deleteKafila(id);
        return ResponseEntity.ok("Kafila with id " +id+ " was deleted succes");
    }

    @DeleteMapping("/deleteMember/{id}")
    public ResponseEntity<String> deleteKafila(@PathVariable Long id) {
        kafilaService.deleteKafila(id);
        return ResponseEntity.ok("Member with id " +id+ " was deleted succes");
    }
}
