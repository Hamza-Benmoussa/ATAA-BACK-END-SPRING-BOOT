package com.example.ataaspringbootangular.ServiceTest;

import com.example.ataaspringbootangular.dto.*;
import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Enum.RoleMembers;
import com.example.ataaspringbootangular.entity.Enum.RoleUser;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;
import com.example.ataaspringbootangular.exception.except.MemberFoundException;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;
import com.example.ataaspringbootangular.service.IAssociationService;
import com.example.ataaspringbootangular.service.IMemebreService;
import com.example.ataaspringbootangular.service.IUtilisateurService;
import com.example.ataaspringbootangular.service.IVilleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private IMemebreService iMemebreService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IUtilisateurService iUtilisateurService;

    @Autowired
    private IAssociationService iAssociationService;

    @Autowired
    private IVilleService iVilleService;

    private MemberDto memberDto;
    private UtilisateurDto utilisateurDto;
    private AssociationDto associationDto;
    private VilleDto villeDto;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() throws ParseException, UtilisateurFoundException, VilleFoundException, AssociationFoundException {
        // Create Utilisateur
        utilisateurDto = new UtilisateurDto();
        utilisateurDto.setNomComplete("TestUser");
        utilisateurDto.setPassword("testPassword");
        utilisateurDto.setAddress("TestAddress");
        utilisateurDto.setTele("+123456789");
        utilisateurDto.setEmail("test@gmail.com");
        utilisateurDto.setDateNaissance(dateFormat.parse("1990-01-01"));
        utilisateurDto.setRoleUser(RoleUser.PresidantAssociation);
        utilisateurDto.setGenre(Genre.Male);
        utilisateurDto = iUtilisateurService.ajouterUtilisateur(utilisateurDto);
        Authentication authentication = new UsernamePasswordAuthenticationToken("test@gmail.com", "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);


        // Create Ville
        villeDto = new VilleDto();
        villeDto.setNomVille("TestVille");
        villeDto = iVilleService.ajouterVille(villeDto);
        // Create Association
        associationDto = new AssociationDto();
        associationDto.setNomAssociation("TestAssociation");
        associationDto.setNomPresidantId(utilisateurDto.getId());
        associationDto.setNbrSerie("fghjkfghj");
        associationDto.setVilleId(villeDto.getId()); // Associate with the created Ville
        associationDto = iAssociationService.ajouterAssociation(associationDto);


        // Create Member with associated Utilisateur, Association, and Ville
        memberDto = new MemberDto();
        memberDto.setEmail("member-" + System.currentTimeMillis() + "@gmail.com");
        memberDto.setGenre(Genre.Male);
        memberDto.setTele("+212 646210352");
        memberDto.setAddress("Res essafa gh 13 imm 3");
        memberDto.setNomMembres("Member Test");
        memberDto.setDateNaissance(dateFormat.parse("2002-01-03"));
        memberDto.setRoleMembers(RoleMembers.Responsable_des_Partenariats);
//        memberDto.setAssociationId(associationDto.getId()); // Associate with the created Association
        memberDto = iMemebreService.ajouterMember(memberDto);
    }

    @AfterEach
    void down() {
        // Cleanup: Delete created entities
        iMemebreService.deleteMember(memberDto.getId());
        iAssociationService.deleteAssociation(associationDto.getId());
        iUtilisateurService.deleteUtilisateur(utilisateurDto.getId());
        iVilleService.deleteVille(villeDto.getId());
        SecurityContextHolder.clearContext();
    }

    @Test
    void ajouterMember() throws MemberFoundException {
        assertNotNull(memberDto, "Member not inserted");
        MemberDto retrievedMemberDto = iMemebreService.getMembersById(memberDto.getId());
        assertNotNull(retrievedMemberDto, "Member not found in the database");
    }

    @Test
    void getMembersById() throws MemberFoundException {
        MemberDto retrievedMemberDto = iMemebreService.getMembersById(memberDto.getId());
        assertNotNull(retrievedMemberDto, "Member Not found");
    }

    @Test
    void updateMember() throws ParseException {
        memberDto.setEmail("updated-email@gmail.com");
        MemberDto updateMemberDto = iMemebreService.updateMember(memberDto, memberDto.getId());
        assertEquals("updated-email@gmail.com", updateMemberDto.getEmail(), "Email should be updated");
    }

    @Test
    void deleteMember() throws MemberFoundException {
        Long memberId = memberDto.getId();
        assertNotNull(iMemebreService.getMembersById(memberId), "Member not found before deletion");
        iMemebreService.deleteMember(memberId);
    }
}