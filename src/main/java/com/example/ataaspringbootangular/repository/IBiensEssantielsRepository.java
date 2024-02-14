package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.BiensEssantiels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IBiensEssantielsRepository extends JpaRepository<BiensEssantiels ,Long> {
    List<BiensEssantiels> findByDeletedFalse();
    Optional<BiensEssantiels> findByIdAndDeletedFalse(Long id);
}
