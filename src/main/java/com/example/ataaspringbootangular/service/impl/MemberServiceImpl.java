package com.example.ataaspringbootangular.service.impl;

import com.example.ataaspringbootangular.dto.MemberDto;
import com.example.ataaspringbootangular.entity.Kafila;
import com.example.ataaspringbootangular.entity.Member;
import com.example.ataaspringbootangular.repository.IMembersRepository;
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
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public MemberDto ajouterMember(MemberDto memberDto) {
        Member member = modelMapper.map(memberDto , Member.class);
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
    public MemberDto getMembersById(Long id) {
        return iMembersRepository.findByIdAndDeletedFalse(id)
                .map(member -> modelMapper.map(member , MemberDto.class))
                .orElse(null);
    }

    @Override
    public MemberDto updateMember(MemberDto memberDto, Long id) throws ParseException {
        Member existingMember = iMembersRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (existingMember !=null) {
            existingMember.setEmail(memberDto.getEmail());
            existingMember.setNomMembres(memberDto.getNomMembres());
            existingMember.setDateNaissance(dateFormat.parse(String.valueOf(memberDto.getDateNaissance())));
            existingMember.setTele(memberDto.getTele());
            existingMember.setAdress(memberDto.getAdress());
            existingMember.setGenre(memberDto.getGenre());
            existingMember.setRoleMembers(memberDto.getRoleMembers());
            Member updateMember = iMembersRepository.save(existingMember);
            return modelMapper.map(updateMember , MemberDto.class);
        }
        return null;
    }

    @Override
    public void deleteMember(Long id) {
        Member member =iMembersRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (member != null){
            member.setDeleted(true);
            iMembersRepository.save(member);
        }

    }
}
