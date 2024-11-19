package com.example.auth.controller;

import com.example.auth.dto.request.AccountDto.SignUpRequest;
import com.example.auth.repository.MemberRepository;
import com.example.auth.service.TestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
@Tags(value = @Tag(name = "TestController", description = "check database"))
public class TestController {

    private final TestService testService;

    @PostMapping("/members")
    public ResponseEntity<String> createUser(@RequestBody SignUpRequest signUpRequest) throws Exception {
        log.info("Controller: createUser");
        String createName = testService.joinMember(signUpRequest.getUsername(), signUpRequest.getPassword());

        return ResponseEntity.ok(createName);
    }
}
