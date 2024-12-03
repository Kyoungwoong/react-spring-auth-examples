package com.example.auth.config.security;

import com.example.auth.config.WebConfig;
import com.example.auth.handler.OAuth2AuthenticationFailureHandler;
import com.example.auth.handler.OAuth2AuthenticationSuccessHandler;
import com.example.auth.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import com.example.auth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    /* exclude authorization url */
    private static final String[] permitAllUrl = new String[]{
            /** @brief Retrieve status*/ "/security/**", "/status/all",
            /** @brief login using cookie */ "/cookie/**",
            /** @brief login using session */ "/session/**",
            "/h2-console/**"
    };

    /* Can Access Only Admin */
    private static final String[] permitAdminUrl = new String[]{
            /** @brief Check Access Admin */ "/status/admin",
    };

    /* Can Access Only authorization user */
    private static final String[] permitMemberUrl = new String[]{
            "/status/cert/**",
    };

    private final JwtFilter filter;
    private final WebConfig webConfig;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headersConfigurer -> headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(permitAllUrl).permitAll();
                    auth.requestMatchers(permitAdminUrl).hasRole("ADMIN");
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(configure ->
                        configure.authorizationEndpoint(config -> config.authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository))
                                .userInfoEndpoint(config -> config.userService(customOAuth2UserService))
                                .successHandler(oAuth2AuthenticationSuccessHandler)
                                .failureHandler(oAuth2AuthenticationFailureHandler)
                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .addFilter(webConfig.corsFilter())
                .build();
    }
}
