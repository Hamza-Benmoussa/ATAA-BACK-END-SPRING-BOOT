package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.Association;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAssociationsRepository extends JpaRepository<Association, Long> {
    Association findByNbrSerieAndDeletedFalse(String nmbrSerie);
    Association findByNomAssociationAndDeletedFalse(String nomAssociation);
    List<Association> findByDeletedFalse();

    Optional<Association> findByIdAndDeletedFalse(Long id);

//    List<Association> findByNomPresidantIdAndDeletedFalse(Long currentUserId);
}
