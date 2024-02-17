package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.dto.DowarDto;
import com.example.ataaspringbootangular.entity.Dowar;
import com.example.ataaspringbootangular.repository.IDowarsRepository;
import com.example.ataaspringbootangular.service.IDowarService;
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
    @Override
    public DowarDto ajouterDowar(DowarDto dowarDto) {
        Dowar dowar = modelMapper.map(dowarDto , Dowar.class);
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
    public DowarDto getDowarsById(Long id) {
        return iDowarsRepository.findByIdAndDeletedFalse(id)
                .map(dowar -> modelMapper.map(dowar , DowarDto.class))
                .orElse(null);
    }

    @Override
    public DowarDto updateDowar(DowarDto dowarDto, Long id) {
        Dowar existingDowar = iDowarsRepository.findByIdAndDeletedFalse(id).orElse(null);
        existingDowar.setNomDowars(dowarDto.getNomDowars());
        existingDowar.setNmbrResidant(dowarDto.getNmbrResidant());
        Dowar updateDowar = iDowarsRepository.save(existingDowar);
        updateDowar.setIdDowars(id);
        return modelMapper.map(updateDowar, DowarDto.class);
    }

    @Override
    public void deleteDowar(Long id) {
        Dowar dowar = iDowarsRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (dowar !=null){
            dowar.setDeleted(true);
            iDowarsRepository.save(dowar);
        }
    }
}
