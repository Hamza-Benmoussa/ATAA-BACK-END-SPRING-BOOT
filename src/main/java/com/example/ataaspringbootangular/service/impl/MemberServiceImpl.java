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
        checkExistEmail(memberDto);

        AssociationDto associationDto = iAssociationService.getAssociationsById(memberDto.getAssociationId());
        Association association = modelMapper.map(associationDto , Association.class);
        Member member = modelMapper.map(memberDto, Member.class);
        member.setAssociation(association);
        Member saveMember = iMembersRepository.save(member);
        return modelMapper.map(saveMember, MemberDto.class);
    }

    @Override
    public List<MemberDto> getMembers() {
        List<Member> members = iMembersRepository.findByDeletedFalse();
        return members.stream()
                .map(member -> modelMapper.map(member, MemberDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MemberDto getMembersById(Long id) throws MemberFoundException {
        return iMembersRepository.findByIdAndDeletedFalse(id)
                .map(member -> modelMapper.map(member, MemberDto.class))
                .orElseThrow(() -> new MemberFoundException("Member Not found: " + id));
    }

    @Override
    public MemberDto updateMember(MemberDto memberDto, Long id) throws ParseException {
        Member existingMember = iMembersRepository.findByIdAndDeletedFalse(id).orElse(null);
        checkExistEmail(memberDto);

            existingMember.setEmail(memberDto.getEmail());
            existingMember.setNomMembres(memberDto.getNomMembres());
            existingMember.setDateNaissance(memberDto.getDateNaissance());
            existingMember.setTele(memberDto.getTele());
            existingMember.setAddress(memberDto.getAdress());
            existingMember.setGenre(memberDto.getGenre());
            existingMember.setRoleMembers(memberDto.getRoleMembers());
            Member updateMember = iMembersRepository.save(existingMember);
            updateMember.setId(id);
            return modelMapper.map(updateMember, MemberDto.class);
    }

    private MemberDto checkExistEmail(MemberDto memberDto) {
        if (memberDto.getEmail().equals(getByEmail(memberDto.getEmail()))) {
            throw new EmailDejaExisteException("Email already exists: " + memberDto.getEmail());
        }
        return memberDto;
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
