package com.wds.user_service.service;

import com.wds.user_service.dto.request.ChangePasswordRequest;
import com.wds.user_service.dto.request.UpdateProfileRequest;
import com.wds.user_service.dto.response.UserResponse;
import com.wds.user_service.entity.User;
import com.wds.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public UserResponse getMyProfile() {
        User user = userRepository.findByEmail(getCurrentUserEmail())
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return UserResponse.fromEntity(user);
    }
    public UserResponse updateProfile (UpdateProfileRequest request) {
        User user = userRepository.findByEmail(getCurrentUserEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAvatarUrl(request.getAvatarUrl());
        userRepository.save(user);
        return UserResponse.fromEntity(user);
    }

    public void changePassword(ChangePasswordRequest request) {
        User user = userRepository.findByEmail(getCurrentUserEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect password!");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::fromEntity)
                .collect(Collectors.toList());
    }
    public void lockUser(Long userId, boolean isLocked) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
        user.setLocked(isLocked);
        userRepository.save(user);
    }
}