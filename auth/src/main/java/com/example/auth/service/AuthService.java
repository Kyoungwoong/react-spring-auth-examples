package com.example.auth.service;

import com.example.auth.domain.Member;
import com.example.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository repository;

    /**
     * Create: 새로운 사용자 생성
     */
    public Member createMember(Member member) {
        if (repository.existsByUsername(member.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        return repository.save(member);
    }

    /**
     * Read: ID로 사용자 조회
     */
    public Member getMemberById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + id));
    }

    /**
     * Read: Username으로 사용자 조회
     */
    public Member getMemberByUsername(String username) {
        if (repository.existsByUsername(username)) {
            return repository.findByUsername(username);
        }
        return null;
    }

    /**
     * Update: 사용자 정보 수정
     */
    public Member updateMember(Long id, Member updatedMember) {
        Member existingMember = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + id));

        existingMember.setUsername(updatedMember.getUsername());
        existingMember.setPassword(updatedMember.getPassword()); // 예시로 비밀번호도 수정

        return repository.save(existingMember);
    }

    /**
     * Delete: 사용자 삭제
     */
    public void deleteMember(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Member not found with ID: " + id);
        }
        repository.deleteById(id);
    }
}
