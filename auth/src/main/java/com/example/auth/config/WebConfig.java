package com.example.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

//@Configuration
//public class WebConfig {
//
//    private final String[] ALLOWED_ORIGIN = {
//            "http://localhost:3000", "http://localhost:5173", "http://220.90.208.206", // 통합 웹 관리자 프론트
//            "http://222.122.186.93", // unity
//    };
//
//
//    protected WebConfig() {
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//
//        config.setAllowCredentials(true);
//        config.setAllowedOrigins(Arrays.asList(ALLOWED_ORIGIN));
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//
//        config.setAllowedOriginPatterns(Arrays.asList(ALLOWED_ORIGIN));
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsFilter(source);
//    }
//}
