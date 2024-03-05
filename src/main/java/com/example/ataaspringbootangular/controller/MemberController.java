package com.example.ataaspringbootangular.controller;

import com.example.ataaspringbootangular.dto.MemberDto;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;
import com.example.ataaspringbootangular.exception.except.MemberFoundException;
import com.example.ataaspringbootangular.service.IMemebreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin("*")
public class MemberController {

    @Autowired
    private IMemebreService memebreService;

    @PostMapping("/ajouterMember")
    @PreAuthorize("hasAuthority('PresidantAssociation')")
    public ResponseEntity<MemberDto> ajouterMember(@RequestBody @Valid MemberDto memberDto) throws AssociationFoundException, AssociationFoundException {
        MemberDto savedMember = memebreService.ajouterMember(memberDto);
        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
    }
    @GetMapping("/memberByPres/{presidentAssociationId}")
    @PreAuthorize("hasAuthority('PresidantAssociation')")
    public ResponseEntity<List<MemberDto>> getAllMembersByPresidentAssociationId(@PathVariable Long presidentAssociationId) {
        List<MemberDto> members = memebreService.getAllMembersByPresidentAssociationId(presidentAssociationId);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PresidantAssociation')")
    public ResponseEntity<MemberDto> getMemberById(@PathVariable Long id) throws MemberFoundException, MemberFoundException {
        MemberDto member = memebreService.getMembersById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PutMapping("/updateMember/{id}")
    @PreAuthorize("hasAuthority('PresidantAssociation')")
    public ResponseEntity<MemberDto> updateMember(@PathVariable Long id, @Valid @RequestBody MemberDto memberDto) throws ParseException, ParseException {
        MemberDto updatedMember = memebreService.updateMember(memberDto, id);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    @DeleteMapping("/deleteMember/{id}")
    @PreAuthorize("hasAuthority('PresidantAssociation')")
    public ResponseEntity<String> deleteMember(@PathVariable("id") Long id) {
        memebreService.deleteMember(id);
        return ResponseEntity.ok("Member with id " +id+ " was deleted succes");
    }
}
