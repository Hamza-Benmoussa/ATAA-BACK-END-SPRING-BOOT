package com.example.ataaspringbootangular.repository;

import com.example.ataaspringbootangular.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface IMembersRepository extends JpaRepository<Member,Long> {
    Member findByEmailAndDeletedFalse(String email);
    List<Member> findByDeletedFalse();
    Optional<Member> findByIdAndDeletedFalse(Long id);
}
