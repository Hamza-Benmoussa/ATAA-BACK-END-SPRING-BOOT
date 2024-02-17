package com.example.ataaspringbootangular.service;

import com.example.ataaspringbootangular.dto.KafilaDto;
import com.example.ataaspringbootangular.dto.MemberDto;

import java.util.List;

public interface IMemebreService {
    MemberDto ajouterMember(MemberDto memberDto);


    List<MemberDto> getMembers();

    MemberDto getMembersById(Long id);

    MemberDto updateMember(MemberDto memberDto, Long id);

    void deleteMember(Long id);
}
