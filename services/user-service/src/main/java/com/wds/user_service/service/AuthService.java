package com.wds.user_service.service;


import com.wds.user_service.dto.request.GoogleLoginRequest;
import com.wds.user_service.dto.request.LoginRequest;
import com.wds.user_service.dto.request.RegisterRequest;
import com.wds.user_service.dto.response.AuthResponse;
import com.wds.user_service.dto.response.UserResponse;

public interface AuthService {

    AuthResponse authenticateGoogle(GoogleLoginRequest request);
    AuthResponse login(LoginRequest loginRequest);
    UserResponse register(RegisterRequest registerRequest);
}
