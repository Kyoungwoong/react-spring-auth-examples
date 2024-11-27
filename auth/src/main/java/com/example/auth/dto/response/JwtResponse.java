package com.example.auth.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public final class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private String username;
    private List<String> roles;

    public JwtResponse(String accessToken, String refreshToken, String username, List<String> roles) {
        this.accessToken = "Bearer " + accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
        this.roles = roles;
    }
}
