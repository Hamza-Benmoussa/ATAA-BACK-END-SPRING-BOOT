package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.dto.DowarDto;
import com.example.ataaspringbootangular.dto.VilleDto;
import com.example.ataaspringbootangular.entity.Dowar;
import com.example.ataaspringbootangular.entity.Kafila;
import com.example.ataaspringbootangular.entity.Ville;
import com.example.ataaspringbootangular.exception.except.DowarFoundException;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;
import com.example.ataaspringbootangular.repository.IDowarsRepository;
import com.example.ataaspringbootangular.service.IDowarService;
import com.example.ataaspringbootangular.service.IVilleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DowarServiceImpl implements IDowarService {
    @Autowired
    private IDowarsRepository iDowarsRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IVilleService iVilleService;
    @Override
    public DowarDto ajouterDowar(DowarDto dowarDto) throws VilleFoundException {
        VilleDto villeDto = iVilleService.getVillesById(dowarDto.getVilleId());
        Ville ville = modelMapper.map(villeDto , Ville.class);
        Dowar dowar = modelMapper.map(dowarDto , Dowar.class);
        dowar.setVille(ville);
        Dowar saveDowar = iDowarsRepository.save(dowar);
        return modelMapper.map(saveDowar , DowarDto.class);
    }

    @Override
    public List<DowarDto> getDowars() {
        List<Dowar> dowars = iDowarsRepository.findByDeletedFalse();
        return dowars.stream()
                .map(dowar -> modelMapper.map(dowar , DowarDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DowarDto getDowarsById(Long id) throws DowarFoundException {
        return iDowarsRepository.findByIdAndDeletedFalse(id)
                .map(dowar -> modelMapper.map(dowar , DowarDto.class))
                .orElseThrow(() -> new DowarFoundException("Dowar Not found with id = " + id));
    }

    @Override
    public DowarDto updateDowar(DowarDto dowarDto, Long id) throws VilleFoundException {
        Dowar existingDowar = iDowarsRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (existingDowar != null){
        existingDowar.setNomDowars(dowarDto.getNomDowars());
        existingDowar.setNmbrResidant(dowarDto.getNmbrResidant());
            VilleDto ville = iVilleService.getVillesById(dowarDto.getVilleId());
            existingDowar.setVille(modelMapper.map(ville , Ville.class));
        Dowar updateDowar = iDowarsRepository.save(existingDowar);
        updateDowar.setId(id);
        return modelMapper.map(updateDowar, DowarDto.class);
        }
        return null;
    }
    public long getNumberOfDowars() {
        return iDowarsRepository.count();
    }
    @Override
    public void deleteDowar(Long id) {
        Dowar dowar = iDowarsRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (dowar !=null){
            dowar.setDeleted(true);
            iDowarsRepository.save(dowar);
        }
    }

    @Override
    public int calculateTotalArrivedKafilasForDowar(Long dowarId) throws DowarFoundException {
        Dowar dowar = iDowarsRepository.findByIdAndDeletedFalse(dowarId)
                .orElseThrow(() -> new DowarFoundException("Dowar Not found with id = " + dowarId));

        int totalArrivedKafilas = 0;

        if (dowar.getKafilas() != null) {
            for (Kafila kafila : dowar.getKafilas()) {
                if (kafila.isArrivedKafila()) {
                    if (kafila.getAssociation() != null) {
                        totalArrivedKafilas++;
                    }
                }
            }
        }

        return totalArrivedKafilas;
    }
}
