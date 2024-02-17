package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.Dowar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IDowarsRepository extends JpaRepository<Dowar,Long> {
    List<Dowar> findByDeletedFalse();
    Dowar findByNomDowars(String nomDowar);
    Optional<Dowar> findByIdAndDeletedFalse(Long id);
}
