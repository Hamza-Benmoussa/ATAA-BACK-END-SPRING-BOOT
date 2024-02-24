package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.dto.VilleDto;
import com.example.ataaspringbootangular.entity.Ville;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;
import com.example.ataaspringbootangular.exception.except.VilleFoundException;
import com.example.ataaspringbootangular.repository.IVilleRepository;
import com.example.ataaspringbootangular.service.IVilleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VilleServiceImpl implements IVilleService {
    @Autowired
    private IVilleRepository iVilleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public VilleDto ajouterVille(VilleDto villeDto) {
        Ville ville = modelMapper.map(villeDto , Ville.class);
        Ville saveVille = iVilleRepository.save(ville);
        return modelMapper.map(saveVille , VilleDto.class);
    }

    @Override
    public Ville getByNomVille(String nomVille) {
        return iVilleRepository.findByNomVilleAndDeletedFalse(nomVille);
    }

    @Override
    public List<VilleDto> getVilles() {
        List<Ville> villes = iVilleRepository.findByDeletedFalse();
        return villes.stream()
                .map(ville -> modelMapper.map(ville , VilleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public VilleDto getVillesById(Long id) throws VilleFoundException {
        return iVilleRepository.findByIdAndDeletedFalse(id)
                .map(ville -> modelMapper.map(ville , VilleDto.class))
                .orElseThrow(() -> new VilleFoundException("Ville Not found with id = " + id));
    }

    @Override
    public VilleDto updateVille(VilleDto villeDto, Long id) {
        Ville existingVille = iVilleRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (existingVille != null) {
            existingVille.setNomVille(villeDto.getNomVille());
            Ville updateVille = iVilleRepository.save(existingVille);
            updateVille.setId(id);
            return modelMapper.map(updateVille, VilleDto.class);
        }
        return null;
    }

    @Override
    public void deleteVille(Long id) {
        Ville ville = iVilleRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (ville != null){
            ville.setDeleted(true);
            iVilleRepository.save(ville);
        }
    }
}
