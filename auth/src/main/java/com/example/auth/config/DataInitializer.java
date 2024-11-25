package com.example.auth.config;

import com.example.auth.domain.Member;
import com.example.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final MemberRepository repository;

    @Override
    public void run(String... args) throws Exception {

        Member member = new Member("hiro", "qwer1234");

        repository.save(member);
        System.out.println("데이터 초기화 완료");
    }
}
