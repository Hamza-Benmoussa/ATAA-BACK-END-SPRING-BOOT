package com.example.ataaspringbootangular.ServiceTest;

import com.example.ataaspringbootangular.dto.*;
import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Enum.RoleUser;
import com.example.ataaspringbootangular.exception.except.*;
import com.example.ataaspringbootangular.service.IAssociationService;
import com.example.ataaspringbootangular.service.IBiensEssantielService;
import com.example.ataaspringbootangular.service.IUtilisateurService;
import com.example.ataaspringbootangular.service.IVilleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BiensEssantielImplTest {

    @Autowired
    private IBiensEssantielService iBiensEssantielService;
    @Autowired
    private IUtilisateurService iUtilisateurService;
    @Autowired
    private IVilleService iVilleService;
    @Autowired
    private IAssociationService iAssociationService;

    private BiensEssantielDto biensEssantielDto;
    private AssociationDto associationDto;
    private UtilisateurDto utilisateurDto;
    private VilleDto villeDto;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() throws ParseException, UtilisateurFoundException, VilleFoundException, AssociationFoundException {
        utilisateurDto = new UtilisateurDto();
        utilisateurDto.setNomComplete("TestUser");
        utilisateurDto.setPassword("testPassword");
        utilisateurDto.setAddress("TestAddress");
        utilisateurDto.setTele("+123456789");
        utilisateurDto.setEmail("member-" + System.currentTimeMillis() + "@gmail.com");
        utilisateurDto.setDateNaissance(dateFormat.parse("1990-01-01"));
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

        biensEssantielDto = new BiensEssantielDto();
        biensEssantielDto.setNomBiens("Zit");
        biensEssantielDto.setQuantity(12);
        biensEssantielDto.setAssociationId(associationDto.getId());
        biensEssantielDto = iBiensEssantielService.ajouterBiensEssantiel(biensEssantielDto);

    }

    @AfterEach
    void down(){
        iAssociationService.deleteAssociation(associationDto.getId());
        iBiensEssantielService.deleteBiensEssantiel(biensEssantielDto.getId());
        iUtilisateurService.deleteUtilisateur(utilisateurDto.getId());
        iVilleService.deleteVille(villeDto.getId());
    }
    @Test
    void ajouterBiensEssantiel() throws BiensEssentielFoundException {
        assertNotNull(biensEssantielDto, "biensEssentiel not inserted");
        BiensEssantielDto retrievedBiensEssentielDto = iBiensEssantielService.getBiensEssantielsById(biensEssantielDto.getId());
        assertNotNull(retrievedBiensEssentielDto, "biensEssentiel not found in the database");
    }

    @Test
    void getBiensEssantiels() {
        List<BiensEssantielDto> bienKafilaDtos = iBiensEssantielService.getBiensEssantiels();
        assertNotNull(bienKafilaDtos, "List is empty");
    }

    @Test
    void getAllBienEseentielsByPresidentAssociationId() throws AssociationFoundException, MemberFoundException {
        Long presidentAssociationId = 123L;

        List<BiensEssantielDto> biensEssentielDtos = iBiensEssantielService.getAllBiensEssentilesByPresidentAssociationId(presidentAssociationId);

        // Assertions
        assertNotNull(biensEssentielDtos, "List is empty");
    }
    @Test
    void getBiensEssantielsById() throws BiensEssentielFoundException {
        BiensEssantielDto retrievedBiensEssentielDto = iBiensEssantielService.getBiensEssantielsById(biensEssantielDto.getId());
        assertNotNull(retrievedBiensEssentielDto, "biensEssentiel Not found");
    }

    @Test
    void updateBiensEssentiel() {
        biensEssantielDto.setNomBiens("sucre");
        BiensEssantielDto updateBiensEssentiel = iBiensEssantielService.updateBiensEssentiel(biensEssantielDto, biensEssantielDto.getId());
        assertEquals("sucre", updateBiensEssentiel.getNomBiens(), "biensNom should be updated");
    }

    @Test
    void deleteBiensEssantiel() throws BiensEssentielFoundException {
        Long biensEssentielId = biensEssantielDto.getId();
        assertNotNull(iBiensEssantielService.getBiensEssantielsById(biensEssentielId), "BiensEssentiel not found before deletion");
        iBiensEssantielService.deleteBiensEssantiel(biensEssentielId);
    }
}