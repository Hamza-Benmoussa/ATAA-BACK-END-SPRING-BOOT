package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.Residants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IResidantsRepository extends JpaRepository<Residants ,Long> {
    List<Residants> findByDeletedFalse();
    Optional<Residants> findByIdAndDeletedFalse(Long id);
}
