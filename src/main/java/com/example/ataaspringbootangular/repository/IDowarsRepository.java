package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.Dowars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface IDowarsRepository extends JpaRepository<Dowars ,Long> {
    List<Dowars> findByDeletedFalse();
    Optional<Dowars> findByIdAndDeletedFalse(Long id);
}
