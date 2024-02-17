package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Enum.RoleMembers;
import com.example.ataaspringbootangular.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Member}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto implements Serializable {
    Long idMembers;
    RoleMembers roleMembers;
    String nomMembres;
    Date dateNaissance;
    Genre genre;
    String email;
    int tele;
    String adress;
    private Long associationIdAssociation;


}