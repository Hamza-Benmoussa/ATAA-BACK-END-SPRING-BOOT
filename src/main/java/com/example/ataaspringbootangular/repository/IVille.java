package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.Villes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IVille extends JpaRepository<Villes,Long> {
    List<Villes> findByDeletedFalse();
    Optional<Villes> findByIdAndDeletedFalse(Long id);
}
