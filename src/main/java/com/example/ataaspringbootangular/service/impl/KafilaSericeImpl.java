    package com.example.ataaspringbootangular.service.impl;

    import com.example.ataaspringbootangular.dto.*;
    import com.example.ataaspringbootangular.entity.*;
    import com.example.ataaspringbootangular.exception.except.*;
    import com.example.ataaspringbootangular.repository.IBienKafilaRepository;
    import com.example.ataaspringbootangular.repository.IBiensEssantielsRepository;
    import com.example.ataaspringbootangular.repository.IDowarsRepository;
    import com.example.ataaspringbootangular.repository.IKafilaRepository;
    import com.example.ataaspringbootangular.service.*;
    import org.modelmapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    @Transactional
    public class KafilaSericeImpl implements IKafilaService {
        @Autowired
        private IKafilaRepository iKafilaRepository;
        @Autowired
        private ModelMapper modelMapper;
        @Autowired
        private IDowarsRepository iDowarsRepository;
        @Autowired
        private IDowarService iDowarService;
        @Autowired
        private IAssociationService iAssociationService;
        @Autowired
        private IBienKafilaService iBienKafilaService;
        @Autowired
        private IBienKafilaRepository iBienKafilaRepository;
        @Autowired
        private IBiensEssantielsRepository iBiensEssantielsRepository;
        @Autowired
        private IBiensEssantielService iBiensEssantielService;
        @Override
        public KafilaDto ajouterKafila(KafilaDto kafilaDto) throws DowarFoundException, AssociationFoundException, BiensEssentielFoundException {
            String createdByEmail = SecurityContextHolder.getContext().getAuthentication().getName();

            Association association = iAssociationService.getAssociationByPresidentEmail(createdByEmail);

            if (association == null) {
                throw new AssociationFoundException("Association not found for president email: " + createdByEmail);
            }

            kafilaDto.setCreatedBy(createdByEmail);
            kafilaDto.setAssociationId(association.getId());

            Kafila kafila = modelMapper.map(kafilaDto, Kafila.class);

            DowarDto dowarDto = iDowarService.getDowarsById(kafilaDto.getDowarId());
            Dowar dowar = modelMapper.map(dowarDto, Dowar.class);
            kafila.setAssociation(association);
            kafila.setDowar(dowar);

            Kafila saveKafila = iKafilaRepository.save(kafila);
            if (kafilaDto.getBienKafilaDtos() != null) {
                for (BienKafilaDto bienKafilaDto : kafilaDto.getBienKafilaDtos()) {
                    BiensEssantielDto biensEssentielDto = iBiensEssantielService.getBiensEssantielsById(bienKafilaDto.getBiensEssentielsId());

                    if (biensEssentielDto != null) {
                        int availableQuantity = biensEssentielDto.getQuantity();
                        int requestedQuantity = bienKafilaDto.getQuantityBienKafila();

                        if (availableQuantity >= requestedQuantity && requestedQuantity > 0) {
                            BiensEssantiel biensEssentiel = modelMapper.map(biensEssentielDto, BiensEssantiel.class);

                            biensEssentiel.setQuantity(availableQuantity - requestedQuantity);
                            iBiensEssantielService.updateBiensEssentiel(modelMapper.map(biensEssentiel, BiensEssantielDto.class), bienKafilaDto.getBiensEssentielsId());

                            BienKafila bienKafila = new BienKafila();
                            bienKafila.setBiensEssentiels(biensEssentiel);
                            bienKafila.setKafila(saveKafila);
                            bienKafila.setQuantityBienKafila(requestedQuantity);

                            iBienKafilaRepository.save(bienKafila);
                        } else {
                            throw new BiensEssentielFoundException("Not enough quantity available for BiensEssentiel with ID: " + bienKafilaDto.getBiensEssentielsId());
                        }
                    } else {
                        throw new BiensEssentielFoundException("BiensEssentiel not found with ID: " + bienKafilaDto.getBiensEssentielsId());
                    }
                }
            }

            return modelMapper.map(saveKafila, KafilaDto.class);
        }


        @Override
        public List<KafilaDto> getKafilasCreatedByUser(String createdByEmail) {
            List<Kafila> kafilas = iKafilaRepository.findByCreatedByAndDeletedFalse(createdByEmail);
            return kafilas.stream()
                    .map(kafila -> {
                        KafilaDto kafilaDto = modelMapper.map(kafila, KafilaDto.class);

                        List<BienKafilaDto> bienKafilaDtos = kafila.getBienKafilas().stream()
                                .map(bienKafila -> modelMapper.map(bienKafila, BienKafilaDto.class))
                                .collect(Collectors.toList());

                        kafilaDto.setBienKafilaDtos(bienKafilaDtos);
                        return kafilaDto;
                    })
                    .collect(Collectors.toList());
        }

        @Override
        public KafilaDto getKafilasById(Long id) throws KafilaFoundException {
            return iKafilaRepository.findByIdAndDeletedFalse(id)
                    .map(kafila -> {
                        KafilaDto kafilaDto = modelMapper.map(kafila, KafilaDto.class);

                        List<BienKafilaDto> bienKafilaDtos = kafila.getBienKafilas().stream()
                                .map(bienKafila -> modelMapper.map(bienKafila, BienKafilaDto.class))
                                .collect(Collectors.toList());

                        kafilaDto.setBienKafilaDtos(bienKafilaDtos);
                        return kafilaDto;
                    })
                    .orElseThrow(() -> new KafilaFoundException("Kafila Not found with id = " + id));
        }
        public Long getNumberOfKafilasForCurrentUser() {
            String currentUsername = getCurrentUsername();
            List<Kafila> kafilas = iKafilaRepository.findByCreatedByAndDeletedFalse(currentUsername);
            return (long) kafilas.size();
        }
        public String getCurrentUsername() {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        }
        @Override
        public KafilaDto updateKafila(KafilaDto kafilaDto, Long id){
            Kafila existingKafila = iKafilaRepository.findByIdAndDeletedFalse(id).orElse(null);
            if (existingKafila != null){
                existingKafila.setArrivedKafila(kafilaDto.isArrivedKafila());
                Kafila updateKafila = iKafilaRepository.save(existingKafila);
                updateKafila.setId(id);
                return modelMapper.map(updateKafila , KafilaDto.class);
            }
            return null;
        }

        @Override
        public void deleteKafila(Long id) {
            Kafila kafila = iKafilaRepository.findByIdAndDeletedFalse(id).orElse(null);
            if (kafila != null){
                kafila.setDeleted(true);
                iKafilaRepository.save(kafila);
            }
        }
    }
