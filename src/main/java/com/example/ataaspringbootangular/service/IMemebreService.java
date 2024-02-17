package com.example.ataaspringbootangular.service;

import com.example.ataaspringbootangular.dto.KafilaDto;
import com.example.ataaspringbootangular.dto.MemberDto;
import com.example.ataaspringbootangular.exception.except.MemberFoundException;
import com.example.ataaspringbootangular.exception.except.UtilisateurFoundException;

import java.text.ParseException;
import java.util.List;

public interface IMemebreService {
    MemberDto ajouterMember(MemberDto memberDto);


    List<MemberDto> getMembers();

    MemberDto getMembersById(Long id) throws MemberFoundException;

    MemberDto updateMember(MemberDto memberDto, Long id) throws ParseException;

    void deleteMember(Long id);
}
