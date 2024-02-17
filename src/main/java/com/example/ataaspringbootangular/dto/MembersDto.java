package com.example.ataaspringbootangular.dto;

import com.example.ataaspringbootangular.entity.Enum.Genre;
import com.example.ataaspringbootangular.entity.Enum.RoleMembers;
import com.example.ataaspringbootangular.entity.Members;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Members}
 */
@Value
public class MembersDto implements Serializable {
    Long idMembers;
    RoleMembers roleMembers;
    String nomMembres;
    Date dateNaissance;
    Genre genre;
    String email;
    int tele;
    String adress;
    Long associationsIdAssociation;
}