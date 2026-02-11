package com.wds.user_service.controller;

import com.wds.user_service.dto.response.UserResponse;
import com.wds.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/lock")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> lockUser(Long userId, boolean isLocked) {
        userService.lockUser(userId, isLocked);
        return ResponseEntity.ok().build();
    }
}
