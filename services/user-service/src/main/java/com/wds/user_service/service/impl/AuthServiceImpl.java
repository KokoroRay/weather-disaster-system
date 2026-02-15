package com.wds.user_service.service;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.wds.user_service.dto.request.GoogleLoginRequest;
import com.wds.user_service.dto.request.LoginRequest;
import com.wds.user_service.dto.request.RegisterRequest;
import com.wds.user_service.dto.response.AuthResponse;
import com.wds.user_service.dto.response.UserResponse;
import com.wds.user_service.entity.User;
import com.wds.user_service.exception.AppException;
import com.wds.user_service.exception.ErrorCode;
import com.wds.user_service.entity.constants.AuthProvider;
import com.wds.user_service.entity.constants.Role;
import com.wds.user_service.repository.UserRepository;
import com.wds.user_service.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService  {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Value("${GOOGLE_CLIENT_ID}")
    private String googleClientId;

    public UserResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .authProvider(AuthProvider.LOCAL)
                .isLocked(false)
                .build();

        userRepository.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().name())
                .avatarUrl(user.getAvatarUrl())
                .build();
    }

    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));
        if (user.getAuthProvider() == AuthProvider.GOOGLE) {
            throw new RuntimeException("This email address was registered with Google. Please use Google Login!");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect password!");
        }

        if (user.isLocked()) throw new RuntimeException("Account locked!");
        String jwt = jwtUtils.generateToken(user);
        return new AuthResponse(jwt, user.getFirstName(), user.getLastName(), user.getRole().name());
    }

    public  AuthResponse authenticateGoogle(GoogleLoginRequest request) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                    .setAudience(Collections.singletonList(googleClientId))
                    .build();

            GoogleIdToken idToken = verifier.verify(request.getIdToken());
            if (idToken == null) throw new RuntimeException("Invalid Google token");
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");

            User user = userRepository.findByEmail(email).orElseGet(() -> {
                User newUser = User.builder()
                        .email(email)
                        .firstName(name.split(" ")[0])
                        .lastName(name.split(" ")[1])
                        .avatarUrl(pictureUrl)
                        .role(Role.USER)
                        .authProvider(AuthProvider.GOOGLE)
                        .isLocked(false)
                        .build();
                return userRepository.save(newUser);
            });
            if(user.isLocked()) throw new RuntimeException("Account locked!");
            String jwt = jwtUtils.generateToken(user);
            return new AuthResponse(jwt, user.getFirstName(), user.getLastName(), user.getRole().name());
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException("Error authenticating with Google", e);
        }

    }
}
