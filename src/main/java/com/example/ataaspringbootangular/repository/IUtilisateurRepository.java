package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface IUtilisateurRepository extends JpaRepository<Utilisateurs ,Long> {
    List<Utilisateurs> findByDeletedFalse();
    Optional<Utilisateurs> findByIdAndDeletedFalse(Long id);
}
