package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.dto.AssociationDto;
import com.example.ataaspringbootangular.dto.MemberDto;
import com.example.ataaspringbootangular.dto.UtilisateurDto;
import com.example.ataaspringbootangular.entity.Association;
import com.example.ataaspringbootangular.entity.Kafila;
import com.example.ataaspringbootangular.entity.Member;
import com.example.ataaspringbootangular.entity.Utilisateur;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;
import com.example.ataaspringbootangular.exception.except.EmailDejaExisteException;
import com.example.ataaspringbootangular.exception.except.MemberFoundException;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;
import com.example.ataaspringbootangular.repository.IMembersRepository;
import com.example.ataaspringbootangular.service.IAssociationService;
import com.example.ataaspringbootangular.service.IMemebreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberServiceImpl implements IMemebreService {

    @Autowired
    private IMembersRepository iMembersRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IAssociationService iAssociationService;

    @Override
    public MemberDto ajouterMember(MemberDto memberDto) throws AssociationFoundException {
        checkExistEmail(memberDto.getEmail());

        // Fetch the associationId based on the createdBy user
        String createdByEmail = memberDto.getCreatedBy();
        Long associationId = iAssociationService.getAssociationByPresidentEmail(createdByEmail).getId();

        // Set the associationId in the MemberDto
        memberDto.setAssociationId(associationId);

        // Map the MemberDto to the Member entity
        Member member = modelMapper.map(memberDto, Member.class);

        // Save the member
        Member saveMember = iMembersRepository.save(member);

        // Map the saved Member entity back to MemberDto and return
        return modelMapper.map(saveMember, MemberDto.class);
    }
    @Override
    public MemberDto getMembersById(Long id) throws MemberFoundException {
        return iMembersRepository.findByIdAndDeletedFalse(id)
                .map(member -> modelMapper.map(member, MemberDto.class))
                .orElseThrow(() -> new MemberFoundException("Member Not found with id = " + id));
    }
//    @Override
//    public List<MemberDto> getAllMembersByPresidentAssociationId(Long presidentAssociationId) {
//        List<Member> members = iMembersRepository.findByAssociationNomPresidantIdAndDeletedFalse(presidentAssociationId);
//        return members.stream()
//                .map(member -> modelMapper.map(member, MemberDto.class))
//                .collect(Collectors.toList());
//    }
@Override
public List<MemberDto> getMembersCreatedByUser(String createdByEmail) {
    List<Member> members = iMembersRepository.findByCreatedByAndDeletedFalse(createdByEmail);
    return members.stream()
            .map(member -> modelMapper.map(member, MemberDto.class))
            .collect(Collectors.toList());
}

    @Override
    public MemberDto updateMember(MemberDto memberDto, Long id) throws ParseException {
        Member existingMember = iMembersRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (!existingMember.getEmail().equals(memberDto.getEmail())) {
            checkExistEmail(memberDto.getEmail());
        }

        existingMember.setEmail(memberDto.getEmail());
        existingMember.setNomMembres(memberDto.getNomMembres());
        existingMember.setDateNaissance(memberDto.getDateNaissance());
        existingMember.setTele(memberDto.getTele());
        existingMember.setAddress(memberDto.getAddress());
        existingMember.setGenre(memberDto.getGenre());
        existingMember.setRoleMembers(memberDto.getRoleMembers());
        try {
            Member updateMember = iMembersRepository.save(existingMember);
            return modelMapper.map(updateMember,MemberDto.class);
        }catch (EmailDejaExisteException ex){
            throw new EmailDejaExisteException("Email déjà existant dans la base de données.");
        }

    }

    private void checkExistEmail(String email) {
        if (getByEmail(email) != null) {
            throw new EmailDejaExisteException("Email déjà existant dans la base de données.");
        }
    }

    public String getByEmail(String email) {
        Member member = iMembersRepository.findByEmailAndDeletedFalse(email);
        if (member != null) {
            return member.getEmail();
        }
        return null;
    }

    @Override
    public void deleteMember(Long id){
        Member member = iMembersRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (member != null) {
            member.setDeleted(true);
            iMembersRepository.save(member);
        }
    }
}