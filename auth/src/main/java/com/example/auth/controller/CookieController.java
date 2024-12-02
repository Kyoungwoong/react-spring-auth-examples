package com.example.auth.controller;

import com.example.auth.domain.Member;
import com.example.auth.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/cookie")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CookieController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        log.info("login id: {}, password: {}", username, password);
        // Authorization User
        Member member = authService.getMemberByUsername(username);
        System.out.println(member.getUsername());
        if (member != null) {
            // create cookie & config
            Cookie idCookie = new Cookie("auth-token", String.valueOf(member.getId()));
            response.addCookie(idCookie);

            return ResponseEntity.ok("Success login");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Fail login");
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        log.info("logout");
        // Delete cookie
        Cookie cookie = new Cookie("auth-token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok("Success logout");
    }
}

