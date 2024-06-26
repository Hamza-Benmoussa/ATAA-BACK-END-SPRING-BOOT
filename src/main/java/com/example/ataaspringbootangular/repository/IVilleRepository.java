package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.Dowar;
import com.example.ataaspringbootangular.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IVilleRepository extends JpaRepository<Ville,Long> {
    List<Ville> findByDeletedFalse();
    Ville findByNomVilleAndDeletedFalse(String nomVille);
    Optional<Ville> findByIdAndDeletedFalse(Long id);
    List<Dowar> findAllDowarsByNomVille(String nomVille);

}
