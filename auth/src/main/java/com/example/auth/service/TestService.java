package com.example.auth.service;

import com.example.auth.domain.Member;
import com.example.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestService {

    private final MemberRepository memberRepository;

    public String joinMember(String username, String password) throws Exception {
        log.info("Service: joinMember");
        if (memberRepository.existsByUsername(username)) {
            throw new RuntimeException("fail join member");
        }

        Member member = new Member(username, password);

        memberRepository.save(member);

        return member.getUsername();
    }
}
