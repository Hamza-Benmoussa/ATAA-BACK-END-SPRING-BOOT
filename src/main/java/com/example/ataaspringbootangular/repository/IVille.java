package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IVille extends JpaRepository<Ville,Long> {
    List<Ville> findByDeletedFalse();
    Optional<Ville> findByIdAndDeletedFalse(Long id);
}
