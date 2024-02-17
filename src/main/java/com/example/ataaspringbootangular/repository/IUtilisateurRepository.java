package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    List<Utilisateur> findByDeletedFalse();
    Optional<Utilisateur> findByIdAndDeletedFalse(Long id);
}
