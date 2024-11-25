package com.example.auth.repository;

import com.example.auth.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsById(Long id);
    boolean existsByUsername(String username);
    Member findByUsername(String username);
}
