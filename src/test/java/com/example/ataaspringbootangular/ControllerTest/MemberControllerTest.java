package com.example.ataaspringbootangular.ControllerTest;

import com.example.ataaspringbootangular.controller.MemberController;
import com.example.ataaspringbootangular.dto.MemberDto;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;
import com.example.ataaspringbootangular.exception.except.MemberFoundException;
import com.example.ataaspringbootangular.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {

    @Mock
    private MemberServiceImpl memberService;

    @InjectMocks
    private MemberController memberController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void ajouterMember() throws AssociationFoundException {
        MemberDto memberDto = new MemberDto();
        when(memberService.ajouterMember(memberDto)).thenReturn(memberDto);

        ResponseEntity<String> responseEntity = memberController.ajouterMember(memberDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("000", responseEntity.getBody());
        verify(memberService, times(1)).ajouterMember(memberDto);
    }

    @Test
    void updateMember() throws Exception {
        Long id = 1L;
        MemberDto memberDto = new MemberDto();
        when(memberService.updateMember(memberDto, id)).thenReturn(memberDto);

        ResponseEntity<String> responseEntity = memberController.updateMember(id, memberDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("000", responseEntity.getBody());
        verify(memberService, times(1)).updateMember(memberDto, id);
    }

    @Test
    void deleteMember() {
        Long id = 1L;
        doNothing().when(memberService).deleteMember(id);

        ResponseEntity<String> responseEntity = memberController.deleteMember(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("000", responseEntity.getBody());
        verify(memberService, times(1)).deleteMember(id);
    }

    @Test
    void getMemberById() throws MemberFoundException {
        Long id = 1L;
        MemberDto memberDto = new MemberDto();
        when(memberService.getMembersById(id)).thenReturn(memberDto);

        ResponseEntity<MemberDto> responseEntity = memberController.getMemberById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(memberDto, responseEntity.getBody());
        verify(memberService, times(1)).getMembersById(id);
    }
}