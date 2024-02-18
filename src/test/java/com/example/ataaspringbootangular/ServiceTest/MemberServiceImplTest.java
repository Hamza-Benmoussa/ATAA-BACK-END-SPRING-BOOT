package com.example.ataaspringbootangular.ServiceTest;

import com.example.ataaspringbootangular.dto.*;
import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Enum.RoleMembers;
import com.example.ataaspringbootangular.entity.Enum.RoleUser;
import com.example.ataaspringbootangular.exception.except.MemberFoundException;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
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
    void setUp() throws ParseException {
        // Create Utilisateur
        utilisateurDto = new UtilisateurDto();
        utilisateurDto.setNomComplete("TestUser");
        utilisateurDto.setPassword("testPassword");
        utilisateurDto.setAddress("TestAddress");
        utilisateurDto.setTele("+123456789");
        utilisateurDto.setEmail("testuser@gmail.com");
        utilisateurDto.setDateNaissance(dateFormat.parse("1990-01-01"));
        utilisateurDto.setRoleUser(RoleUser.PresidantAssociation);
        utilisateurDto.setGenre(Genre.Male);
        utilisateurDto = iUtilisateurService.ajouterUtilisateur(utilisateurDto);

        // Create Ville
        villeDto = new VilleDto();
        villeDto.setNomVille("TestVille");
        villeDto = iVilleService.ajouterVille(villeDto);
        // Create Association
        associationDto = new AssociationDto();
        associationDto.setNomAssociation("TestAssociation");
        associationDto.setNomPresidantNomComplete(utilisateurDto.getNomComplete());
        associationDto.setNbrSerie("fghjkfghj");
        associationDto.setVilleNomVille(villeDto.getNomVille()); // Associate with the created Ville
        associationDto = iAssociationService.ajouterAssociation(associationDto);


        // Create Member with associated Utilisateur, Association, and Ville
        memberDto = new MemberDto();
        memberDto.setEmail("member-" + System.currentTimeMillis() + "@gmail.com");
        memberDto.setGenre(Genre.Male);
        memberDto.setTele("+212 646210352");
        memberDto.setAdress("Res essafa gh 13 imm 3");
        memberDto.setNomMembres("Member Test");
        memberDto.setDateNaissance(dateFormat.parse("2002-01-03"));
        memberDto.setRoleMembers(RoleMembers.Responsable_des_Partenariats);
        memberDto.setAssociationNomAssociation(associationDto.getNomAssociation()); // Associate with the created Association
        memberDto = iMemebreService.ajouterMember(memberDto);
    }

    @AfterEach
    void down() {
        // Cleanup: Delete created entities
        iMemebreService.deleteMember(memberDto.getId());
        iAssociationService.deleteAssociation(associationDto.getId());
        iUtilisateurService.deleteUtilisateur(utilisateurDto.getId());
        iVilleService.deleteVille(villeDto.getId());
    }

    @Rollback(value = false)
    @Test
    void ajouterMember() throws MemberFoundException {
        assertNotNull(memberDto, "Member not inserted");
        MemberDto retrievedMemberDto = iMemebreService.getMembersById(memberDto.getId());
        assertNotNull(retrievedMemberDto, "Member not found in the database");
    }

    @Rollback(value = false)
    @Test
    void getMembers() {
        List<MemberDto> memberDtos = iMemebreService.getMembers();
        assertNotNull(memberDtos, "List is empty");
    }

    @Rollback(value = false)
    @Test
    void getMembersById() throws MemberFoundException {
        MemberDto retrievedMemberDto = iMemebreService.getMembersById(memberDto.getId());
        assertNotNull(retrievedMemberDto, "Member Not found");
    }

    @Rollback(value = false)
    @Test
    void updateMember() throws ParseException {
        memberDto.setEmail("updated-email@gmail.com");
        MemberDto updateMemberDto = iMemebreService.updateMember(memberDto, memberDto.getId());
        assertEquals("updated-email@gmail.com", updateMemberDto.getEmail(), "Email should be updated");
    }

    @Rollback(value = false)
    @Test
    void deleteMember() throws MemberFoundException {
        Long memberId = memberDto.getId();
        assertNotNull(iMemebreService.getMembersById(memberId), "Member not found before deletion");
        iMemebreService.deleteMember(memberId);
    }
}
