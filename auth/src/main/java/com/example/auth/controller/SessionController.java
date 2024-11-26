package com.example.auth.controller;


import com.example.auth.config.SessionManager;
import com.example.auth.domain.Member;
import com.example.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SessionController {

    private final AuthService authService;
    private final SessionManager sessionManager;
    private final String LOGIN_MEMBER = "Spring-React-Session-Login";


    @PostMapping("/loginV1")
    public ResponseEntity<String> loginV1(@RequestParam String username,
                                          @RequestParam String password,
                                          HttpServletResponse response) {
        log.info("session login id: {}, password: {}", username, password);

        // Authorization User
        Member member = authService.login(username, password);

        if (member != null) {
            sessionManager.createSession(member, response);
            return ResponseEntity.ok("Success session-login");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Fail login");
    }

    @PostMapping("/loginV2")
    public ResponseEntity<String> loginV2(@RequestParam String username,
                                          @RequestParam String password,
                                          HttpServletRequest request) {

        Member member = authService.login(username, password);
        if (member != null) {
            // Login success handling
            // Return the existing session if it exists; otherwise, create a new session
            HttpSession session = request.getSession();
            // Store login member information in the session
            session.setAttribute(LOGIN_MEMBER, member);

            return ResponseEntity.ok("Success session-login using request");
        }

        // Return an error response if login fails (optional: add this block for clarity)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }


    @PostMapping("/logoutV1")
    public ResponseEntity<String> logoutV1(HttpServletRequest request) {
        if (sessionManager.expire(request)) {
            return ResponseEntity.ok("Success session-logout");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Fail logout");
    }

    @PostMapping("/logoutV2")
    public ResponseEntity<String> logoutV3(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("Success session-logout: ");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            sb.append("expire session");
        }

        return ResponseEntity.ok(sb.toString());
    }
}
