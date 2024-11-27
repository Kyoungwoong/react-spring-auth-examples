package com.example.auth.service;

import com.example.auth.config.security.JwtProvider;
import com.example.auth.domain.Member;
import com.example.auth.dto.response.JwtResponse;
import com.example.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    /**
     * Create a new user.
     *
     * @param member the member to be created
     * @return the created member
     * @throws IllegalArgumentException if the username already exists
     */
    public Member createMember(Member member) {
        if (repository.existsByUsername(member.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        return repository.save(member);
    }

    /**
     * Retrieve a user by their ID.
     *
     * @param id the ID of the member
     * @return the member with the specified ID
     * @throws IllegalArgumentException if no member is found with the given ID
     */
    public Member getMemberById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + id));
    }

    /**
     * Retrieve a user by their username.
     *
     * @param username the username of the member
     * @return the member with the specified username, or null if not found
     */
    public Member getMemberByUsername(String username) {
        if (repository.existsByUsername(username)) {
            return repository.findByUsername(username);
        }
        return null;
    }

    /**
     * Update the information of an existing user.
     *
     * @param id the ID of the member to update
     * @param updatedMember the updated member information
     * @return the updated member
     * @throws IllegalArgumentException if no member is found with the given ID
     */
    public Member updateMember(Long id, Member updatedMember) {
        Member existingMember = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + id));

        existingMember.setUsername(updatedMember.getUsername());
        existingMember.setPassword(updatedMember.getPassword()); // Example: updating password

        return repository.save(existingMember);
    }

    /**
     * Delete a user by their ID.
     *
     * @param id the ID of the member to delete
     * @throws IllegalArgumentException if no member is found with the given ID
     */
    public void deleteMember(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Member not found with ID: " + id);
        }
        repository.deleteById(id);
    }

    /**
     * Authenticate a user by username and password.
     *
     * @param username the username of the member
     * @param password the password of the member
     * @return the authenticated member, or null if authentication fails
     */
    public Member login(String username, String password) {
        Member member = repository.findByUsername(username);

        if (member.getPassword().equals(password)) {
            return member;
        }

        return null;
    }

    public JwtResponse loginMember(String username, String password) {
        if (!repository.existsByUsername(username)) {
            throw new RuntimeErrorException(new Error("not Found Member"));
        }

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        final String jwtAccessToken = jwtProvider.generateJwtToken(authentication);
        final String jwtRefreshToken = null;

        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return new JwtResponse(jwtAccessToken, jwtRefreshToken, userDetails.getUsername(), roles);
    }
}

