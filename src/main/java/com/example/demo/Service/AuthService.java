package com.example.demo.Service;

import com.example.demo.Dto.*;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponseDto signup(SignupRequestDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponseDto(false, "Email already in use", null);
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        // 🔥 REAL TOKEN GENERATION
        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponseDto(true, "Signup successful", token);
    }

    public AuthResponseDto signin(LoginRequestDto request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponseDto(false, "Invalid credentials", null);
        }

        // 🔥 REAL TOKEN GENERATION
        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponseDto(true, "Signin successful", token);
    }
}
