package com.wds.user_service.controller;

import com.wds.user_service.dto.request.GoogleLoginRequest;
import com.wds.user_service.dto.request.LoginRequest;
import com.wds.user_service.dto.request.RegisterRequest;
import com.wds.user_service.dto.response.ApiResponse;
import com.wds.user_service.dto.response.AuthResponse;
import com.wds.user_service.dto.response.UserResponse;
import com.wds.user_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> register (@RequestBody RegisterRequest registerRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(authService.register(registerRequest))
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ApiResponse.<AuthResponse>builder()
                .result(authService.login(loginRequest))
                .build();
    }

    @PostMapping("/google")
    public ApiResponse<AuthResponse> authenticateGoogle(@RequestBody GoogleLoginRequest request) {
        return ApiResponse.<AuthResponse>builder()
                .result(authService.authenticateGoogle(request))
                .build();
    }
}
