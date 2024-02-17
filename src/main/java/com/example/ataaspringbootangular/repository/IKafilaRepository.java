package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.Kafila;
import com.example.ataaspringbootangular.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IKafilaRepository extends JpaRepository<Kafila,Long> {
    List<Kafila> findByDeletedFalse();
    Optional<Kafila> findByIdAndDeletedFalse(Long id);
}
