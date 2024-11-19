package com.example.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record AccountDto() {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class SignUpRequest {
        private String username;
        private String password;
    }
}
