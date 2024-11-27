package com.example.auth.controller;

import com.example.auth.domain.Member;
import com.example.auth.dto.request.AccountDto.SignUpRequest;
import com.example.auth.dto.response.JwtResponse;
import com.example.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class SecurityController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> signIn(
            @RequestBody SignUpRequest loginRequest
    ) {
        log.info("member sign in username: {} password: {}", loginRequest.getUsername(), loginRequest.getPassword());
        JwtResponse response = authService.loginMember(loginRequest.getUsername(), loginRequest.getPassword());

        return ResponseEntity.ok(response);
    }
}
