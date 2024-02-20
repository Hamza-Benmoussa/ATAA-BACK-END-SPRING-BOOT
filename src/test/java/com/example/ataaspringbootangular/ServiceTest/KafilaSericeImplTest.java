package com.example.ataaspringbootangular.ServiceTest;

import com.example.ataaspringbootangular.dto.*;
import com.example.ataaspringbootangular.entity.BienKafila;
import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Enum.RoleUser;
import com.example.ataaspringbootangular.exception.except.*;
import com.example.ataaspringbootangular.service.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class KafilaSericeImplTest {

    @Autowired
    private IKafilaService iKafilaService;
    @Autowired
    private IAssociationService iAssociationService;
    @Autowired
    private IDowarService iDowarService;
    @Autowired
    private IVilleService iVilleService;
    @Autowired
    private IUtilisateurService iUtilisateurService;
    private DowarDto dowarDto;
    private AssociationDto associationDto;
    private KafilaDto kafilaDto;
    private VilleDto villeDto;
    private UtilisateurDto utilisateurDto;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @BeforeEach
    void setUp() throws UtilisateurFoundException, VilleFoundException, DowarFoundException, ParseException, AssociationFoundException, BiensEssentielFoundException {

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

        dowarDto = new DowarDto();
        dowarDto.setNomDowars("Tamri");
        dowarDto.setNmbrResidant(15);
        dowarDto.setVilleId(villeDto.getId());
        dowarDto =iDowarService.ajouterDowar(dowarDto);

        associationDto = new AssociationDto();
        associationDto.setNomAssociation("TestAssociation");
        associationDto.setNomPresidantId(utilisateurDto.getId());
        associationDto.setNbrSerie("koko"+ System.currentTimeMillis() +"koko");
        associationDto.setVilleId(villeDto.getId());
        associationDto = iAssociationService.ajouterAssociation(associationDto);

        kafilaDto = new KafilaDto();
        kafilaDto.setNomKfila("khayr");
        kafilaDto.setDateArrivee(LocalDate.now());
        kafilaDto.setAssociationId(associationDto.getId());
        kafilaDto.setDowarId(dowarDto.getId());
        kafilaDto.setDateDepart(dateFormat.parse("2002-01-03"));
        kafilaDto = iKafilaService.ajouterKafila(kafilaDto);
    }
    @AfterEach
    void down(){
        iKafilaService.deleteKafila(kafilaDto.getId());
        iAssociationService.deleteAssociation(kafilaDto.getId());
        iDowarService.deleteDowar(kafilaDto.getId());
    }
    @Test
    void ajouterKafila() throws KafilaFoundException {
        assertNotNull(kafilaDto, "Kafila not inserted");

//        // Vérifier que la liste bienKafilas n'est pas null avant d'itérer
//        if (kafilaDto.getBienKafilas() != null) {
//            for (BienKafila bienKafila : kafilaDto.getBienKafilas()) {
//                assertNotNull(bienKafila, "BienKafila should not be null");
//                assertNotNull(bienKafila.getBiensEssentiels(), "BiensEssentiels in BienKafila should not be null");
//            }
//        }

        KafilaDto retrievedKafilaDto = iKafilaService.getKafilasById(kafilaDto.getId());
        assertNotNull(retrievedKafilaDto, "Kafila not found in the database");
    }


    @Test
    void getKafilas() {
        List<KafilaDto> kafilaDtos = iKafilaService.getKafilas();
        assertNotNull(kafilaDtos, "List is empty");
    }

    @Test
    void getKafilasById() throws KafilaFoundException {
        KafilaDto retrievedKafilaDto = iKafilaService.getKafilasById(kafilaDto.getId());
        assertNotNull(retrievedKafilaDto, "Kafila Not found");
    }

    @Test
    void updateKafila() throws ParseException {
        kafilaDto.setNomKfila("khayrat");
        KafilaDto updateKafilaDto = iKafilaService.updateKafila(kafilaDto, kafilaDto.getId());
        assertEquals("khayrat", updateKafilaDto.getNomKfila(), "KafilaNom should be updated");
    }

    @Test
    void deleteKafila() throws KafilaFoundException {
        Long kafilaId = kafilaDto.getId();
        assertNotNull(iKafilaService.getKafilasById(kafilaId), "Kafila not found before deletion");
        iKafilaService.deleteKafila(kafilaId);
    }
}