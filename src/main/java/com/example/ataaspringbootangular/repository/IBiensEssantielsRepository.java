package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.dto.BiensEssantielDto;
import com.example.ataaspringbootangular.entity.BiensEssantiel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IBiensEssantielsRepository extends JpaRepository<BiensEssantiel,Long> {
    List<BiensEssantiel> findByDeletedFalse();
    Optional<BiensEssantiel> findByIdAndDeletedFalse(Long id);
    List<BiensEssantiel> findByCreatedByAndDeletedFalse(String createdByEmail);
}
