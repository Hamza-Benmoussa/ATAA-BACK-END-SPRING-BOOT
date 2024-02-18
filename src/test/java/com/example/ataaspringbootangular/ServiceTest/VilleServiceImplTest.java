package com.example.ataaspringbootangular.ServiceTest;

import com.example.ataaspringbootangular.dto.VilleDto;
import com.example.ataaspringbootangular.entity.Ville;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;
import com.example.ataaspringbootangular.service.IVilleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VilleServiceImplTest {

    @Autowired
    private IVilleService iVilleService;

    @Autowired
    private ModelMapper modelMapper;

   VilleDto villeDto;

    @BeforeEach
    void setUp() {
        // Create VilleDto for testing
        villeDto = new VilleDto();
        villeDto.setNomVille("TestVille");
        villeDto = iVilleService.ajouterVille(villeDto);
    }

    @AfterEach
    void tearDown() {
        // Cleanup: Delete created entities
        iVilleService.deleteVille(villeDto.getId());
    }

    @Test
    void ajouterVille() throws VilleFoundException {
        assertNotNull(villeDto, "Ville not inserted");
        VilleDto retrievedVilleDto = iVilleService.getVillesById(villeDto.getId());
        assertNotNull(retrievedVilleDto, "Ville not found in the database");
    }

    @Test
    void getVilles() {
        List<VilleDto> villeDtos = iVilleService.getVilles();
        assertNotNull(villeDtos, "List is empty");
    }

    @Test
    void getVillesById() throws VilleFoundException {
        VilleDto retrievedVilleDto = iVilleService.getVillesById(villeDto.getId());
        assertNotNull(retrievedVilleDto, "Ville Not found");
    }

    @Test
    void updateVille() {
        villeDto.setNomVille("UpdatedTestVille");
        VilleDto updatedVilleDto = iVilleService.updateVille(villeDto, villeDto.getId());
        assertEquals("UpdatedTestVille", updatedVilleDto.getNomVille(), "NomVille should be updated");
    }

    @Test
    void deleteVille() throws VilleFoundException {
        Long villeId = villeDto.getId();
        assertNotNull(iVilleService.getVillesById(villeId), "Ville not found before deletion");
        iVilleService.deleteVille(villeId);
    }
}
