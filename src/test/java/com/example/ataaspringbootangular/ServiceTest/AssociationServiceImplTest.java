package com.example.ataaspringbootangular.ServiceTest;

import com.example.ataaspringbootangular.dto.*;
import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Enum.RoleUser;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;
import com.example.ataaspringbootangular.service.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AssociationServiceImplTest {

    @Autowired
    private IAssociationService iAssociationService;
    @Autowired
    private IVilleService iVilleService;
    @Autowired
    private IUtilisateurService iUtilisateurService;
    private DowarDto dowarDto;
    private AssociationDto associationDto;
    private VilleDto villeDto;
    private UtilisateurDto utilisateurDto;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @BeforeEach
    void setUp() throws ParseException, UtilisateurFoundException, VilleFoundException {
        utilisateurDto = new UtilisateurDto();
        utilisateurDto.setNomComplete("TestUser");
        utilisateurDto.setPassword("testPassword");
        utilisateurDto.setAddress("TestAddress");
        utilisateurDto.setTele("+123456789");
        utilisateurDto.setEmail("member-" + System.currentTimeMillis() + "@gmail.com");
        utilisateurDto.setDateNaissance(LocalDate.now());
        utilisateurDto.setRoleUser(RoleUser.PresidantAssociation);
        utilisateurDto.setGenre(Genre.Male);
        utilisateurDto = iUtilisateurService.ajouterUtilisateur(utilisateurDto);

        villeDto = new VilleDto();
        villeDto.setNomVille("TestVille");
        villeDto = iVilleService.ajouterVille(villeDto);

        associationDto = new AssociationDto();
        associationDto.setNomAssociation("TestAssociation");
        associationDto.setNomPresidantId(utilisateurDto.getId());
        associationDto.setNbrSerie("koko"+ System.currentTimeMillis() +"koko");
        associationDto.setVilleId(villeDto.getId());
        associationDto = iAssociationService.ajouterAssociation(associationDto);
    }

    @AfterEach
    void down(){
        iAssociationService.deleteAssociation(associationDto.getId());
        iVilleService.deleteVille(villeDto.getId());
        iUtilisateurService.deleteUtilisateur(utilisateurDto.getId());
    }

    @Test
    void ajouterAssociation() throws AssociationFoundException {
        assertNotNull(associationDto, "association not inserted");
        AssociationDto retrievedAssociationDto = iAssociationService.getAssociationsById(associationDto.getId());
        assertNotNull(retrievedAssociationDto, "Kafila not found in the database");
    }

    @Test
    void getAssociations() {
        List<AssociationDto> associationDtos = iAssociationService.getAssociations();
        assertNotNull(associationDtos, "List is empty");
    }

    @Test
    void getAssociationsById() throws AssociationFoundException {
        AssociationDto retrievedAssociationDto = iAssociationService.getAssociationsById(associationDto.getId());
        assertNotNull(retrievedAssociationDto, "Association Not found");
    }

    @Test
    void updateAssociation() {
        associationDto.setNbrSerie("kakakakaka");
        AssociationDto updateAssociation = iAssociationService.updateAssociation(associationDto, associationDto.getId());
        assertEquals("kakakakaka", updateAssociation.getNbrSerie(), "Association should be updated");
    }

    @Test
    void deleteAssociation() throws AssociationFoundException {
        Long associationDtoId = associationDto.getId();
        assertNotNull(iAssociationService.getAssociationsById(associationDtoId), "Association not found before deletion");
        iAssociationService.deleteAssociation(associationDtoId);
    }
}