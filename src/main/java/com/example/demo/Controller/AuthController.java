package com.example.demo.Controller;

import com.example.demo.Dto.*;
import com.example.demo.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public AuthResponseDto signup(@RequestBody SignupRequestDto request) {
        return authService.signup(request);
    }

    @PostMapping("/signin")
    public AuthResponseDto signin(@RequestBody LoginRequestDto request) {
        return authService.signin(request);
    }
}
