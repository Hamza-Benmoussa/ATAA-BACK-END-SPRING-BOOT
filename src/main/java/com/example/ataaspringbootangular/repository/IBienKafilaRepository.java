package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.BienKafila;
import com.example.ataaspringbootangular.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IBienKafilaRepository extends JpaRepository<BienKafila, Long> {
}