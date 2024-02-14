package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.RegionVilles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRegionVille extends JpaRepository<RegionVilles ,Long> {
    List<RegionVilles> findByDeletedFalse();
    Optional<RegionVilles> findByIdAndDeletedFalse(Long id);
}
