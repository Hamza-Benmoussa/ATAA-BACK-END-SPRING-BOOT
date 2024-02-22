package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.dto.BienKafilaDto;
import com.example.ataaspringbootangular.entity.BienKafila;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBienKafilaRepository extends JpaRepository<BienKafila, Long> {
}