package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
public interface IMembersRepository extends JpaRepository<Members ,Long> {
    List<Members> findByDeletedFalse();
    Optional<Members> findByIdAndDeletedFalse(Long id);
}