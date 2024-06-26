package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.Enum.RoleUser;
import com.example.ataaspringbootangular.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    Utilisateur findByEmailAndDeletedFalse(String email);
    List<Utilisateur> findByDeletedFalse();
    List<Utilisateur> findByRoleUser(RoleUser role);
    Optional<Utilisateur> findByIdAndDeletedFalse(Long id);

    List<Utilisateur> findByRoleUserAndDeletedFalse(RoleUser userRole);
}
