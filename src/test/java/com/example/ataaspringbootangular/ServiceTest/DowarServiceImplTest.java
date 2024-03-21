package com.example.ataaspringbootangular.ServiceTest;

import com.example.ataaspringbootangular.dto.DowarDto;
import com.example.ataaspringbootangular.dto.MemberDto;
import com.example.ataaspringbootangular.dto.VilleDto;
import com.example.ataaspringbootangular.exception.except.DowarFoundException;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;
import com.example.ataaspringbootangular.service.IDowarService;
import com.example.ataaspringbootangular.service.IVilleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class DowarServiceImplTest {
    @Autowired
    private IDowarService iDowarService;
    @Autowired
    private IVilleService iVilleService;

    private DowarDto dowarDto;
    private VilleDto villeDto;

    @BeforeEach
    void setUp() throws DowarFoundException, VilleFoundException {
        villeDto = new VilleDto();
        villeDto.setNomVille("TestVille");
        villeDto = iVilleService.ajouterVille(villeDto);

        dowarDto = new DowarDto();
        dowarDto.setNomDowars("Tamri");
        dowarDto.setNmbrResidant(15);
        dowarDto.setVilleId(villeDto.getId());
        dowarDto =iDowarService.ajouterDowar(dowarDto);
    }

    @AfterEach
    void down(){
        iDowarService.deleteDowar(dowarDto.getId());
        iVilleService.deleteVille(villeDto.getId());
    }

    @Rollback(value = false)
    @Test
    void ajouterDowar() throws DowarFoundException {
        assertNotNull(dowarDto, "Dowar not inserted");
        DowarDto retrievedDowarDto = iDowarService.getDowarsById(dowarDto.getId());
        assertNotNull(retrievedDowarDto, "Dowar not found in the database");

    }

    @Rollback(value = false)
    @Test
    void getDowars() {
        List<DowarDto> dowarDtos = iDowarService.getDowars();
        assertNotNull(dowarDtos, "List is empty");
    }

    @Rollback(value = false)
    @Test
    void getDowarsById() throws DowarFoundException {
        DowarDto retrievedDowarDto = iDowarService.getDowarsById(dowarDto.getId());
        assertNotNull(retrievedDowarDto, "Member Not found");
    }

    @Rollback(value = false)
    @Test
    void updateDowar() throws VilleFoundException {
        dowarDto.setNomDowars("imzwagn");
        DowarDto updateDowarDto = iDowarService.updateDowar(dowarDto ,dowarDto.getId());
        assertEquals("imzwagn", updateDowarDto.getNomDowars(), "DowarNom should be updated");

    }

    @Rollback(value = false)
    @Test
    void deleteDowar() throws DowarFoundException {
        Long dowarId = dowarDto.getId();
        assertNotNull(iDowarService.getDowarsById(dowarId), "Dowar not found before deletion");
        iDowarService.deleteDowar(dowarId);
    }
}
