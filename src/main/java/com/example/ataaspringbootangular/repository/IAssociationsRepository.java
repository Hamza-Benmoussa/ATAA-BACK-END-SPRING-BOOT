package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.Associations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface IAssociationsRepository extends JpaRepository<Associations , Long> {
    List<Associations> findByDeletedFalse();
    Optional<Associations> findByIdAndDeletedFalse(Long id);
}
