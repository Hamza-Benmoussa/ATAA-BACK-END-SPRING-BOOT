package com.example.ataaspringbootangular.ControllerTest;

import com.example.ataaspringbootangular.controller.MemberController;
import com.example.ataaspringbootangular.dto.MemberDto;
import com.example.ataaspringbootangular.exception.except.AssociationFoundException;
import com.example.ataaspringbootangular.exception.except.MemberFoundException;
import com.example.ataaspringbootangular.service.IMemebreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MemberControllerTest {

    @Mock
    private IMemebreService memebreService;

    @InjectMocks
    private MemberController memberController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void ajouterMember() throws AssociationFoundException {
        MemberDto memberDto = new MemberDto();
        when(memebreService.ajouterMember(memberDto)).thenReturn(memberDto);

        ResponseEntity<MemberDto> responseEntity = memberController.ajouterMember(memberDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(memberDto, responseEntity.getBody());
        verify(memebreService, times(1)).ajouterMember(memberDto);
    }

//    @Test
//    void getAllMembersByPresidentAssociationId() throws MemberFoundException {
//        // Assuming you have a known president association ID for testing purposes
//        Long presidentAssociationId = 123L;
//
//        // Mock the behavior of your service method
//        List<MemberDto> mockMembers = Collections.singletonList(new MemberDto());
//        when(memebreService.getAllMembersByPresidentAssociationId(presidentAssociationId)).thenReturn(mockMembers);
//
//        // Call the controller method
//        ResponseEntity<List<MemberDto>> responseEntity = memberController.getAllMembersByPresidentAssociationId(presidentAssociationId);
//
//        // Assertions
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(mockMembers, responseEntity.getBody());
//        verify(memebreService, times(1)).getAllMembersByPresidentAssociationId(presidentAssociationId);
//    }

    @Test
    void getMemberById() throws MemberFoundException {
        Long id = 1L;
        MemberDto memberDto = new MemberDto();
        when(memebreService.getMembersById(id)).thenReturn(memberDto);

        ResponseEntity<MemberDto> responseEntity = memberController.getMemberById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(memberDto, responseEntity.getBody());
        verify(memebreService, times(1)).getMembersById(id);
    }

    @Test
    void updateMember() throws ParseException {
        Long id = 1L;
        MemberDto memberDto = new MemberDto();
        when(memebreService.updateMember(memberDto, id)).thenReturn(memberDto);

        ResponseEntity<MemberDto> responseEntity = memberController.updateMember(id, memberDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(memberDto, responseEntity.getBody());
        verify(memebreService, times(1)).updateMember(memberDto, id);
    }

    @Test
    void deleteMember() {
        Long id = 1L;
        doNothing().when(memebreService).deleteMember(id);

        ResponseEntity<String> responseEntity = memberController.deleteMember(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Member with id 1 was deleted succes", responseEntity.getBody());
        verify(memebreService, times(1)).deleteMember(id);
    }
}
