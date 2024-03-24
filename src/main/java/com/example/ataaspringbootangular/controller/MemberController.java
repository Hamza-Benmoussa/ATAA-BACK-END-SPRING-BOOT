package com.example.ataaspringbootangular.controller;

import com.example.ataaspringbootangular.dto.MemberDto;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;
import com.example.ataaspringbootangular.exception.except.MemberFoundException;
import com.example.ataaspringbootangular.service.IMemebreService;
import com.example.ataaspringbootangular.service.impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin("*")
public class MemberController {

    @Autowired
    private MemberServiceImpl memebreService;

    @PostMapping("/ajouterMember")
    @PreAuthorize("hasAuthority('PresidantAssociation')")
    public ResponseEntity<MemberDto> ajouterMember(@RequestBody @Valid MemberDto memberDto) throws AssociationFoundException {
        MemberDto savedMember = memebreService.ajouterMember(memberDto);
        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
    }
@GetMapping()
@PreAuthorize("hasAuthority('PresidantAssociation')")
public ResponseEntity<List<MemberDto>> getMembersCreatedByCurrentUser(Authentication authentication) {
    String currentUserEmail = authentication.getName();
    List<MemberDto> membersCreatedByCurrentUser = memebreService.getMembersCreatedByUser(currentUserEmail);
    return ResponseEntity.ok(membersCreatedByCurrentUser);
}
    @GetMapping("/count")
    public ResponseEntity<Long> getNumberOfMembers() {
        long count = memebreService.getNumberOfMembers();
        return ResponseEntity.ok(count);
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